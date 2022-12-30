package com.example.quizresources.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quizresources.TopBar
import com.example.quizresources.navigation.QuizStorageNavRoute

@Composable
fun ResultsQuestions(navController: NavController) {
    Scaffold(topBar = { TopBar(navController, "Вопрос 3") }, content = { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Результаты")
            Spacer(modifier = Modifier.size(8.dp))
            QuizStorageNavRoute.ResultsQuestions.answers.forEach { answer ->
                Text(text = answer)
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = {
                navController.navigate(route = QuizStorageNavRoute.StartScreen.route) {
                    popUpTo(QuizStorageNavRoute.StartScreen.route) {
                        inclusive = true
                    }
                }
            }) {
                Text(text = "На главную")
            }
        }
    })

}