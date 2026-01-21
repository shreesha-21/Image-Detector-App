package com.example.imagedetector.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagedetector.homeScreen.HomeScreenUiState.Success
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
                _homeScreenUiState.value = Success(file, detections)
            }.onFailure { error ->
                _homeScreenUiState.value = HomeScreenUiState.Error(error.localizedMessage ?: "Unknown Error")
            }
        }
    }

    //  Resets the screen to the initial view which shows the camera to the user
    fun resetToCamera() {

        val currentState = homeScreenUiState.value

        //  Deletes any image file that was not required anymore
        if (currentState is Success) {
            val imageFile = currentState.imageFile
            if (imageFile.exists()) {
                imageFile.delete()
            }
        }

        _homeScreenUiState.value = HomeScreenUiState.Camera
    }

}