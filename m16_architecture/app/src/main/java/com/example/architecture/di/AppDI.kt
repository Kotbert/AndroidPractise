package com.example.architecture.di

import com.example.architecture.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        MainViewModel(GetUsefulActivityUseCase = get())
    }
}