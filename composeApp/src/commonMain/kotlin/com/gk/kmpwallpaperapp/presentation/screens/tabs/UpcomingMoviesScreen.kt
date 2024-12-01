@file:OptIn(ExperimentalMaterial3Api::class)

package com.gk.kmpwallpaperapp.presentation.screens.tabs

import MovieDto
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gk.kmpwallpaperapp.presentation.viewmodel.MoviesViewModel
import org.koin.compose.viewmodel.koinViewModel

object UpcomingMoviesScreen : Tab {
    @Composable
    override fun Content() {
        val viewModel: MoviesViewModel = koinViewModel<MoviesViewModel>()
        val moviesState = viewModel.upcomingMovies.collectAsState()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Upcoming Movies")
                    }
                )
            }
        ) { paddingValues ->
            // Handle the state of movies
            when (val movies = moviesState.value) {
                null -> {
                    // Show a loading state while data is being fetched
                    androidx.compose.material.Text(
                        text = "Loading...",
                        modifier = Modifier.fillMaxSize().padding(paddingValues),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                else -> {
                    // Display the list of movies
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(paddingValues)
                    ) {
                        items(movies.results) { movie ->
                            MovieItem(movie)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun MovieItem(movie: MovieDto) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            movie.title?.let {
                androidx.compose.material.Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            androidx.compose.material.Text(
                text = "Release Date: ${movie.releaseDate}",
                style = MaterialTheme.typography.bodySmall
            )
            movie.overview?.let {
                androidx.compose.material.Text(
                    text = it,
                    maxLines = 3,
                    style = MaterialTheme.typography.bodySmall
                )
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