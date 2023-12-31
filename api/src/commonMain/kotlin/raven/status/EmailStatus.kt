@file:JsExport

package raven.status

import identifier.Email
import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
sealed class EmailStatus {
    abstract val to: Email
}

data class EmailSent(override val to: Email) : EmailStatus()
data class EmailFailed(override val to: Email, val cause: Throwable) : EmailStatus()