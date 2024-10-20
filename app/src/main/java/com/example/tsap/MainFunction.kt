package com.example.tsap

import FavouriteScreen
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainFunction(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navItemList = listOf(
        NaviItem(label = "Home", icon = Icons.Default.Home, screen = Screen.Home),
        NaviItem(label = "Players", icon = Icons.Default.Person, screen = Screen.Player),
        NaviItem(label = "Favourites", icon = Icons.Default.Star, screen = Screen.Favourites)
    )
    var selectedIndex by remember{ mutableStateOf(0) }
    val favPlayerImages = remember{ mutableStateOf(mutableListOf<Int>()) }
    val favPlayerDescriptions = remember{ mutableStateOf(mutableListOf<String>()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                containerColor = Color(255, 114, 43)
            ) {
                NavigationBar(
                    containerColor = Color(255, 114, 43)
                ) {
                    navItemList.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon ={ Icon(item.icon, contentDescription = null) },
                            label ={ Text(item.label) },
                            selected = selectedIndex == index,
                            onClick ={
                                selectedIndex = index
                                if (navController.currentDestination?.route != item.screen.route) {
                                    navController.navigate(item.screen.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier
        ) {
            composable(Screen.Home.route){ HomeScreen(navController) }
            composable(Screen.Player.route){
                PlayerScreen(navController, favPlayerImages.value, favPlayerDescriptions.value)
            }
            composable(
                route = Screen.Achievements.route,
                arguments = listOf(
                    navArgument("imageRes"){ type = NavType.IntType },
                    navArgument("achievement"){ type = NavType.StringType }
                )
            ) { backStackEntry ->
                val image = backStackEntry.arguments?.getInt("imageRes") ?: R.drawable.ic_launcher_foreground
                val achievement = backStackEntry.arguments?.getString("achievement") ?: "No achievement available."
                AchievementScreen(navController, image, achievement)
            }
            composable(Screen.Favourites.route){
                FavouriteScreen(navController, favPlayerImages.value, favPlayerDescriptions.value)
            }
        }
    }
}