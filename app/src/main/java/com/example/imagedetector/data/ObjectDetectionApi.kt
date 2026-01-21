package com.example.imagedetector.data

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ObjectDetectionApi {
    @Multipart
    @POST("predict") // Replace with your actual endpoint
    suspend fun detectObjects(
        @Part image: MultipartBody.Part
    ): DetectionResponse
}
