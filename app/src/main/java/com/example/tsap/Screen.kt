package com.example.tsap

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object Player : Screen("Player")
    object Achievements : Screen("Achievements/{imageRes}/{achievement}") {
        fun createRoute(image: Int, achievement: String) = "Achievements/$image/$achievement"
    }
    object Favourites : Screen("Favourites")
}