package raven

import kase.Failure
import kase.Result
import kollections.Collection
import kollections.List
import kollections.any
import kollections.filterIsInstance
import kollections.first
import kollections.firstOrNull
import kollections.forEach
import kollections.isEmpty
import kollections.isNotEmpty
import kollections.joinToString
import kollections.map
import koncurrent.Later
import koncurrent.Laters
import koncurrent.later.then
import raven.EmailContentType.html
import raven.EmailContentType.plain
import raven.exceptions.MailingException

class MultiEmailSender(private val senders: List<EmailSender>) : EmailSender {

    override fun supports(body: EmailContentType) = senders.any { it.supports(body) }
    override fun send(params: SendEmailParams): Later<SendEmailParams> = Laters(senders.map {
        it.send(params)
    }).ensureNoFailures()

    fun send(params: SendEmailTemplateParams) = Laters(senders.map {
        when {
            it.supports(html) -> it.send(params.toParams(html))
            it.supports(plain) -> it.send(params.toParams(plain))
            else -> throw MailingException("Unsupported email type")
        }
    }).ensureNoFailures()

    private fun Later<List<Result<SendEmailParams>>>.ensureNoFailures() = then {
        val failures = it.filterIsInstance<Failure<SendEmailParams>>()
        if (failures.isNotEmpty()) throw failures.toCause()

        val error = "No mailer has been added to a multi sender email"
        it.firstOrNull()?.getOrThrow() ?: throw MailingException(error)
    }

    private fun SendEmailTemplateParams.toParams(type: EmailContentType) = SendEmailParams(
        subject = subject,
        from = from,
        to = to,
        cc = cc,
        bcc = bcc,
        body = when (type) {
            html -> body.html
            plain -> body.plain
        },
        type = type,
        attachments = attachments
    )

    private fun Collection<Failure<SendEmailParams>>.toMessage() = joinToString("\n") { it.message }

    private fun Collection<Failure<SendEmailParams>>.toCause() = MailingException(first().message).apply {
        forEach { addSuppressed(it.cause) }
    }
}