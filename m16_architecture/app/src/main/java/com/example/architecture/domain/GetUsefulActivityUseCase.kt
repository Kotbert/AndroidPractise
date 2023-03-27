package com.example.architecture.domain

import com.example.architecture.data.UsefulActivitiesRepository
import com.example.architecture.entity.UsefulActivityDto

class GetUsefulActivityUseCase(private val UsefulActivitiesRepository: UsefulActivitiesRepository) {

    suspend fun execute(
        type: String
    ): UsefulActivityDto? {
        return UsefulActivitiesRepository.getUsefulActivity(type)
    }
}