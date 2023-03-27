package com.example.architecture.data

import com.example.architecture.entity.UsefulActivityDto
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitInstance {
    const val BASE_URL = "https://www.boredapi.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchUsefulActivity: SearchUsefulActivity =
        retrofit.create(SearchUsefulActivity::class.java)
}

interface SearchUsefulActivity {
    @GET("api/activity")
    fun getUsefulActivity(@Query("type") type: String): Call<UsefulActivityDto>
}