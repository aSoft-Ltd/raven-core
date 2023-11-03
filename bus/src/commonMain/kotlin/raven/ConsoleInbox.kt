package raven

import kotlinx.serialization.json.Json
import sanity.EventBus

fun setUpConsoleInbox(
    bus: EventBus,
    formatter: PrettyConsoleEmailFormatter = PrettyConsoleEmailFormatter()
) {
    val topic = BusEmailTopic()
    bus.subscribe(topic.emailSent()) {
        val params = Json.decodeFromString<SendEmailParams>(it.data as String)
        println(formatter.format(params))
    }
}