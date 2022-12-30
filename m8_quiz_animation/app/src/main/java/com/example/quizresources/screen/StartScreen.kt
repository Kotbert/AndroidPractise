package com.example.quizresources.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quizresources.R
import com.example.quizresources.navigation.QuizStorageNavRoute
import com.example.quizresources.ui.theme.opensans

@Composable
fun StartScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(id = R.drawable.welcome_picture),
                contentDescription = "")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Стартовая картинка", fontFamily = opensans, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = { navController.navigate(route = QuizStorageNavRoute.Question1.route) }) {
                Text(text = "Начать опрос")
            }
        }
    }
}