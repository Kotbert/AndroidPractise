package com.example.quizresources

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.quizresources.navigation.SetupNavGraph
import com.example.quizresources.ui.theme.QuizResourcesTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            QuizResourcesTheme {
                SetupNavGraph(navController = navController)
            }
        }
    }
}