package com.gk.kmpwallpaperapp.data.repository

import com.gk.kmpwallpaperapp.data.remote.MovieApiService
import com.gk.kmpwallpaperapp.data.remote.respond.MovieListDto

class MoviesRepositoryImpl(
    private val apiService: MovieApiService
): MoviesRepository {
    override suspend fun getPopularMovies(page: Int): MovieListDto {
        return apiService.getMoviesList("popular", page)
    }

    override suspend fun getUpcomingMovies(page: Int): MovieListDto {
        return apiService.getMoviesList("upcoming", page)
    }
}