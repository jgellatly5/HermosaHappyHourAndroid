package com.jordangellatly.hermosahappyhour.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
class Filter(
    val name: String,
    val eventType: EventType,
    enabled: Boolean = false,
    val icon: ImageVector? = null
) {
    val enabled = mutableStateOf(enabled)
}

val filters = listOf(
    Filter(name = "Happy Hour", eventType = EventType.HappyHour),
    Filter(name = "Brunch", eventType = EventType.Brunch),
    Filter(name = "Sports", eventType = EventType.Sports),
    Filter(name = "Default", eventType = EventType.Special)
)