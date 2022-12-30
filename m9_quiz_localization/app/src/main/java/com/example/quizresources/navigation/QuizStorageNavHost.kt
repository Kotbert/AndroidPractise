package com.example.quizresources.navigation


sealed class QuizStorageNavRoute(val route: String) {
    object StartScreen : QuizStorageNavRoute(route = "startScreen_NavRoute")
    object Question1 : QuizStorageNavRoute(route = "question1_NavRoute")
    object Question2 : QuizStorageNavRoute(route = "question2_NavRoute")
    object Question3 : QuizStorageNavRoute(route = "question3_NavRoute")
    object ResultsQuestions :
        QuizStorageNavRoute(route = "resultsQuestions_NavRoute") {
            var answers = mutableListOf<String>()
    }


}