package raven

import koncurrent.Later
import koncurrent.Laters

class MailSender(private val mailers: List<Mailer>) : Mailer {

    override fun send(draft: EmailDraft, from: AddressInfo, to: kollections.List<AddressInfo>): Later<EmailMessage> {
        return Laters(*mailers.map { it.send(draft, from, to) }.toTypedArray()).andThen {
            Later(draft.toMessage(from, to))
        }
    }
}