package com.jordangellatly.hermosahappyhour.model

import androidx.compose.runtime.Immutable
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.home.toString

@Immutable
data class RestaurantCollection(
    val id: Long,
    val name: String,
    val restaurants: List<Restaurant>,
    val type: CollectionType = CollectionType.Normal
)

enum class CollectionType { Normal, HappyHour, Event }

object RestaurantRepo {
    val date = getCurrentDateTime()
    val getDayFromDate = date.toString("EEEE").uppercase()
    fun getRestaurantsByEventType(eventType: EventType): List<Restaurant> =
        sampleSearchRestaurantData

    fun getRestaurantsWithEvents(): RestaurantCollection = restaurantsWithEvents
    fun getRestaurant(restaurantId: Long) =
        sampleSearchRestaurantData.find { it.id == restaurantId }!!

    fun getFilters() = filters
}

private val happyHourRestaurants = RestaurantCollection(
    id = 1L,
    name = "Happy Hour Restaurants",
    type = CollectionType.HappyHour,
    restaurants = sampleSearchRestaurantData
)

private val restaurantsWithEvents = RestaurantCollection(
    id = 2L,
    name = "Restaurants with Events",
    type = CollectionType.Event,
    restaurants = sampleSearchRestaurantData
)