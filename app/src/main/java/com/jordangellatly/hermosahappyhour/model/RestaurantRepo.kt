package com.jordangellatly.hermosahappyhour.model

import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import java.text.SimpleDateFormat
import java.util.*

object RestaurantRepo {
    private val date = getCurrentDateTime()
    private val format = SimpleDateFormat("EEEE, yyyy-MM-dd", Locale.getDefault())
    private val formattedDateTimestamp = format.format(date)

    fun getRestaurantsByEventType(eventType: EventType): List<Restaurant> =
        sampleSearchRestaurantData.filter { restaurant ->
            restaurant.eventsByDate.getValue(formattedDateTimestamp).containsKey(eventType)
        }

    fun getRestaurant(restaurantId: Long) =
        sampleSearchRestaurantData.find { it.id == restaurantId }!!

    fun getFilters() = filters
}