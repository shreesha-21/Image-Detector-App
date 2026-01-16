package com.example.imagedetector.ui.components

import android.graphics.BitmapFactory.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import com.example.imagedetector.data.DetectionResponse
import java.io.File

//  Composable which shows the result from the server by overlaying a box on the captured image
@Composable
fun ResultView(
    file: File,
    detectionResponse: DetectionResponse,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bitmap = remember(file) { decodeFile(file.absolutePath) }

    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            val scaleX = size.width / bitmap.width
            val scaleY = scaleX

            detectionResponse.detections.forEach { obj ->
                // Extract coordinates from the list
                // The server sends [x1, y1, x2, y2]
                val x1 = obj.box[0]
                val y1 = obj.box[1]
                val x2 = obj.box[2]
                val y2 = obj.box[3]

                // Apply scaling (Screen Size / Image Size)
                val left = x1 * scaleX
                val top = y1 * scaleY
                val right = x2 * scaleX
                val bottom = y2 * scaleY

                drawRect(
                    color = Color.Red,
                    topLeft = Offset(left, top),
                    size = Size(right - left, bottom - top),
                    style = Stroke(width = 8f)
                )
            }
        }
    }
}