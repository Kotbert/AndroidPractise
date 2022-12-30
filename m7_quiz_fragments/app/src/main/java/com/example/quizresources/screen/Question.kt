package com.example.quizresources.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quizresources.TopBar
import com.example.quizresources.navigation.QuizStorageNavRoute

@Composable
fun Question(
    navController: NavController,
    questionNumber: Int,
    question: String,
    radioOptions: List<String>,
    route: String,
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions.first()) }
    Scaffold(topBar = { TopBar(navController, "Вопрос 1") }, content = { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = question)
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                radioOptions.forEach { text ->
                    Row(Modifier
                        .fillMaxWidth()
                        .selectable(selected = (text == selectedOption), onClick = {
                            onOptionSelected(text)
                        })
                        .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) })
                        Text(text = text)
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = {
                navController.navigate(route = route)
                QuizStorageNavRoute.ResultsQuestions.answers.add(questionNumber - 1, selectedOption)
            }) {
                Text(text = "Следующий вопрос")
            }
        }
    })
}