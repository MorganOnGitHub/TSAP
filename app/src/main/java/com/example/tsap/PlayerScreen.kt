package com.example.tsap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PlayerScreen(navController: NavHostController, favPlayerImages: MutableList<Int>, favPlayerDescriptions: MutableList<String>) {
    val players = listOf(
        Player(R.drawable.alfajer, "Emir 'Alfajer' Beder (born June 10, 2005) is a Turkish player who is currently playing for Fnatic.","Emir 'Alfajer' Beder (born June 10, 2005): Key player in Fnatic's success, helping secure top finishes, including VCT 2023: Masters Tokyo, showcasing sharp gameplay and adaptability." ),
        Player(R.drawable.boaster, "The captain of the Valorant team, Jake 'Boaster' Howlett is currently the fragging IGL of the team.","Jake 'Boaster' Howlett: (The captain of the Valorant team) Led Fnatic to multiple championships, including VCT Stage 2 Masters Reykjavik, recognized for his strategic leadership and impressive fragging abilities."),
        Player(R.drawable.chronicle, "Timofey 'Chronicle' Khromov (born August 16, 2002) is a Russian player who is currently playing for Fnatic.","Timofey 'Chronicle' Khromov (born August 16, 2002): Significant contributor to Fnatic's success with key plays in major tournaments, showcasing exceptional skill and clutch performances during VCT competitions."),
        Player(R.drawable.derke, "Nikita 'Derke' Sirmitev (born February 6, 2003) is a Finnish/Russian player who is currently on the inactive roster of Fnatic.","Nikita 'Derke' Sirmitev (born February 6, 2003): Achieved critical victories while on the active roster, including the VCT 2022 Stage 1 Masters, earning recognition for sharpshooting and game-changing abilities."),
        Player(R.drawable.doma,"Domagoj 'Doma' Fancev, born September 11, 2003, is a Croatian Valorant pro known for his time with Fnatic.","Specializing in Duelist and Initiator roles, he helped Fnatic secure 2nd place at VCT Stage 2 Masters Reykjavik 2021."),
        Player(R.drawable.enzo, "Enzo 'Enzo' Mestari (born September 24, 1998): Strategic thinker, clutch performer, delivering critical support and adaptability.","Enzo 'Enzo' Mestari (born September 24, 1998): Strategic thinker and clutch performer, delivering critical support and adaptability during pivotal moments of the team's journey."),
        Player(R.drawable.hiro, "Emirhan 'Hiro' Kat was born on December 14, 2002, primarily known for his coaching role in Valorant rather than as a player", "He has been instrumental in developing team strategies and guiding Fnatic to major victories, such as VCT 2023: LOCKIN São Paulo and Masters Tokyo."),
        Player(R.drawable.kamyk, "Dominik 'Kamyk' Mikołajewski (born January 23, 2004): Versatile player, adapted quickly to challenges.","Dominik 'Kamyk' Mikołajewski (born January 23, 2004): Versatile player who quickly adapted to challenges, providing key support and strategic control during a crucial team period."),
        Player(R.drawable.leo, "Leo 'Leo' Jannesson (born December 15, 2003) is a Swedish player who is currently on the inactive roster of Fnatic.","Leo 'Leo' Jannesson (born December 15, 2003): Showcased versatility in various roles, contributing to Fnatic's competitive edge in multiple tournaments before moving to the inactive roster."),
        Player(R.drawable.mistic, "James 'Mistic' Orfila (born March 23, 2002): Reliable team anchor, known for exceptional utility management.","James 'Mistic' Orfila (born March 23, 2002): Reliable team anchor, known for exceptional utility management and calm, consistent performances in high-pressure situations.")
    )
    Box(
        modifier = Modifier.fillMaxSize().background(Color(13, 14, 14))
    ){
        LazyColumn(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            items(players) { player ->
                PlayerCard(player, navController, favPlayerImages, favPlayerDescriptions)
            }
        }


    }
}