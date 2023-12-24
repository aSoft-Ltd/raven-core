package raven

import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch

@Deprecated("Use EmailReceiver instead")
interface MailBox {

    fun anticipate() : Later<String>

    fun save(message: EmailMessage): Later<EmailMessage>

    fun load(): Later<List<EmailMessage>>
}