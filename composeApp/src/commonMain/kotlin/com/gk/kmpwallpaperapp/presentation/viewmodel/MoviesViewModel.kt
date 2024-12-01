package com.gk.kmpwallpaperapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.kmpwallpaperapp.data.remote.respond.MovieListDto
import com.gk.kmpwallpaperapp.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(
    private val repository: MoviesRepository
): ViewModel() {
    private val _popularMovies = MutableStateFlow<MovieListDto?>(null)
    val popularMovies: StateFlow<MovieListDto?> = _popularMovies

    private val _upcomingMovies = MutableStateFlow<MovieListDto?>(null)
    val upcomingMovies: StateFlow<MovieListDto?> = _upcomingMovies

    init {
        loadPopularMovies(1)
        loadUpcomingMovies(1)
    }

    private fun loadPopularMovies(page: Int) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.Default) {
                repository.getPopularMovies(page)
            }
            _popularMovies.value = response
        }
    }

    private fun loadUpcomingMovies(page: Int) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.Default) {
                repository.getUpcomingMovies(page)
            }
            _upcomingMovies.value = response
        }
    }
}