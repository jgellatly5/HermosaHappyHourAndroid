package com.jordangellatly.hermosahappyhour.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.jordangellatly.hermosahappyhour.api.EventsService
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventRepository @Inject constructor(private val eventsService: EventsService) {

    private val eventsDataSource: EventsDataSource = EventsCache()

    suspend fun getAllEvents(): List<Event> = eventsService.getAllEvents()

    fun getEventById(eventId: UUID): Event {
        val date = getCurrentDateTime()
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val currentDateTimestamp = defaultFormat.format(date)
        val formattedCurrentDateString =
            formatTimestamp(currentDateTimestamp, "yyyy-MM-dd").toString()
        return eventsDataSource.getEvents(formattedCurrentDateString).find { it.id == eventId }!!
    }

    fun getFilters(): SnapshotStateList<Filter> = filters
}