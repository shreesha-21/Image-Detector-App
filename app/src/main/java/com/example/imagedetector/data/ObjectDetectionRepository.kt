package com.example.imagedetector.data

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class ObjectDetectionRepository {
    private val api: ObjectDetectionApi by lazy {
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ObjectDetectionApi::class.java)
    }

    suspend fun uploadImage(file: File): Result<DetectionResponse> {
        return try {
            val requestFile = file.asRequestBody(contentType = "image/jpeg".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("image", file.name, requestFile)
            val response: DetectionResponse = api.detectObjects(body)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}