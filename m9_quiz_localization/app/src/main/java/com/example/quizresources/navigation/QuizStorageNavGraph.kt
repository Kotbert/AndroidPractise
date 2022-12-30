package com.example.quizresources.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.quizresources.R
import com.example.quizresources.screen.Question
import com.example.quizresources.screen.QuestionLast
import com.example.quizresources.screen.ResultsQuestions
import com.example.quizresources.screen.StartScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    val tweenSpeed = 1000
    AnimatedNavHost(navController = navController,
        startDestination = QuizStorageNavRoute.StartScreen.route,
        enterTransition = {
            slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Start, tween(tweenSpeed))
        },
        exitTransition = {
            slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Left, tween(tweenSpeed))
        },
        popEnterTransition = {
            slideIntoContainer(towards = AnimatedContentScope.SlideDirection.End, tween(tweenSpeed))
        },
        popExitTransition = {
            slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Left, tween(tweenSpeed))
        }) {
        composable(route = QuizStorageNavRoute.StartScreen.route) {
            StartScreen(navController = navController)
        }
        composable(route = QuizStorageNavRoute.Question1.route) {
            Question(navController = navController,
                questionNumber = 1,
                question = stringResource(R.string.QUIZ1),
                radioOptions = stringArrayResource(id = R.array.QUIZ1arr).toList(),
                route = QuizStorageNavRoute.Question2.route)
        }
        composable(route = QuizStorageNavRoute.Question2.route) {
            Question(navController = navController,
                questionNumber = 2,
                question = stringResource(R.string.QUIZ2),
                radioOptions = stringArrayResource(id = R.array.QUIZ2arr).toList(),
                route = QuizStorageNavRoute.Question3.route)
        }
        composable(route = QuizStorageNavRoute.Question3.route) {
            QuestionLast(navController = navController,
                questionNumber = 3,
                question = stringResource(R.string.QUIZ3),
                radioOptions = stringArrayResource(id = R.array.QUIZ3arr).toList())
        }
        composable(route = QuizStorageNavRoute.ResultsQuestions.route) {
            ResultsQuestions(navController = navController)
        }
    }
}