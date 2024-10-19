package com.example.act1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.act1.ui.theme.ACT1Theme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class UserRequest(val username: String, val email: String, val password: String)

@Serializable
data class UserResponse(val message: String, val userData: Map<String, Any>?)

// Function to verify user
suspend fun verifyUserClient(username: String, email: String, password: String): UserResponse? {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    return try {
        val response: UserResponse = client.post("http://127.0.0.1:5001/rd-year-project-1f41d/europe-west2/verifyUser") {
            contentType(ContentType.Application.Json)
            setBody(UserRequest(username, email, password))
        }.body()
        response
    } catch (e: Exception) {
        println("Error fetching user data: ${e.message}")
        null
    } finally {
        client.close()
    }
}

// MainActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ACT1Theme {
                var isDarkTheme by remember { mutableStateOf(false) }
                val backgroundColor = if (isDarkTheme) {
                    Brush.horizontalGradient(
                        listOf(
                            Color(7, 3, 122),
                            Color(7, 0, 10),
                            Color(8, 105, 3)
                        )
                    )
                } else {
                    Brush.horizontalGradient(
                        listOf(
                            Color(185, 5, 41),
                            Color(237, 97, 4),
                            Color(185, 5, 41)
                        )
                    )
                }
                val navController = rememberNavController()
                var selectedIndex by remember { mutableStateOf(0) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomAppBar(
                            containerColor = Color(36, 35, 49)
                        ) {
                            NavigationBar(
                                containerColor = Color(36, 35, 49)
                            ) {
                                navItemList.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                item.icon,
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                        },
                                        label = { Text(item.label, color = Color.White) },
                                        selected = selectedIndex == index,
                                        onClick = {
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
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundColor)
                            .padding(paddingValues)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Signup.route,
                            modifier = Modifier
                        ) {
                            composable(Screen.Home.route) { HomeScreen(navController, backgroundColor) }
                            composable(Screen.Premium.route) { PremiumScreen(navController, backgroundColor) }
                            composable(Screen.Support.route) { SupportScreen(navController, backgroundColor) }
                            composable(Screen.Rate.route) { RateScreen(navController, backgroundColor) }
                            composable(Screen.Signup.route) { SignUpScreen(navController, backgroundColor) }
                            composable(Screen.Login.route) { LoginScreen(navController, backgroundColor) }
                        }
                    }

                    Button(
                        onClick = { isDarkTheme = !isDarkTheme },
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(text = "SWITCH THEME")
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object Premium : Screen("Premium")
    object Support : Screen("Support")
    object Rate : Screen("Rate Us")
    object Signup : Screen("Sign Up")
    object Login : Screen("Login")
}

data class NavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val screen: Screen)

val navItemList = listOf(
    NavItem("Home", Icons.Default.Home, Screen.Home),
    NavItem("Premium", Icons.Default.Star, Screen.Premium),
    NavItem("Support", Icons.Default.Person, Screen.Support),
    NavItem("Rate Us", Icons.Default.ThumbUp, Screen.Rate)
)

@Composable
fun HomeScreen(navController: NavHostController, backgroundColor: Brush) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "HOME", color = Color.White)
        }
    }
}

@Composable
fun PremiumScreen(navController: NavHostController, backgroundColor: Brush) {
    ScreenContent(title = "Premium", navController = navController, backgroundColor)
}

@Composable
fun SupportScreen(navController: NavHostController, backgroundColor: Brush) {
    ScreenContent(title = "Support", navController = navController, backgroundColor)
}

@Composable
fun RateScreen(navController: NavHostController, backgroundColor: Brush) {
    ScreenContent(title = "Rate Us", navController = navController, backgroundColor)
}

@Composable
fun LoginScreen(navController: NavHostController, backgroundColor: Brush) {
    // State variables to hold user input
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") } // Optional: To display any error messages

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }
            item {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
            item {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Button(
                    onClick = {
                        // Launch a coroutine to call the verifyUserClient function
                        CoroutineScope(Dispatchers.Main).launch {
                            val response = verifyUserClient("user1", email, password)
                            if (response != null && response.message == "success") {
                                // Navigate to Home screen upon successful login
                                navController.navigate(Screen.Home.route)
                            } else {
                                // Update the error message if verification fails
                                errorMessage = "Login failed. Please check your credentials."
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(36, 35, 49),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(text = "Login", color = Color.White)
                }
            }

            // Optional: Show error message if login fails
            if (errorMessage.isNotEmpty()) {
                item {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(navController: NavHostController, backgroundColor: Brush) {
    ScreenContent(title = "Sign Up", navController = navController, backgroundColor)
}

@Composable
fun ScreenContent(title: String, navController: NavHostController, backgroundColor: Brush) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = title, color = Color.White)
            Button(onClick = { navController.navigate(Screen.Login.route) }) {
                Text("Go to Login")
            }
        }
    }
}

