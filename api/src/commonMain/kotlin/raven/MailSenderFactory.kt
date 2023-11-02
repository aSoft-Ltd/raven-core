package raven

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MailSenderFactory : ReadOnlyProperty<Any?, Mailer> {

    private val mailers = mutableListOf<Mailer>()
    fun add(mailer: Mailer) = mailers.add(mailer)

    fun addAll(mailers: List<Mailer>) = this.mailers.addAll(mailers)

    fun build() = MailSender(mailers)
    override fun getValue(thisRef: Any?, property: KProperty<*>) = MailSender(mailers)
}