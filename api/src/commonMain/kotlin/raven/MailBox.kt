package raven

import koncurrent.Later

interface MailBox {

    fun anticipate() : Later<String>

    fun save(message: EmailMessage): Later<EmailMessage>

    fun load(): Later<List<EmailMessage>>
}