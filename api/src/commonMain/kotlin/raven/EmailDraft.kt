@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package raven

import kollections.List
import kollections.iListOf
import kotlin.js.JsExport
@Deprecated("use SendEmailParams instead together with EmailSender instead of Mailer")
data class EmailDraft(
    val subject: String,
    val body: String,
    val attachments: List<EmailAttachment<Any?>> = iListOf()
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
        status = iListOf()
    )
}