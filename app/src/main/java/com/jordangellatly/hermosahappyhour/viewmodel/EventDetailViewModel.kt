package com.jordangellatly.hermosahappyhour.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordangellatly.hermosahappyhour.MainDestinations.EVENT_ID_KEY
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val eventRepository: EventRepository,
//    @Assisted private val eventId: UUID,
//    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<EventDetailUiState>(EventDetailUiState.Empty)
    val uiState: StateFlow<EventDetailUiState> = _uiState

    private val eventId: UUID = UUID.fromString(checkNotNull(savedStateHandle[EVENT_ID_KEY]))

    init {
        getEventById(eventId)
    }

    fun getEventById(eventId: UUID) {
        _uiState.value = EventDetailUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = eventRepository.getEventById(eventId)
                if (response == null) {
                    _uiState.value = EventDetailUiState.Empty
                } else {
                    _uiState.value = EventDetailUiState.Loaded(response)
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
        class Loaded(val event: Event) : EventDetailUiState()
        class Error(val message: String) : EventDetailUiState()
    }

//    @AssistedFactory
//    interface EventDetailViewModelFactory {
//        fun create(eventId: UUID): EventDetailViewModel
//    }
//
//    companion object {
//        fun providesFactory(
//            assistedFactory: EventDetailViewModelFactory,
//            eventId: UUID
//        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return assistedFactory.create(eventId) as T
//            }
//        }
//    }
}

//class EventDetailViewModelFactory(private val eventId: UUID) : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return EventDetailViewModel(eventId) as T
//    }
//}