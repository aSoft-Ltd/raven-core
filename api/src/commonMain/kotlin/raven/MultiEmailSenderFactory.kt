package raven

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kollections.mutableListOf
import kollections.add
import kollections.addAll
import kollections.List
import kollections.isEmpty

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