package raven

import kotlinx.serialization.StringFormat
import sanity.EventBus

class BusEmailOptions(
    val bus: EventBus,
    val topic: BusEmailTopic,
    val codec: StringFormat
)