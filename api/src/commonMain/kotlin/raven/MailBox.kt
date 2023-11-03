package raven

import koncurrent.Later

@Deprecated("Use EmailReceiver instead")
interface MailBox {

    fun anticipate() : Later<String>

    fun save(message: EmailMessage): Later<EmailMessage>

    fun load(): Later<List<EmailMessage>>
}