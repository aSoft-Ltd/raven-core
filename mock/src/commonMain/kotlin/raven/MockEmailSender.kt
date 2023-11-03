package raven

import koncurrent.toLater

class MockEmailSender : EmailSender {
    val outbox = mutableListOf<SendEmailParams>()
    override fun send(params: SendEmailParams) = params.toLater().complete { outbox.add(params) }
}