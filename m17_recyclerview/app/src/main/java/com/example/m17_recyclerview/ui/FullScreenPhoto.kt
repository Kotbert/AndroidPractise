package com.example.m17_recyclerview.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun FullScreenPhoto(viewModel: MainViewModel, index: Int = 0) {
    val image by viewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = image.marsRoverPhoto?.photos?.get(index)?.imgSrc,
            contentDescription = null
        )
    }
}