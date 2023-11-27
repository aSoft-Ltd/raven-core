package raven

import koncurrent.Later
import koncurrent.LaterPromise
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import sanity.EventBus
import sanity.EventSource

class BusEmailReceiver(private val options: BusEmailOptions) : EmailReceiver {
    override fun anticipate(): Later<SendEmailParams> {
        val email = LaterPromise<SendEmailParams>()
        val topic = options.topic.emailSent()
        options.bus.subscribe(topic) {
            email.resolveWith(options.codec.decodeFromString<SendEmailParams>(it.data as String))
        }
        return email
    }
}