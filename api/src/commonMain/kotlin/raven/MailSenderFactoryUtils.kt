package raven

fun emailSender(builder: MailSenderFactory.() -> Unit): EmailSender = MailSenderFactory().apply(builder).build()