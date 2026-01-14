package com.example.imagedetector.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagedetector.data.ObjectDetectionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

//  View model for the home screen
class HomeScreenViewModel: ViewModel() {

    // Simple manual injection for brevity
    private val repository = ObjectDetectionRepository()

    // Backing property for state
    private val _homeScreenUiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Camera)
    val homeScreenUiState: StateFlow<HomeScreenUiState> = _homeScreenUiState.asStateFlow()

    fun processImage(file: File) {
        _homeScreenUiState.value = HomeScreenUiState.Loading

        viewModelScope.launch {
            val result = repository.uploadImage(file)
            result.onSuccess { detections ->
                _homeScreenUiState.value = HomeScreenUiState.Success(file, detections)
            }.onFailure { error ->
                _homeScreenUiState.value = HomeScreenUiState.Error(error.localizedMessage ?: "Unknown Error")
            }
        }
    }

    fun resetToCamera() {
        _homeScreenUiState.value = HomeScreenUiState.Camera
    }

}