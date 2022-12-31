package com.jordangellatly.hermosahappyhour.model

import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import java.text.SimpleDateFormat
import java.util.*

object EventRepo {

    fun getAllEventsByDate(date: Date): List<Event> = sampleEventData.filter {
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val currentDateTimestamp = defaultFormat.format(date)
        val formattedCurrentDateString = formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
        val formattedEventStartString = formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
        formattedCurrentDateString == formattedEventStartString
    }

//    fun getAllEventsToday(getDayFromDate: String): List<Event> =
//        sampleSearchRestaurantData.flatMap { restaurant ->
//            restaurant.eventsToday.getValue(getDayFromDate).map { eventMap ->
//                eventMap.value
//            }
//        }

    fun getAllEvents(): List<Event> = sampleSearchRestaurantData.flatMap { restaurant ->
        restaurant.eventsByDate.values.flatMap { eventMap ->
            eventMap.values
        }
    }

    fun getFilters() = filters
}