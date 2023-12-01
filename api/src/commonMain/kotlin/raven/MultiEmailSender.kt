package raven

import kase.Failure
import koncurrent.Later
import koncurrent.Laters
import koncurrent.toLater

class MultiEmailSender(private val senders: List<EmailSender>) : EmailSender {

    override fun supports(body: EmailContentType) = senders.any { it.supports(body) }
    override fun send(params: SendEmailParams): Later<SendEmailParams> = Laters(*senders.map {
        it.send(params)
    }.toTypedArray()).andThen {
        val failures = it.filterIsInstance<Failure<SendEmailParams>>()
        if (failures.isEmpty()) {
            params.toLater()
        } else {
            throw failures.toCause()
        }
    }

    private fun Collection<Failure<SendEmailParams>>.toMessage() = joinToString("\n") { it.message }

    private fun Collection<Failure<SendEmailParams>>.toCause() = RuntimeException("Failed to send email").apply {
        forEach { addSuppressed(it.cause) }
    }
}