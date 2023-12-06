package raven

fun emailSender(builder: MultiEmailSenderFactory.() -> Unit): MultiEmailSender = MultiEmailSenderFactory().apply(builder).build()