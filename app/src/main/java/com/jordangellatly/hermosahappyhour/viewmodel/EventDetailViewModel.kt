package com.jordangellatly.hermosahappyhour.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordangellatly.hermosahappyhour.MainDestinations.EVENT_ID_KEY
import com.jordangellatly.hermosahappyhour.MainDestinations.RESTAURANT_ID_KEY
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.repository.HappyHourRepository
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
class EventDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val happyHourRepository: HappyHourRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<EventDetailUiState>(EventDetailUiState.Empty)
    val uiState: StateFlow<EventDetailUiState> = _uiState

    private val eventId: UUID = UUID.fromString(checkNotNull(savedStateHandle[EVENT_ID_KEY]))
    private val restaurantId: UUID = UUID.fromString(checkNotNull(savedStateHandle[RESTAURANT_ID_KEY]))

    init {
        getEventDetails(eventId, restaurantId)
    }

    private fun getEventDetails(eventId: UUID, restaurantId: UUID) {
        _uiState.value = EventDetailUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val mainEvent = happyHourRepository.getEventById(eventId)
                val restaurant = happyHourRepository.getRestaurantById(restaurantId)
                val date = getCurrentDateTime()
                val defaultFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDateTimestamp = defaultFormat.format(date)
                val eventList = restaurant?.eventsByDate
                    ?.getValue(formattedDateTimestamp)
                    ?.map { it.value }
                    ?.sortedBy { if (it == mainEvent) 0 else 1 }
                if (mainEvent == null || restaurant == null || eventList == null) {
                    onErrorOccurred()
                } else {
                    _uiState.value = EventDetailUiState.Loaded(mainEvent, restaurant, eventList)
                }
            } catch (e: Exception) {
                onErrorOccurred()
            }
        }
    }

    private fun onErrorOccurred() {
        _uiState.value = EventDetailUiState.Error("An error has occurred.")
    }

    sealed class EventDetailUiState {
        object Empty : EventDetailUiState()
        object Loading : EventDetailUiState()
        class Loaded(val event: Event, val restaurant: Restaurant, val eventList: List<Event>) : EventDetailUiState()
        class Error(val message: String) : EventDetailUiState()
    }
}