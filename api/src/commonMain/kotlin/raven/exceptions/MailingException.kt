package raven.exceptions

class MailingException(override val message: String?) : Exception(message)