package raven

import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch

interface EmailSender {
    fun supports(body: EmailContentType): Boolean

    fun send(params: SendEmailParams): Later<SendEmailParams>
}