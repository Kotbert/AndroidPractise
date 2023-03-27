package com.example.retrofit

import com.example.retrofit.userData.UserData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://randomuser.me/"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchUserAPI: SearchUserApi = retrofit.create(SearchUserApi::class.java)
}

interface SearchUserApi {
    @GET("api/")
    fun getUserData(): Call<UserData>
}