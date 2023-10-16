package raven

class FlixMailEndpoint(private val base: String) {
    fun send() = "$base/mail/send"
    fun mailbox() = "$base/mail/box"
}