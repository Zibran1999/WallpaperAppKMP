package com.gk.kmpwallpaperapp.presentation.screens.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gk.kmpwallpaperapp.common.utils.Category
import com.gk.kmpwallpaperapp.domain.model.Movie
import com.gk.kmpwallpaperapp.presentation.MovieListUIEvent
import com.gk.kmpwallpaperapp.presentation.MovieListViewModel
import com.gk.kmpwallpaperapp.presentation.component.MovieItem
import org.koin.compose.viewmodel.koinViewModel

object UpcomingMoviesScreen : Tab {
    @Composable
    override fun Content() {
        val viewModel: MovieListViewModel = koinViewModel<MovieListViewModel>()
        val movieListState = viewModel.movieListState.collectAsState().value
        val  navigator = LocalNavigator.current
        val lazyGridState = rememberLazyGridState()

        if (movieListState.upcomingMovieList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
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

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 1u,
                title = "Upcoming",
                icon = null
            )
        }
}