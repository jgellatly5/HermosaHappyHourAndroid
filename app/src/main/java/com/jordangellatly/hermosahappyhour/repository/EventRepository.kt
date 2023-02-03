package com.jordangellatly.hermosahappyhour.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.jordangellatly.hermosahappyhour.api.EventsService
import com.jordangellatly.hermosahappyhour.model.*
import java.util.*
import javax.inject.Inject

class EventRepository @Inject constructor(private val eventsService: EventsService) {

    private val eventsDataSource: EventsDataSource = EventsCache()

    suspend fun getAllEvents(): List<Event> = eventsService.getAllEvents()

    suspend fun getEventById(eventId: UUID): Event? = eventsService.getEventById(eventId)

    fun getFilters(): SnapshotStateList<Filter> = filters
}