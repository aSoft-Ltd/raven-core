@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package raven

import kollections.List
import kollections.listOf
import kotlinx.JsExport
@Deprecated("use SendEmailParams instead together with EmailSender instead of Mailer")
data class EmailDraft(
    val subject: String,
    val body: String,
    val attachments: List<EmailAttachment<Any?>> = listOf()
) {
    fun toMessage(
        from: AddressInfo,
        to: List<AddressInfo>
    ) = EmailMessage(
        subject = subject,
        from = from,
        to = to,
        body = body,
        attachments = attachments,
        status = listOf()
    )
}