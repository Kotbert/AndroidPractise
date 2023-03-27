package com.example.retrofit.userData

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserData(
    @Json(name = "info") val info: Info,
    @Json(name = "results") val results: List<Result>
)