@file:JsExport

package raven.config

import kotlinx.JsExport
import raven.PrettyConsoleEmailFormatterOptions
import kotlin.jvm.JvmField

fun EmailConsoleConfigurationBuilder.build() = console.takeIf { it.enabled }?.let {
    PrettyConsoleEmailFormatterOptions(
        separator = it.separator,
        width = it.width,
        margin = it.margin,
        padding = it.padding,
        border = it.border
    )
}