package raven

import koncurrent.toLater

class ConsoleEmailSender(private val config: ConsoleEmailFormatter = PrettyConsoleEmailFormatter()) : EmailSender {

    override fun send(params: SendEmailParams) = params.toLater().complete { println(config.format(params)) }

    override fun toString(): String = "ConsoleEmailSender"
}