package com.jordangellatly.hermosahappyhour.model

object EventRepo {

    fun getAllEventsToday(getDayFromDate: String): List<Event> =
        sampleSearchRestaurantData.flatMap { restaurant ->
            restaurant.weeklyEvents.getValue(getDayFromDate).map { eventMap ->
                eventMap.value
            }
        }

    fun getAllEvents(): List<Event> = sampleSearchRestaurantData.flatMap { restaurant ->
        restaurant.weeklyEvents.values.flatMap { eventMap ->
            eventMap.values
        }
    }

    fun getFilters() = filters
}