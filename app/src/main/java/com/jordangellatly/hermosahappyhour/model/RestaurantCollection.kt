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
    fun getRestaurants(): List<RestaurantCollection> = restaurantCollections
    fun getRestaurant(restaurantId: Long) = sampleSearchRestaurantData.find { it.id == restaurantId }!!
    fun getFilters() = filters
}

private val featuredDeals = RestaurantCollection(
    id = 1L,
    name = "Featured Happy Hours",
    type = CollectionType.Normal,
    restaurants = sampleSearchRestaurantData
)

private val featuredEvents = RestaurantCollection(
    id = 2L,
    name = "Featured Events",
    type = CollectionType.Event,
    restaurants = sampleSearchRestaurantData
)

//private val popularRestaurants = RestaurantCollection(
//    id = 3L,
//    name = "Popular Restaurants",
//    type = CollectionType.Normal,
//    restaurants = sampleSearchRestaurantData
//)
//
//private val newlyAdded = RestaurantCollection(
//    id = 4L,
//    name = "Newly Added",
//    type = CollectionType.Normal,
//    restaurants = sampleSearchRestaurantData
//)

val restaurantCollections = listOf(
    featuredDeals,
    featuredEvents,
//    popularRestaurants,
//    newlyAdded
)