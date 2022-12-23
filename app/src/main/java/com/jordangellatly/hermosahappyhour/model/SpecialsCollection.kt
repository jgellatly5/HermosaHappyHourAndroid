package com.jordangellatly.hermosahappyhour.model

import androidx.compose.runtime.Immutable

@Immutable
data class SpecialsCollection(
    val id: Long,
    val name: String,
    val specials: List<Deal>
)