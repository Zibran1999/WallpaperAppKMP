package com.gk.kmpwallpaperapp.domain.repository

import com.gk.kmpwallpaperapp.domain.model.Movie
import com.gk.kmpwallpaperapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}