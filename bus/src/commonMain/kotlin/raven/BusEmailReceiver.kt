package raven

import koncurrent.Later
import koncurrent.LaterPromise
import kotlinx.serialization.json.Json
import sanity.EventBus
import sanity.EventSource

class BusEmailReceiver(val bus: EventBus) : EmailReceiver {
    private val topic = BusEmailTopic()
    override fun anticipate(): Later<SendEmailParams> {
        val email = LaterPromise<SendEmailParams>()
        bus.subscribe(topic.emailSent()) {
            email.resolveWith(Json.decodeFromString<SendEmailParams>(it.data as String))
        }
        return email
    }
}