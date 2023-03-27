package com.example.m17_recyclerview.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m17_recyclerview.data.Retrofit
import com.example.m17_recyclerview.dto.MarsRoverPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    data class UiState(
        val marsRoverPhoto: MarsRoverPhoto? = null,
        val loading: Boolean = false
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        getListPhotos()
    }

    private fun getListPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(loading = true)
            delay(3000)
            _uiState.value = _uiState.value.copy(
                loading = false,
                marsRoverPhoto = Retrofit.searchMarsPhotosList.getMarsPhotosList().execute().body()
            )
            Log.d("photo", "size ${uiState.value.marsRoverPhoto?.photos?.size}")
        }
    }
}