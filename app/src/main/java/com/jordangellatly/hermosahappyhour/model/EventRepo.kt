package com.jordangellatly.hermosahappyhour.model

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import java.text.SimpleDateFormat
import java.util.*

object EventRepo {

    fun getAllEventsByDate(date: Date): SnapshotStateList<Event> = sampleEventData.filter {
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val currentDateTimestamp = defaultFormat.format(date)
        val formattedCurrentDateString = formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
        val formattedEventStartString = formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
        formattedCurrentDateString == formattedEventStartString
    }.toMutableStateList()

    fun getEventsByDateAndType(date: Date, eventType: EventType): SnapshotStateList<Event> =
        sampleEventData.filter {
            val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
            val currentDateTimestamp = defaultFormat.format(date)
            val formattedCurrentDateString = formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
            val formattedEventStartString = formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
            formattedCurrentDateString == formattedEventStartString && it.eventType == eventType
        }.toMutableStateList()

    fun getEvent(eventId: UUID) = sampleEventData.find { it.id == eventId }!!

    fun getFilters() = filters
}