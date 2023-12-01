package raven

import koncurrent.toLater

class MockEmailSender : EmailSender {
    val outbox = mutableListOf<SendEmailParams>()

    override fun supports(body: EmailContentType): Boolean = true
    override fun send(params: SendEmailParams) = params.toLater().complete { outbox.add(params) }
}