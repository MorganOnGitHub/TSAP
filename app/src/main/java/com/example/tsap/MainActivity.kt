package com.example.tsap


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tsap.ui.theme.TSAPTheme


sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object PlayerList : Screen("PlayerList")
    object PlayerDetails : Screen("PlayerDetails")
    object TeamAchievements : Screen("TeamAchievements")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TSAPTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.PlayerList.route) { PlayerListScreen(navController) }
        composable(Screen.PlayerDetails.route) { PlayerDetailScreen(navController) }
        composable(Screen.TeamAchievements.route) { TeamAchievementScreen(navController) }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    LazyColumn(Modifier.padding(16.dp).fillMaxWidth()) {
        item {
            Text(text = "Select a Page", modifier = Modifier.padding(bottom = 16.dp))
        }
        item {
            Button(
                onClick = { navController.navigate(Screen.PlayerList.route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(text = "Player List")
            }
        }
        item {
            Button(
                onClick = { navController.navigate(Screen.PlayerDetails.route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(text = "Player Details")
            }
        }
        item {
            Button(
                onClick = { navController.navigate(Screen.TeamAchievements.route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(text = "Team Achievements")
            }
        }
        // Add more buttons as needed
    }
}

@Composable
fun PlayerListScreen(navController: NavHostController) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.padding(16.dp).fillMaxWidth()) {
            items(10) { index ->
                Text(text = "Player Item #$index", modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun PlayerDetailScreen(navController: NavHostController) {
    Box(Modifier.fillMaxSize()) {
        Text(text = "Player Detail", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun TeamAchievementScreen(navController: NavHostController) {
    Box(Modifier.fillMaxSize()) {
        Text(text = "Team Achievements", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TSAPTheme {
        Greeting("Android")
    }
}
