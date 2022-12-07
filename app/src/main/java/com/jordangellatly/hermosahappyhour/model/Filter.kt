package com.jordangellatly.hermosahappyhour.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
class Filter(
    val name: String,
    enabled: Boolean = false,
    val icon: ImageVector? = null
) {
    val enabled = mutableStateOf(enabled)
}

val filters = listOf(
    Filter(name = "Food"),
    Filter(name = "Drink"),
    Filter(name = "Brunch"),
    Filter(name = "Sports")
)