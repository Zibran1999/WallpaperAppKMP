package com.gk.composecourse.movies_app_cleanArch.movieList.domain.repository

import com.gk.composecourse.movies_app_cleanArch.movieList.domain.model.Movie
import com.gk.composecourse.movies_app_cleanArch.movieList.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}