package com.example.architecture

import android.app.Application
import com.example.architecture.di.appModule
import com.example.architecture.di.dataDI
import com.example.architecture.di.useCase
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class AppKoin : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            loadKoinModules(listOf(useCase, appModule, dataDI))
        }
    }
}