package com.example.m17_recyclerview.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "camera")
    val camera: Camera?,
    @Json(name = "earth_date")
    val earthDate: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "img_src")
    val imgSrc: String?,
    @Json(name = "rover")
    val rover: Rover?,
    @Json(name = "sol")
    val sol: Int?
)