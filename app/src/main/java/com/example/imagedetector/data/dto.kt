package com.example.imagedetector.data

data class DetectionResponse(
    val detections: List<DetectionObject>
)

data class DetectionObject(
    val label: String,
    val confidence: Float,
    val box: BoundingBox // The server returns [x_min, y_min, x_max, y_max]
)

data class BoundingBox(
    val x1: Float,
    val y1: Float,
    val x2: Float,
    val y2: Float
)
