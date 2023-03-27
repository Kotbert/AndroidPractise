package com.example.m17_recyclerview.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.m17_recyclerview.MainScreen
import com.example.m17_recyclerview.ui.FullScreenPhoto
import com.example.m17_recyclerview.ui.MainViewModel

@Composable
fun NavGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Screens.PhotosListScreen.route) {
        composable(Screens.PhotosListScreen.route) {
            MainScreen(viewModel = mainViewModel, navController = navController)
        }
        composable(route = Screens.PhotosDetailScreen.route, arguments = listOf(navArgument("imageSrc"){
            type = NavType.IntType
        })) {
            FullScreenPhoto(viewModel = mainViewModel, index = it.arguments!!.getInt("imageSrc"))
        }
    }
}