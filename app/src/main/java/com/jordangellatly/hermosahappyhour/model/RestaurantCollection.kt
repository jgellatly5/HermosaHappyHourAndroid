package com.jordangellatly.hermosahappyhour.model

import androidx.compose.runtime.Immutable

@Immutable
data class RestaurantCollection(
    val id: Long,
    val name: String,
    val snacks: List<Restaurant>,
    val type: CollectionType = CollectionType.Normal
)

enum class CollectionType { Normal, Highlight }