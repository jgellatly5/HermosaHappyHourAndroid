package com.jordangellatly.hermosahappyhour.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.repository.HappyHourRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFeedViewModel @Inject constructor(
    private val happyHourRepository: HappyHourRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchFeedUiState>(SearchFeedUiState.Empty)
    val uiState: StateFlow<SearchFeedUiState> = _uiState

    init {
        getAllRestaurants()
    }

    private fun getAllRestaurants() {
        _uiState.value = SearchFeedUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = happyHourRepository.getAllRestaurants()
                if (response.isEmpty()) {
                    _uiState.value = SearchFeedUiState.Empty
                } else {
                    _uiState.value = SearchFeedUiState.Loaded(response)
                }
            } catch (e: Exception) {
                onErrorOccurred()
            }
        }
    }

    private fun onErrorOccurred() {
        _uiState.value = SearchFeedUiState.Error("An error has occurred.")
    }

    sealed class SearchFeedUiState {
        object Empty : SearchFeedUiState()
        object Loading : SearchFeedUiState()
        class Loaded(val restaurants: List<Restaurant>) : SearchFeedUiState()
        class Error(val message: String) : SearchFeedUiState()
    }
}