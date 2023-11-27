package raven

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MultiEmailSenderFactory : ReadOnlyProperty<Any?, MultiEmailSender> {

    private val senders = mutableListOf<EmailSender>()
    fun add(mailer: EmailSender) = senders.add(mailer)

    fun addAll(senders: List<EmailSender>) = this.senders.addAll(senders)

    fun build(): MultiEmailSender {
        if (senders.isEmpty()) {
            println("[WARNING]: There are no email senders configured")
        }
        return MultiEmailSender(senders)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>) = MultiEmailSender(senders)
}