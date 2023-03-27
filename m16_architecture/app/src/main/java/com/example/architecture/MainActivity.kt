package com.example.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.architecture.presentation.MainViewModel
import com.example.architecture.ui.theme.ArchitectureTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: MainViewModel by viewModel()
        super.onCreate(savedInstanceState)
        setContent {
            ArchitectureTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val uiData by viewModel.uiData.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            uiData.load -> {
                Text(text = "Идет загрузка или нужна загрузка")
                CircularProgressIndicator()
            }

            uiData.error != null -> {
                Text(
                    text = uiData.error!!,
                    modifier = modifier
                )
            }

            uiData.data != null -> {
                Text(
                    text = uiData.data.toString(),
                    modifier = modifier
                )
            }
        }
        Button(onClick = { viewModel.reloadUsefulActivity() }) {
            Text(text = "Обновить")
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArchitectureTheme {
        Greeting()
    }
}*/
