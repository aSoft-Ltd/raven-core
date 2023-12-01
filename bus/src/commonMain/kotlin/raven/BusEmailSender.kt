package raven

import koncurrent.Later
import koncurrent.toLater
import kotlinx.serialization.encodeToString

class BusEmailSender(private val options: BusEmailOptions) : EmailSender {

    override fun supports(body: EmailContentType) = body == EmailContentType.plain
    override fun send(params: SendEmailParams): Later<SendEmailParams> {
        val topic = options.topic.emailSent()
        options.bus.dispatch(topic, options.codec.encodeToString<SendEmailParams>(params))
        return params.toLater()
    }
}