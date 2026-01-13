package com.example.imagedetector.ui.components

import android.provider.Settings.Global.getString
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.imagedetector.R


@Composable
fun CameraScreen(

) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Default.Camera,
                    contentDescription = stringResource(R.string.camera_button_description)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Modifier.padding(paddingValues)
    }
}


@Preview
@Composable
fun CameraScreenPreview() {
    CameraScreen()
}