package com.jordangellatly.hermosahappyhour.ui.detail

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventRepository @Inject constructor() : EventRepo {

    override fun getAllEventsByDate(date: Date): SnapshotStateList<Event> = sampleEventData.filter {
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val currentDateTimestamp = defaultFormat.format(date)
        val formattedCurrentDateString = formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
        val formattedEventStartString = formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
        formattedCurrentDateString == formattedEventStartString
    }.toMutableStateList()

    override fun getEventsByDateAndType(date: Date, eventType: EventType): SnapshotStateList<Event> =
        sampleEventData.filter {
            val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
            val currentDateTimestamp = defaultFormat.format(date)
            val formattedCurrentDateString = formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
            val formattedEventStartString = formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
            formattedCurrentDateString == formattedEventStartString && it.eventType == eventType
        }.toMutableStateList()

    override fun getEventById(eventId: UUID) = sampleEventData.find { it.id == eventId }!!

    override fun getFilters(): SnapshotStateList<Filter> = filters
}

interface EventRepo {
    fun getAllEventsByDate(date: Date): SnapshotStateList<Event>
    fun getEventsByDateAndType(date: Date, eventType: EventType): SnapshotStateList<Event>
    fun getEventById(eventId: UUID): Event
    fun getFilters(): SnapshotStateList<Filter>
}