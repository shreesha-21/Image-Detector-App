package com.example.imagedetector.data

import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ObjectDetectionApi {
    @Multipart
    @POST("detect") // Replace with your actual endpoint
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): DetectionResponse
}

// 3. Retrofit Instance
object RetrofitClient {
    val api: ObjectDetectionApi by lazy {
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ObjectDetectionApi::class.java)
    }
}