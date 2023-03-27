package com.example.m15_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.m15_room.ui.theme.M15_roomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()
        setContent {
            M15_roomTheme {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val listDictionary by viewModel.allDictionary.collectAsState()
    var textInput by remember { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = textInput, onValueChange = { textInput = it })
        Spacer(modifier = Modifier.size(4.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { viewModel.onAddBtn(textInput) }) {
                Text(text = "Добавить")
            }
            Spacer(modifier = Modifier.size(4.dp))
            Button(onClick = { viewModel.onDeleteBtn() }) {
                Text(text = "Удалить")
            }
        }
        Spacer(modifier = Modifier.size(4.dp))
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(listDictionary) { item ->
                Text(text = item.toString())
            }
        }
    }
}