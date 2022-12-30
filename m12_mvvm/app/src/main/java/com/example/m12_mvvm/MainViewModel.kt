package com.example.m12_mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

class MainViewModel : ViewModel() {
    private var _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val listSomeText = listOf(
        "Greetings",
        "Двигатель",
        "Космос",
        "Katt",
        "Разный текст который можно сюда написать"
    )

    fun searchButton(searchText: String): Flow<String> = flow {
        _state.value = State.Loading
        var someText = "Запрос не дал результатов"
        listSomeText.forEach { text ->
            if (text.lowercase().startsWith(searchText.lowercase())) someText = text
            delay(1_000)
            emit(someText)
        }
        _state.value = State.Success
    }
}
