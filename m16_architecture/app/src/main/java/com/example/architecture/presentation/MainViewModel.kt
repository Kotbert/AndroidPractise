package com.example.architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecture.domain.GetUsefulActivityUseCase
import com.example.architecture.entity.UsefulActivityDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val GetUsefulActivityUseCase: GetUsefulActivityUseCase) : ViewModel() {

    data class UiData(
        var load: Boolean = false, var data: UsefulActivityDto? = null, val error: String? = null
    )

    private val _uiData = MutableStateFlow(UiData())
    val uiData get() = _uiData.asStateFlow()

    fun reloadUsefulActivity() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiData.value = _uiData.value.copy(load = true)
            _uiData.value = _uiData.value.copy(data = GetUsefulActivityUseCase.execute("recreational"))
            delay(3000)
            _uiData.value = _uiData.value.copy(load = false)
        }
    }
}