package raven

class TemplatedEmailOptions(
    val brand: String,
    val domain: String,
    val from: Address,
    val subject: String,
    val template: EmailTemplate,
    val address: String
)