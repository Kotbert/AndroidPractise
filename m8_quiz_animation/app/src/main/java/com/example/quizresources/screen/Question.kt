package com.example.quizresources.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    route: String
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions.first()) }
    var visible by remember { mutableStateOf(false) }
    Scaffold(topBar = { TopBar(navController, "Вопрос $questionNumber") }, bottomBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { visible = !visible }) {
                Text(
                    text = if (visible) {
                        "Скрыть"
                    } else {
                        "Показать"
                    }
                )
            }
        }
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = question)
            Spacer(modifier = Modifier.size(8.dp))
            AnimatedVisibility(
                visible = visible, enter = fadeIn(
                    initialAlpha = 0.0f
                ), exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Column {
                    radioOptions.forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .selectable(selected = (text == selectedOption), onClick = {
                                    onOptionSelected(text)
                                })
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) })
                            Text(text = text)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = {
                navController.navigate(route = route)
                QuizStorageNavRoute.ResultsQuestions.answers.add(
                    questionNumber - 1, selectedOption
                )
            }) {
                Text(text = "Следующий вопрос")
            }
        }
    })
}