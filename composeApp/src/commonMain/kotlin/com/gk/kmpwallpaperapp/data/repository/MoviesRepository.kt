package com.gk.kmpwallpaperapp.data.repository

import com.gk.kmpwallpaperapp.data.remote.respond.MovieListDto

interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): MovieListDto

    suspend fun getUpcomingMovies(page: Int): MovieListDto
}