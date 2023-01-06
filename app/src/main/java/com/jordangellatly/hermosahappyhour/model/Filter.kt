package com.jordangellatly.hermosahappyhour.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
class Filter(
    val name: String,
    val eventType: EventType,
    val icon: ImageVector? = null
) {
}

val filters = mutableStateListOf(
    Filter(name = "Happy Hour", eventType = EventType.HappyHour),
    Filter(name = "Brunch", eventType = EventType.Brunch),
    Filter(name = "Sports", eventType = EventType.Sports),
    Filter(name = "Special", eventType = EventType.Special)
)