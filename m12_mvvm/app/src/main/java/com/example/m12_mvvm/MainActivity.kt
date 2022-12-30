package com.example.m12_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.m12_mvvm.ui.theme.M12_mvvmTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            M12_mvvmTheme {
                MainScreen(viewModel)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    var textString by remember { mutableStateOf("") }
    val screenState by viewModel.state.collectAsState()
    var textStringResult by remember { mutableStateOf("Здесь будет отображаться результат запроса") }
    Scaffold(content = { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = textString,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Значение") },
                onValueChange = { textString = it })
            Spacer(modifier = Modifier.size(10.dp))
            Button(modifier = Modifier.fillMaxWidth(), enabled = textString.length > 3, onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.searchButton(textString).collect { textStringResult = it }
                }
            }) {
                Text(text = "Результат")
            }
            Spacer(modifier = Modifier.size(10.dp))


            when (screenState) {
                State.Success -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Text(
                                text = textStringResult,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.LightGray)
                                    .padding(8.dp)
                            )
                        }
                    }
                }

                State.Loading -> {
                    LinearProgressIndicator()
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
        }
    })
}


@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(viewModel = MainViewModel())
}