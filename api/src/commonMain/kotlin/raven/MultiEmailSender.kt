package raven

import kase.Failure
import koncurrent.Later
import koncurrent.Laters
import koncurrent.toLater
import kollections.List
import kollections.Collection
import kollections.map
import kollections.toTypedArray
import kollections.any
import kase.Result
import kollections.filterIsInstance
import kollections.first
import kollections.forEach
import kollections.isEmpty
import kollections.joinToString
import raven.EmailContentType.html
import raven.EmailContentType.plain

class MultiEmailSender(private val senders: List<EmailSender>) : EmailSender {

    override fun supports(body: EmailContentType) = senders.any { it.supports(body) }
    override fun send(params: SendEmailParams): Later<SendEmailParams> = Laters(*senders.map {
        it.send(params)
    }.toTypedArray()).ensureNoFailures()

    fun send(params: SendEmailTemplateParams) = Laters(*senders.map {
        when {
            it.supports(html) -> it.send(params.toParams(html))
            it.supports(plain) -> it.send(params.toParams(plain))
            else -> throw IllegalArgumentException("Unsupported email type")
        }
    }.toTypedArray()).ensureNoFailures()

    private fun Later<List<Result<SendEmailParams>>>.ensureNoFailures() = then {
        val failures = it.filterIsInstance<Failure<SendEmailParams>>()
        if (failures.isEmpty()) {
            it.first().getOrThrow()
        } else {
            throw failures.toCause()
        }
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

    private fun Collection<Failure<SendEmailParams>>.toCause() = RuntimeException("Failed to send email").apply {
        forEach { addSuppressed(it.cause) }
    }
}