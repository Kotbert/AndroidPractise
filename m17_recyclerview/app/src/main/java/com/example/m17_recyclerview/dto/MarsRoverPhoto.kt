package com.example.m17_recyclerview.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarsRoverPhoto(
    @Json(name = "photos")
    val photos: List<Photo> = listOf()
)