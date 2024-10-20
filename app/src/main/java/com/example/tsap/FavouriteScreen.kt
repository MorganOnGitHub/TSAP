import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun FavouriteScreen(navController: NavHostController, favPlayerImages: MutableList<Int>, favPlayerDescriptions: MutableList<String>) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color(13, 14, 14))
    ){
        LazyColumn(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ){item {
            Text(text = "Favourite Player(s)",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )

        }
            items(favPlayerImages.size) { index ->
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
                    painter = painterResource(id = favPlayerImages[index]),
                    contentDescription = "Favorite player image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(8.dp)
                )
                    Text(favPlayerDescriptions[index])

                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            favPlayerImages.removeAt(index)
                            favPlayerDescriptions.removeAt(index)},
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Remove from Favourites", textAlign = TextAlign.Center)
                    }
                }


                }

            }


        }


    }
}