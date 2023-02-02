package com.jordangellatly.hermosahappyhour.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.repository.EventRepository
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<EventsUiState>(EventsUiState.Empty)
    val uiState: StateFlow<EventsUiState> = _uiState

    private var _event = MutableLiveData<Event>()
    val event: LiveData<Event>
        get() = _event

    init {
        printEventsJson()
        printRestaurantsJson()
        getEventsByDateAndType(date = getCurrentDateTime(), eventType = EventType.HappyHour)
    }

    fun getEventsByDate(date: Date) {
        _uiState.value = EventsUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = eventRepository.getAllEvents().filter {
                    val defaultFormat =
                        SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
                    val currentDateTimestamp = defaultFormat.format(date)
                    val formattedCurrentDateString =
                        formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
                    val formattedEventStartString =
                        formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
                    formattedCurrentDateString == formattedEventStartString
                }
                if (response.isEmpty()) {
                    _uiState.value = EventsUiState.Empty
                } else {
                    _uiState.value = EventsUiState.Loaded(response)
                }
            } catch (e: Exception) {
                onErrorOccurred()
            }
        }
    }

    fun getEventsByDateAndType(date: Date, eventType: EventType) {
        _uiState.value = EventsUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = eventRepository.getAllEvents().filter {
                    val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
                    val currentDateTimestamp = defaultFormat.format(date)
                    val formattedCurrentDateString =
                        formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
                    val formattedEventStartString = formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
                    formattedCurrentDateString == formattedEventStartString && it.eventType == eventType
                }
                if (response.isEmpty()) {
                    _uiState.value = EventsUiState.Empty
                } else {
                    _uiState.value = EventsUiState.Loaded(response)
                }
            } catch (e: Exception) {
                onErrorOccurred()
            }
        }
    }

    fun getEventById(eventId: UUID) = eventRepository.getEventById(eventId)

    fun getFilters(): SnapshotStateList<Filter> {
        return eventRepository.getFilters()
    }

    private fun onErrorOccurred() {
        _uiState.value = EventsUiState.Error("An error has occurred.")
    }

    sealed class EventsUiState {
        object Empty : EventsUiState()
        object Loading : EventsUiState()
        class Loaded(val events: List<Event>) : EventsUiState()
        class Error(val message: String) : EventsUiState()
    }
}