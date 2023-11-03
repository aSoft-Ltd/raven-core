package raven

import koncurrent.Later
import koncurrent.Laters
import koncurrent.toLater

class MailSender(private val mailers: List<EmailSender>) : EmailSender {

    override fun send(params: SendEmailParams): Later<SendEmailParams> {
        return Laters(*mailers.map { it.send(params) }.toTypedArray()).andThen {
            params.toLater()
        }
    }
}