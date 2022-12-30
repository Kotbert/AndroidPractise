package com.example.quizresources

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quizresources.navigation.SetupNavGraph
import com.example.quizresources.ui.theme.QuizResourcesTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizResourcesTheme {

                navController = rememberNavController()
                SetupNavGraph(navController = navController)

            }
        }
    }
}