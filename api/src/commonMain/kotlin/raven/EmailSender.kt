package raven

import koncurrent.Later

interface EmailSender {
    fun send(params: SendEmailParams): Later<SendEmailParams>
}