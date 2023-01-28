package com.jordangellatly.hermosahappyhour.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Filter
import com.jordangellatly.hermosahappyhour.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val eventRepository: EventRepository) : ViewModel() {

    private var _events = MutableLiveData<SnapshotStateList<Event>>()
    val events: LiveData<SnapshotStateList<Event>>
        get() = _events

    fun getAllEventsByDate(date: Date): SnapshotStateList<Event> {
        return eventRepository.getAllEventsByDate(date)
    }

    fun getEventsByDateAndType(date: Date, eventType: EventType) {
        viewModelScope.launch {
            _events.postValue(eventRepository.getEventsByDateAndType(date, eventType))
        }
    }

    fun getEventById(eventId: UUID): Event {
        return eventRepository.getEventById(eventId)
    }

    fun getFilters(): SnapshotStateList<Filter> {
        return eventRepository.getFilters()
    }
}