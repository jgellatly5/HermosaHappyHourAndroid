package com.jordangellatly.hermosahappyhour.model

import androidx.compose.runtime.Immutable

@Immutable
data class RestaurantCollection(
    val id: Long,
    val name: String,
    val restaurants: List<Restaurant>,
    val type: CollectionType = CollectionType.Normal
)

enum class CollectionType { Normal, HappyHour, Event }

object RestaurantRepo {
    fun getHappyHourRestaurants(): RestaurantCollection = happyHourRestaurants
    fun getRestaurantsWithEvents(): RestaurantCollection = restaurantsWithEvents
    fun getRestaurant(restaurantId: Long) = sampleSearchRestaurantData.find { it.id == restaurantId }!!
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