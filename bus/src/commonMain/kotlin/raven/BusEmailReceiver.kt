package raven

import koncurrent.Later
import koncurrent.PendingLater
import koncurrent.resolveWith
import kotlinx.serialization.decodeFromString

class BusEmailReceiver(private val options: BusEmailOptions) : EmailReceiver {
    override fun anticipate(): Later<SendEmailParams> {
        val email = PendingLater<SendEmailParams>()
        val topic = options.topic.emailSent()
        options.bus.subscribe(topic) {
            email.resolveWith(options.codec.decodeFromString<SendEmailParams>(it.data as String))
        }
        return email
    }
}