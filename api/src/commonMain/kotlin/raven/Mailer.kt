package raven

import identifier.Email
import koncurrent.Later
import kollections.List
import kollections.listOf

/**
 * An interface to be used to send emails
 */
@Deprecated("use EmailSender instead")
interface Mailer {
    fun send(draft: EmailDraft, from: AddressInfo, to: List<AddressInfo>): Later<EmailMessage>

    fun send(draft: EmailDraft, from: AddressInfo, to: AddressInfo): Later<EmailMessage> = send(draft, from, listOf(to))

    fun send(draft: EmailDraft, from: String, to: String) = send(draft, Email(from), Email(to))

    fun send(draft: EmailDraft, from: Email, to: Email): Later<EmailMessage> = send(draft, AddressInfo(from), listOf(AddressInfo(to)))
}