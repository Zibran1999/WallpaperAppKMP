@file:OptIn(ExperimentalMaterial3Api::class)

package com.gk.kmpwallpaperapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.gk.kmpwallpaperapp.common.utils.Category
import com.gk.kmpwallpaperapp.common.utils.calculateColumnCount
import com.gk.kmpwallpaperapp.presentation.MovieListUIEvent
import com.gk.kmpwallpaperapp.presentation.MovieListViewModel
import com.gk.kmpwallpaperapp.presentation.component.MovieItem
import org.koin.compose.viewmodel.koinViewModel

class UpcomingMoviesScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: MovieListViewModel = koinViewModel<MovieListViewModel>()
        val movieListState = viewModel.movieListState.collectAsState().value
        val navigator = LocalNavigator.current
        val lazyGridState = rememberLazyGridState()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Upcoming Movies",
                            color = Color.White
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF303030)
                    )
                )
            },
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xFF101010)
        ) {
            if (movieListState.upcomingMovieList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                BoxWithConstraints(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    val screenWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() }
                    val columnCount = calculateColumnCount(screenWidth)

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columnCount),
                        modifier = Modifier.fillMaxSize(),
                        state = lazyGridState,
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
                    ) {
                        items(movieListState.upcomingMovieList.size) { index ->
                            MovieItem(
                                movie = movieListState.upcomingMovieList[index],
                                navigator = navigator
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            if (index >= movieListState.upcomingMovieList.size - 1 && !movieListState.isLoading) {
                                viewModel.onEvent(MovieListUIEvent.Paginate(Category.UPCOMING))
                            }
                        }
                    }
                }
            }
        }
    }
}