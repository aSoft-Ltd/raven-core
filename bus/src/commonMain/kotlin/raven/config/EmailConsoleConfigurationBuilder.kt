@file:JsExport

package raven.config

import kotlinx.JsExport
import kotlin.jvm.JvmField

class EmailConsoleConfigurationBuilder {
    @JvmField
    val console = ConsoleInboxConfigurationBuilder()
}