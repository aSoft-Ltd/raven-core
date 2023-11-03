package raven

import koncurrent.Later
import koncurrent.toLater
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import sanity.EventBus
import sanity.EventDispatcher

class BusEmailSender(private val options: BusEmailSenderOptions) : EmailSender {

    private val topic = BusEmailTopic()
    override fun send(params: SendEmailParams): Later<SendEmailParams> {
        options.bus.dispatch(topic.emailSent(), Json.encodeToString<SendEmailParams>(params))
        return params.toLater()
    }
}