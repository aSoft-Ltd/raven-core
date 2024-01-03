package raven

import koncurrent.Later
import koncurrent.PendingLater
import koncurrent.resolveWith

@Deprecated("use BusMailBoxReceiver instead")
class LocalMemoryMailbox : MailBox {
    private val messages = mutableListOf<EmailMessage>()

    private val laters = mutableListOf<PendingLater<String>>()

    override fun save(message: EmailMessage): Later<EmailMessage> {
        messages.add(message)
        for(later in laters) later.resolveWith(message.body)
        laters.clear()
        return Later(message)
    }

    override fun anticipate(): Later<String> {
        val later = PendingLater<String>()
        laters.add(later)
        return later
    }

    override fun load(): Later<List<EmailMessage>> = Later(messages)
}