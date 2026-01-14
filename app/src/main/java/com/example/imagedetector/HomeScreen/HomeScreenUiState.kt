package com.example.imagedetector.HomeScreen

import com.example.imagedetector.data.DetectionResponse
import java.io.File

//  Declares all possible ui states of the Home screen
sealed interface HomeScreenUiState {
    object Camera : HomeScreenUiState                  // Show Camera Preview
    object Loading : HomeScreenUiState                 // Show Spinner
    data class Success(                      // Show Result
        val imageFile: File,
        val detections: DetectionResponse
    ) : HomeScreenUiState
    data class Error(val message: String) : HomeScreenUiState // Show Error
}
