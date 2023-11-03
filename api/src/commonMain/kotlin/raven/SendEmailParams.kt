package raven

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class SendEmailParams(
    val subject: String,
    val from: Address,
    val to: List<Address>,
    val cc: List<Address>,
    val bcc: List<Address>,
    val body: String,
    @Transient
    val attachments: List<EmailAttachment<Any?>> = emptyList(),
)