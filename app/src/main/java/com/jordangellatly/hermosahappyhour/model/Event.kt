package com.jordangellatly.hermosahappyhour.model

import androidx.compose.runtime.mutableStateListOf
import com.jordangellatly.hermosahappyhour.model.tower12.*

data class Event(
    val id: Long,
    val eventType: EventType,
    val title: String,
    val description: String = "",
    val startTimestamp: String,
    val endTimestamp: String,
    val weeklyHoursDescription: String,
    val restaurantId: Long,
    val drinkSpecials: List<Deal>? = null,
    val foodSpecials: List<Deal>? = null,
    val eventUrl: String
)

data class Deal(
    val description: String,
    val price: String,
    val imageUrl: String = "https://cdn-icons-png.flaticon.com/512/187/187448.png"
)

enum class EventType { HappyHour, Brunch, Sports, Special, All }

val sampleEventData = mutableStateListOf(
    tower12MondayHappyHour,
    tower12TuesdayHappyHour,
    tower12WednesdayHappyHour,
    tower12ThursdayHappyHour,
    tower12FridayHappyHour,
    mondayNightFootball,
    tacoTuesday,
    wineWednesday,
    thursdayNightFootball,
    fridayNightTrivia,
    saturdayBrunch,
    saturdaySportEvent,
    sundayBrunch,
    sundaySportEvent,
    saturdayHappyHour,
    sundayHappyHour,
    sundaySilentDiscoSunset,
    mondayBrunch
)