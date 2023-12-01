package raven

import koncurrent.toLater

class ConsoleEmailSender(private val options: ConsoleEmailSenderOptions = ConsoleEmailSenderOptions()) : EmailSender {

    override fun supports(body: EmailContentType) = body == EmailContentType.plain
    override fun send(params: SendEmailParams) = params.toLater().complete { println(options.formatter.format(params)) }

    override fun toString(): String = "ConsoleEmailSender"
}