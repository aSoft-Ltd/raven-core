package raven

import koncurrent.Later

interface EmailSender {
    fun supports(body: EmailContentType): Boolean

    fun send(params: SendEmailParams): Later<SendEmailParams>

//    fun send(params: SendEmailParamsTemplate): Later<SendEmailParams>
}