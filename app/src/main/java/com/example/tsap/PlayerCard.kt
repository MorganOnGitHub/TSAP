package com.example.tsap

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PlayerCard(
    player: Player,
    navController: NavHostController,
    favPlayerImages: MutableList<Int>,
    favPlayerDescriptions: MutableList<String>
){
    val achievement = "${player.achievement}"
    OutlinedCard(
        modifier = Modifier.padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Image(
                painter = painterResource(id = player.image),
                contentDescription = "Image of player",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(8.dp)
            )
            Text(player.description)

            Spacer(modifier = Modifier.height(8.dp))
            Row{
                Button(onClick ={
                    if (!favPlayerImages.contains(player.image)) {
                        favPlayerImages.add(player.image)
                        favPlayerDescriptions.add(player.description)
                    }
                }, modifier = Modifier.padding(8.dp)){
                    Text("Add to Favourites", textAlign = TextAlign.Center)
                }
                Button(onClick = {
                    navController.navigate(Screen.Achievements.createRoute(player.image, achievement))
                }, modifier = Modifier.padding(8.dp)){
                    Text("Achievements", textAlign = TextAlign.Center)
                }
            }

        }


    }
}