package com.gk.kmpwallpaperapp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.gk.kmpwallpaperapp.common.Constants.IMAGE_BASE_URL
import com.gk.kmpwallpaperapp.common.utils.RatingBar
import com.gk.kmpwallpaperapp.details.presentation.DetailsScreen
import com.gk.kmpwallpaperapp.domain.model.Movie

@Composable
fun MovieItem(
    movie: Movie,
    navigator: Navigator?
) {
    val painter = rememberAsyncImagePainter("${IMAGE_BASE_URL}${movie.poster_path}")
    val painterState = painter.state.collectAsState().value
    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    var dominantColor by remember { mutableStateOf(defaultColor) }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
            .padding(4.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 22.dp,
                    topEnd = 22.dp,
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                )
            )
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondaryContainer,
                        dominantColor
                    )
                )
            )
            .clickable {
                 navigator?.push(DetailsScreen(movie.id))
            }
    ) {
        when (painterState) {
            is AsyncImagePainter.State.Loading -> {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(MaterialTheme.colorScheme.primary)
                )
            }

            is AsyncImagePainter.State.Error -> {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(MaterialTheme.colorScheme.error),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error Loading", color = Color.White)
                }
            }

            else -> {
                Image(
                    painter = painter,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(22.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp),
            text = movie.title,
            color = Color.White,
            fontSize = 15.sp,
            maxLines = 1
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 12.dp, top = 4.dp)
        ) {
            RatingBar(
                starsModifier = Modifier.size(18.dp),
                rating = movie.vote_average / 2
            )

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = movie.vote_average.toString().take(3),
                color = Color.LightGray,
                fontSize = 14.sp,
                maxLines = 1
            )
        }
    }

}