package com.jordangellatly.hermosahappyhour.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.jordangellatly.hermosahappyhour.api.EventsApi
import com.jordangellatly.hermosahappyhour.api.RetrofitClient
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventRepository @Inject constructor() {

    private val retrofit = RetrofitClient.getInstance()
    private val eventsApi = retrofit.create(EventsApi::class.java)
    private val eventsDataSource: EventsDataSource = EventsCache()

    fun getAllEventsByDate(date: Date): SnapshotStateList<Event> = sampleEventData.filter {
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val currentDateTimestamp = defaultFormat.format(date)
        val formattedCurrentDateString = formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
        val formattedEventStartString = formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
        formattedCurrentDateString == formattedEventStartString
    }.toMutableStateList()

    suspend fun getEventsByDateAndType(date: Date, eventType: EventType): List<Event> {
        val response = eventsApi.getAllEvents()
        return response.filter {
            val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
            val currentDateTimestamp = defaultFormat.format(date)
            val formattedCurrentDateString = formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
            val formattedEventStartString = formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
            eventsDataSource.insertEvents(formattedCurrentDateString.toString(), response)
            formattedCurrentDateString == formattedEventStartString && it.eventType == eventType
        }
    }

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