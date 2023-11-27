package raven

fun emailSender(builder: MultiEmailSenderFactory.() -> Unit): EmailSender = MultiEmailSenderFactory().apply(builder).build()