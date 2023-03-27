package com.example.m17_recyclerview.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rover(
    @Json(name = "id")
    val id: Int,
    @Json(name = "landing_date")
    val landingDate: String,
    @Json(name = "launch_date")
    val launchDate: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "status")
    val status: String
)