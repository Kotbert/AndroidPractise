package com.example.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cat(
    @Json(name = "id")val id:Int,
    @Json(name = "url")val url: String,
)