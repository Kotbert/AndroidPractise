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
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizresources.R
import com.example.quizresources.TopBar
import com.example.quizresources.navigation.QuizStorageNavRoute

@Preview
@Composable
fun ResultsQuestions(navController: NavController = rememberNavController()) {
    var visible by remember { mutableStateOf(false) }
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.some_video))
    Scaffold(topBar = { TopBar(navController, "Результаты") }, bottomBar = {
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
        LottieAnimation(modifier = Modifier.fillMaxSize().scale(2f), composition = composition, iterations = LottieConstants.IterateForever)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "Результаты")
            Spacer(modifier = Modifier.size(8.dp))
            QuizStorageNavRoute.ResultsQuestions.answers.forEach { answer ->
                AnimatedVisibility(
                    visible = visible, enter = fadeIn(
                        initialAlpha = 0.0f
                    ), exit = slideOutVertically() + shrinkVertically() + fadeOut()
                ) { Text(text = answer) }
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
            Spacer(modifier = Modifier.size(125.dp))
        }
    })

}