package raven

import koncurrent.later.finally
import koncurrent.toLater
import koncurrent.later.then
import koncurrent.later.andThen

class ConsoleEmailSender(private val options: ConsoleEmailSenderOptions = ConsoleEmailSenderOptions()) : EmailSender {

    override fun supports(body: EmailContentType) = body == EmailContentType.plain
    override fun send(params: SendEmailParams) = params.toLater().finally { println(options.formatter.format(params)) }

    override fun toString(): String = "ConsoleEmailSender"
}