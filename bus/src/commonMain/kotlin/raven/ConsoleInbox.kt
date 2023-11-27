package raven

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import sanity.EventBus

fun setUpConsoleInbox(
    options: BusEmailOptions,
    formatter: PrettyConsoleEmailFormatter = PrettyConsoleEmailFormatter()
) {
    options.bus.subscribe(options.topic.emailSent()) {
        val params = options.codec.decodeFromString<SendEmailParams>(it.data as String)
        println(formatter.format(params))
    }
}