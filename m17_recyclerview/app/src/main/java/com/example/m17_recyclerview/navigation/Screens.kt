package com.example.m17_recyclerview.navigation

sealed class Screens(val route: String) {
    object PhotosListScreen:Screens("List")
    object PhotosDetailScreen:Screens("Detail/{imageSrc}")
}