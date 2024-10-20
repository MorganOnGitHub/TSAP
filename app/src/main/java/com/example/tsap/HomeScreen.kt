package com.example.tsap

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    var showImage by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        showImage = true
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(13, 14, 14))
    ){
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color(13, 14, 14)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                AnimatedVisibility(
                    visible = showImage,
                    enter = fadeIn(tween(durationMillis = 1600)) + scaleIn(initialScale = 0.5f),
                    exit = fadeOut(tween(durationMillis = 1600)) + scaleOut(targetScale = 0.5f)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.fnatic),
                        contentDescription = "Fnatic Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 120.dp)
                            .align(Alignment.Center)
                            .height(200.dp)
                    )
                }
            }
            item{
                AnimatedVisibility(
                    visible = showImage,
                    enter = fadeIn(tween(durationMillis = 3000)) + scaleIn(initialScale = 0.5f),
                    exit = fadeOut(tween(durationMillis = 3000)) + scaleOut(targetScale = 0.5f)
                ) {
                    Text(
                        text = "Home of the Fnatic Valorant Esports Team",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 16.dp),
                        color = Color.White
                    )
                }
            }

        }


    }
}