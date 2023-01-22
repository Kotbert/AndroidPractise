package com.example.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val BASE_URL = "http://api.thecatapi.com"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchImageAPI: SearchImageApi = retrofit.create(SearchImageApi::class.java)

}

interface SearchImageApi {
    @Headers(
        "Accept: application/json",
        "Content-type: application/json"
    )
    @GET("/v1/images/search")
    fun getCatImage(@Query("limit") limit: Int = 1): Call<List<Cat>>
}