package com.jordangellatly.hermosahappyhour.model

import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.home.toString

object RestaurantRepo {
    private val date = getCurrentDateTime()
    private val getDayFromDate = date.toString("EEEE").uppercase()

    fun getRestaurantsByEventType(eventType: EventType): List<Restaurant> =
        sampleSearchRestaurantData.filter { restaurant ->
            restaurant.weeklyEvents.getValue(getDayFromDate).containsKey(eventType)
        }

    fun getRestaurant(restaurantId: Long) =
        sampleSearchRestaurantData.find { it.id == restaurantId }!!

    fun getFilters() = filters
}