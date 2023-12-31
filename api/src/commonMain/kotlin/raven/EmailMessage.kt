@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package raven

import kollections.List
import kollections.listOf
import kotlinx.JsExport
import raven.status.EmailStatus

@Deprecated("use MailMessageInstead")
data class EmailMessage(
    val subject: String,
    val from: AddressInfo,
    val to: List<AddressInfo>,
    val body: String,
    val attachments: List<EmailAttachment<Any?>> = listOf(),
    val status: List<EmailStatus>
)