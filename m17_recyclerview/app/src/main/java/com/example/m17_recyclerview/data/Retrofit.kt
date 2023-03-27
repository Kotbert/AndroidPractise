package com.example.m17_recyclerview.data

import com.example.m17_recyclerview.dto.MarsRoverPhoto
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object Retrofit {
    private const val BASE_URL = "https://api.nasa.gov"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchMarsPhotosList: GetMarsPhotosList =
        retrofit.create(GetMarsPhotosList::class.java)
}

interface GetMarsPhotosList {
    @GET("/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY")
    fun getMarsPhotosList(): Call<MarsRoverPhoto>
}
