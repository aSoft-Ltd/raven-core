package raven

data class SendEmailParamsTemplate(
    val subject: String,
    val from: Address,
    val to: List<Address>,
    val cc: List<Address>,
    val bcc: List<Address>,
    val body: TemplateFactoryOptions,
    val attachments: List<EmailAttachment<Any?>> = emptyList(),
)