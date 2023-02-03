package com.jordangellatly.hermosahappyhour.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.repository.HappyHourRepository
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
class EventFeedViewModel @Inject constructor(
    private val happyHourRepository: HappyHourRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<EventFeedUiState>(EventFeedUiState.Empty)
    val uiState: StateFlow<EventFeedUiState> = _uiState

    init {
        printEventsJson()
        printRestaurantsJson()
        getEventsByDateAndType(date = getCurrentDateTime(), eventType = EventType.HappyHour)
    }

    fun getEventsByDate(date: Date) {
        _uiState.value = EventFeedUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = happyHourRepository.getAllEvents().filter {
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
                    _uiState.value = EventFeedUiState.Empty
                } else {
                    _uiState.value = EventFeedUiState.Loaded(response)
                }
            } catch (e: Exception) {
                onErrorOccurred()
            }
        }
    }

    fun getEventsByDateAndType(date: Date, eventType: EventType) {
        _uiState.value = EventFeedUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = happyHourRepository.getAllEvents().filter {
                    val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
                    val currentDateTimestamp = defaultFormat.format(date)
                    val formattedCurrentDateString =
                        formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
                    val formattedEventStartString = formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
                    formattedCurrentDateString == formattedEventStartString && it.eventType == eventType
                }
                if (response.isEmpty()) {
                    _uiState.value = EventFeedUiState.Empty
                } else {
                    _uiState.value = EventFeedUiState.Loaded(response)
                }
            } catch (e: Exception) {
                onErrorOccurred()
            }
        }
    }

    fun getFilters(): SnapshotStateList<Filter> {
        return happyHourRepository.getFilters()
    }

    private fun onErrorOccurred() {
        _uiState.value = EventFeedUiState.Error("An error has occurred.")
    }

    sealed class EventFeedUiState {
        object Empty : EventFeedUiState()
        object Loading : EventFeedUiState()
        class Loaded(val events: List<Event>) : EventFeedUiState()
        class Error(val message: String) : EventFeedUiState()
    }
}