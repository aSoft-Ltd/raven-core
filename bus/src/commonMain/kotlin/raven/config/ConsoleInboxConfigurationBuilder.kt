@file:JsExport
package raven.config

import kotlinx.JsExport

class ConsoleInboxConfigurationBuilder(
    internal var enabled: Boolean = false,
    internal var separator: String = "=",
    internal var width: Int = 95,
    internal var margin: Int = 1,
    internal var border: String = "|",
    internal var padding: Int = 1,
) {
    fun enabled(value: Boolean) {
        enabled = value
    }

    fun width(value: Int) {
        width = value
    }

    fun margin(value: Int) {
        margin = value
    }

    fun separator(value: String) {
        separator = value
    }

    fun border(value: String) {
        border = value
    }

    fun padding(value: Int) {
        padding = value
    }
}