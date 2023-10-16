package raven

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("mock")
data class MockMailerExternalConfig(
    val printToConsole: Boolean = MockMailerOptions.DEFAULT_PRINT_TO_CONSOLE
) : MailerExternalConfig()