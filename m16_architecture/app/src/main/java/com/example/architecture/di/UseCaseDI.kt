package com.example.architecture.di

import com.example.architecture.domain.GetUsefulActivityUseCase
import org.koin.dsl.module

val useCase = module {
    single {
        GetUsefulActivityUseCase(UsefulActivitiesRepository = get())
    }
}