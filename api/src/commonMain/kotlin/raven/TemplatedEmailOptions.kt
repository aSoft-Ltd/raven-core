package raven

class TemplatedEmailOptions(
    val address: AddressInfo,
    val subject: String,
    val template: String
)