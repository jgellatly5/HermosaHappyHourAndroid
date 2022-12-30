package com.jordangellatly.hermosahappyhour.model

object EventRepo {

    fun getAllEventsToday(getDayFromDate: String): List<Event> = sampleEventData

//    fun getAllEventsToday(getDayFromDate: String): List<Event> =
//        sampleSearchRestaurantData.flatMap { restaurant ->
//            restaurant.eventsToday.getValue(getDayFromDate).map { eventMap ->
//                eventMap.value
//            }
//        }

    fun getAllEvents(): List<Event> = sampleSearchRestaurantData.flatMap { restaurant ->
        restaurant.eventsToday.values.flatMap { eventMap ->
            eventMap.values
        }
    }

    fun getFilters() = filters
}