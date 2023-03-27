package com.example.architecture.di

import com.example.architecture.data.RetrofitInstance
import com.example.architecture.data.UsefulActivitiesRepository
import org.koin.dsl.module


val dataDI = module {
    single {
        UsefulActivitiesRepository(retrofit = get())
    }

    factory {
        RetrofitInstance
    }
}