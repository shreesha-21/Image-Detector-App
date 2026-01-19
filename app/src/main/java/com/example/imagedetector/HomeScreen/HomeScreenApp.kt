package com.example.imagedetector.HomeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imagedetector.ui.components.CameraPermissionWrapper
import com.example.imagedetector.ui.components.CameraView
import com.example.imagedetector.ui.components.PermissionRequestAlertDialog
import com.example.imagedetector.ui.components.ResultView

//  Composable to display the HomeScreen of the application
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<HomeScreenViewModel>()
    val state by viewModel.homeScreenUiState.collectAsState()

    Scaffold(modifier = modifier) { padding ->
        Box(Modifier.padding(padding)) {
            when (val currentState = state) {
                is HomeScreenUiState.Camera -> {
//                    CameraView(onImageCaptured = { file ->
//                        viewModel.processImage(file)
//                    })
                    CameraPermissionWrapper(
                        onPermissionGranted = {
                            CameraView(
                                onImageCaptured = { file ->
                                    viewModel.processImage(file)
                                }
                            )
                        },
                    )
                }
                is HomeScreenUiState.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                is HomeScreenUiState.Success -> {
                    ResultView(
                        file = currentState.imageFile,
                        detectionResponse = currentState.detections,
                        onReset = { viewModel.resetToCamera() }
                    )
                }
                is HomeScreenUiState.Error -> {
                    Text("Error: ${currentState.message}")
                    Button(onClick = { viewModel.resetToCamera() }) { Text("Retry") }
                }
            }
        }
    }
}