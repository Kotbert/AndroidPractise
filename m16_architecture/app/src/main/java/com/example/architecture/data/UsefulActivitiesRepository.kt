package com.example.architecture.data

import com.example.architecture.entity.UsefulActivityDto

class UsefulActivitiesRepository(private val retrofit: RetrofitInstance) {
    fun getUsefulActivity(type: String): UsefulActivityDto? {
        return retrofit.searchUsefulActivity.getUsefulActivity(type)
            .execute().body()
    }
}