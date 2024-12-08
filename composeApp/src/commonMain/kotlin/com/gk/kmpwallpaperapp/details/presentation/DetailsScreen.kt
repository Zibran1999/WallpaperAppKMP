@file:OptIn(ExperimentalMaterial3Api::class)

package com.gk.kmpwallpaperapp.details.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.gk.kmpwallpaperapp.common.Constants.IMAGE_BASE_URL
import com.gk.kmpwallpaperapp.common.utils.RatingBar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import wallpaperapp.composeapp.generated.resources.Res
import wallpaperapp.composeapp.generated.resources.image_not_supported
import wallpaperapp.composeapp.generated.resources.language
import wallpaperapp.composeapp.generated.resources.overview
import wallpaperapp.composeapp.generated.resources.release_date
import wallpaperapp.composeapp.generated.resources.votes

class DetailsScreen(
    private val movieId: Int? = null
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val detailsViewModel = koinViewModel<DetailsViewModel>()
        val detailsState by detailsViewModel.detailsState.collectAsState()
        // State to hold the average color
        val defaultColor = MaterialTheme.colorScheme.secondaryContainer
        var dominantColor by remember {
            mutableStateOf(defaultColor)
        }

        LaunchedEffect(movieId) {
            if (movieId != null) {
                detailsViewModel.getMovieDetails(movieId)
            }
        }

        val backDropImage =
            rememberAsyncImagePainter("${IMAGE_BASE_URL}${detailsState.movie?.backdrop_path}")
        val posterImage =
            rememberAsyncImagePainter("${IMAGE_BASE_URL}${detailsState.movie?.poster_path}")

        val backDropImageState = backDropImage.state.collectAsState().value
        val posterImageState = posterImage.state.collectAsState().value


        if (backDropImageState is AsyncImagePainter.State.Success) {
//            dominantColor = getAverageColor(
//                imageBitmap = backDropImageState.result.drawable.toBitmap().asImageBitmap()
//            )
        }

        fun isColorDark(color: Color): Boolean {
            return color.luminance() < 0.4
        }

        val textColor = if (isColorDark(dominantColor)) {
            Color.White
        } else {
            Color.Black
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = detailsState.movie?.title ?: "Movie Details",
                            maxLines = 1,
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { navigator?.pop() },
                        )
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                defaultColor,
                                dominantColor
                            )
                        )
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                if (backDropImageState is AsyncImagePainter.State.Error) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(70.dp),
                            painter = painterResource(Res.drawable.image_not_supported),
                            contentDescription = detailsState.movie?.title
                        )
                    }
                }

                if (backDropImageState is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(290.dp),
                        painter = backDropImageState.painter,
                        contentDescription = detailsState.movie?.title,
                        contentScale = ContentScale.FillWidth
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(240.dp)
                    ) {
                        if (posterImageState is AsyncImagePainter.State.Error) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(70.dp),
                                    painter = painterResource(Res.drawable.image_not_supported),
                                    contentDescription = detailsState.movie?.title
                                )
                            }
                        }

                        if (posterImageState is AsyncImagePainter.State.Success) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp)),
                                painter = posterImageState.painter,
                                contentDescription = detailsState.movie?.title,
                                contentScale = ContentScale.Crop
                            )
                        }

                    }

                    detailsState.movie?.let { movie ->
                        Column(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = movie.title,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = textColor
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Row {
                                RatingBar(
                                    starsModifier = Modifier.size(18.dp),
                                    rating = movie.vote_average / 2
                                )

                                Text(
                                    modifier = Modifier.padding(start = 4.dp),
                                    text = movie.vote_average.toString().take(3),
                                    color = textColor.copy(alpha = 0.8f),
                                    fontSize = 14.sp,
                                    maxLines = 1
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = stringResource(Res.string.language) + movie.original_language,
                                color = textColor
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontSize = 15.sp)) {
                                        append(stringResource(Res.string.release_date))
                                    }
                                    withStyle(style = SpanStyle(fontSize = 12.sp)) {
                                        append(movie.release_date)
                                    }
                                },
                                color = textColor
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = movie.vote_count.toString() + stringResource(Res.string.votes),
                                color = textColor
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(Res.string.overview),
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                detailsState.movie?.let {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = it.overview,
                        fontSize = 16.sp,
                        color = textColor
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

}