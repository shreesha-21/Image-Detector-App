package com.example.imagedetector.ui.components

import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.imagedetector.R
import java.io.File


//  Composable showing the live camera field
@Composable
fun CameraView(onImageCaptured: (File) -> Unit) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val controller = remember { LifecycleCameraController(context) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                PreviewView(it).apply {
                    this.controller = controller
                    controller.bindToLifecycle(lifecycleOwner)
                }
            }
        )
        Button(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(all = dimensionResource(id = R.dimen.button_padding)),
            onClick = {
                val file = File.createTempFile("scan", ".jpg", context.cacheDir)
                val options = ImageCapture.OutputFileOptions.Builder(file).build()

                controller.takePicture(
                    options,
                    ContextCompat.getMainExecutor(context),
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(res: ImageCapture.OutputFileResults) {
                            onImageCaptured(file)
                        }
                        override fun onError(exception: ImageCaptureException) {}
                    }
                )
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Camera,
                contentDescription = stringResource(R.string.camera_button_description)
            )
        }

    }

}