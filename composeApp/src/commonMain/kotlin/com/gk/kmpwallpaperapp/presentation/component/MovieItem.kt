//package com.gk.kmpwallpaperapp.presentation.component
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import cafe.adriel.voyager.navigator.Navigator
//import coil3.compose.AsyncImagePainter
//import coil3.compose.rememberAsyncImagePainter
//import coil3.request.ImageRequest
//import coil3.size.Size
//import com.gk.kmpwallpaperapp.common.Constants.IMAGE_BASE_URL
//import com.gk.kmpwallpaperapp.domain.model.Movie
//import com.gk.kmpwallpaperapp.common.utils.RatingBar
//import com.gk.kmpwallpaperapp.common.utils.Screen
//import com.gk.kmpwallpaperapp.common.utils.getAverageColor
//
//@Composable
//fun MovieItem(
//    movie: Movie,
//    navigator: Navigator
//) {
//    val imageState = rememberAsyncImagePainter(
//        model = ImageRequest
//            .Builder(LocalContext.current)
//            .data(IMAGE_BASE_URL + movie.poster_path)
//            .size(Size.ORIGINAL)
//            .build()
//    ).state
//
//    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
//    var dominantColor by remember {
//        mutableStateOf(defaultColor)
//    }
//
//    Column(
//        modifier = Modifier
//            .wrapContentHeight()
//            .width(200.dp)
//            .padding(4.dp)
//            .clip(
//                RoundedCornerShape(
//                    topStart = 22.dp,
//                    topEnd = 22.dp,
//                    bottomStart = 10.dp,
//                    bottomEnd = 10.dp
//                )
//            )
//            .background(
//                Brush.verticalGradient(
//                    colors = listOf(
//                        MaterialTheme.colorScheme.secondaryContainer,
//                        dominantColor
//                    )
//                )
//            )
//            .clickable {
//               // navigator.push(Screen.Details.rout + "/${movie.id}")
//            }
//    ) {
//        if (imageState is AsyncImagePainter.State.Error) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(6.dp)
//                    .height(250.dp)
//                    .clip(RoundedCornerShape(22.dp))
//                    .background(MaterialTheme.colorScheme.primaryContainer),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    modifier = Modifier.size(70.dp),
//                    imageVector = Icons.Rounded.ImageNotSupported,
//                    contentDescription = movie.title
//                )
//            }
//        }
//
//        if (imageState is AsyncImagePainter.State.Success) {
//            dominantColor = getAverageColor(
//                imageBitmap = imageState.result.drawable.toBitmap().asImageBitmap()
//            )
//
//            Image(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(0.dp)
//                    .height(250.dp)
//                    .clip(RoundedCornerShape(22.dp)),
//                painter = imageState.painter,
//                contentDescription = movie.title,
//                contentScale = ContentScale.Crop
//            )
//        }
//        Spacer(modifier = Modifier.height(6.dp))
//
//        Text(
//            modifier = Modifier.padding(start = 16.dp, end = 8.dp),
//            text = movie.title,
//            color = Color.White,
//            fontSize = 15.sp,
//            maxLines = 1
//        )
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 16.dp, bottom = 12.dp, top = 4.dp)
//        ) {
//            RatingBar(
//                starsModifier = Modifier.size(18.dp),
//                rating = movie.vote_average / 2
//            )
//
//            Text(
//                modifier = Modifier.padding(start = 4.dp),
//                text = movie.vote_average.toString().take(3),
//                color = Color.LightGray,
//                fontSize = 14.sp,
//                maxLines = 1
//            )
//        }
//    }
//
//}