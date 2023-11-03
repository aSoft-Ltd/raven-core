package raven

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MailSenderFactory : ReadOnlyProperty<Any?, MailSender> {

    private val senders = mutableListOf<EmailSender>()
    fun add(mailer: EmailSender) = senders.add(mailer)

    fun addAll(senders: List<EmailSender>) = this.senders.addAll(senders)

    fun build() = MailSender(senders)
    override fun getValue(thisRef: Any?, property: KProperty<*>) = MailSender(senders)
}