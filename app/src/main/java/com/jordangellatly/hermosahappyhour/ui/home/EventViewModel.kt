package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Filter
import com.jordangellatly.hermosahappyhour.ui.detail.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val eventRepository: EventRepository) : ViewModel() {

    fun getAllEventsByDate(date: Date): SnapshotStateList<Event> {
        return eventRepository.getAllEventsByDate(date)
    }

    fun getEventsByDateAndType(date: Date, eventType: EventType): SnapshotStateList<Event> {
        return eventRepository.getEventsByDateAndType(date, eventType)
    }

    fun getEventById(eventId: UUID): Event {
        return eventRepository.getEventById(eventId)
    }

    fun getFilters(): SnapshotStateList<Filter> {
        return eventRepository.getFilters()
    }
}