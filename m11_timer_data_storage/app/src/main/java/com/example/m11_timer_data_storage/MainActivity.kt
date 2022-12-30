package com.example.m11_timer_data_storage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.m11_timer_data_storage.ui.theme.M11_timer_data_storageTheme

const val PREFERENCE_NAME = "pref_name"
lateinit var repository: Repository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = Repository(preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE))
        setContent {
            M11_timer_data_storageTheme() {
                MaiScreen(repository.getText())
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaiScreen(titleText:String, modifier: Modifier = Modifier) {
    var someText by remember { mutableStateOf(titleText) }
    var tempSomeText by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 24.dp, end = 24.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Color(192, 192, 192, 65))
                .padding(8.dp),
            maxLines = 5,
            text = someText,
            overflow = TextOverflow.Ellipsis

        )
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        OutlinedTextField(
            value = tempSomeText,
            onValueChange = { tempSomeText = it },
            placeholder = { Text(text = "Вводите") },
            label = { Text(text = "Вводимый текст") },
            singleLine = true
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Button(onClick = {
                    repository.saveText(tempSomeText)
                }) {
                    Text(text = "Ввод")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Button(onClick = {
                    someText = repository.getText()
                }) {
                    Text(text = "Вывод")
                }
            }
            Row {
                Button(onClick = { repository.clearText() }) {
                    Text(text = "Отчистить")
                }
                Button(onClick = { someText = repository.getDataFromLocalVariable() }) {
                    Text(text = "Вывод 2")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    M11_timer_data_storageTheme() {
        MaiScreen("Он будет тут")
    }
}