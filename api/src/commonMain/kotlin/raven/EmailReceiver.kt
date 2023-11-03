package raven

import koncurrent.Later

interface EmailReceiver {
    fun anticipate() : Later<SendEmailParams>
}