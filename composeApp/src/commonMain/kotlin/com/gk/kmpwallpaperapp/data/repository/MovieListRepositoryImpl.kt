package com.gk.kmpwallpaperapp.data.repository

import MovieListRepository
import com.gk.kmpwallpaperapp.domain.model.Movie
import com.gk.kmpwallpaperapp.common.utils.Resource
import com.gk.kmpwallpaperapp.data.local.movie.MovieDatabase
import com.gk.kmpwallpaperapp.data.remote.MovieApiService
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import toMovie
import toMovieEntity

class MovieListRepositoryImpl(
    private val apiService: MovieApiService,
    private val movieDatabase: MovieDatabase
): MovieListRepository {
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading(true))

        val localMovieList = movieDatabase.movieDao.getMovieListByCategory(category)
        val shouldLoadLocalMovies = localMovieList.isNotEmpty() && !forceFetchFromRemote

        if (shouldLoadLocalMovies) {
            emit(Resource.Success(
                data = localMovieList.map { movieEntity ->
                    movieEntity.toMovie(category)
                }
            ))
            emit(Resource.Loading(false))
            return@flow
        }

        val movieListFromApi = try {
            apiService.getMoviesList(category, page)
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error(message = "Network error: Unable to load movies"))
            return@flow
        } catch (e: ClientRequestException) {
            // 4xx responses (Client errors)
            e.printStackTrace()
            emit(Resource.Error(message = "Client error: ${e.response.status.description}"))
            return@flow
        } catch (e: ServerResponseException) {
            // 5xx responses (Server errors)
            e.printStackTrace()
            emit(Resource.Error(message = "Server error: ${e.response.status.description}"))
            return@flow
        } catch (e: ResponseException) {
            // Handles other response-related errors
            e.printStackTrace()
            emit(Resource.Error(message = "HTTP error: ${e.response.status.description}"))
            return@flow
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(message = "Error loading movies"))
            return@flow
        }

        val movieEntities = movieListFromApi.results.let {
            it.map { movieDto ->
                movieDto.toMovieEntity(category)
            }
        }

        movieDatabase.movieDao.upsertMovieList(movieEntities)
        emit(Resource.Success(
            movieEntities.map { it.toMovie(category) }
        ))
        emit(Resource.Loading(false))

    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading(true))

        val movieEntity = movieDatabase.movieDao.getMovieById(id)
        if (movieEntity != null){
            emit(Resource.Success(
                movieEntity.toMovie(movieEntity.category)
            ))

            emit(Resource.Loading(false))
            return@flow
        }
        emit(Resource.Error("Error no such movie"))
        emit(Resource.Loading(false))
    }
}