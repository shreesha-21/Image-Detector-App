package com.example.imagedetector.data

import com.google.gson.annotations.SerializedName

data class DetectionResponse(
    val detections: List<DetectionObject>
)

//  Data class to be used as blueprint to convert json response from the server into kotlin object
data class DetectionObject(
    val label: String,
    val confidence: Float,
    @SerializedName("bbox") val box: List<Float>
)

