package com.gk.kmpwallpaperapp.utils.mappers

import com.gk.kmpwallpaperapp.data.local.movie.roomdb.entities.MovieEntity
import com.gk.kmpwallpaperapp.data.remote.model.response.MovieDto
import com.gk.kmpwallpaperapp.domain.model.Movie


fun MovieDto.toMovieEntity(
    category: String
): MovieEntity {
    return MovieEntity(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        id = id ?: -1,
        category = category,
        genreIds = try {
            genreIds?.joinToString(",") ?: "-1,-2"
        } catch (e: Exception) {
            "-1,-2"
        }
    )
}

fun MovieEntity.toMovie(
    category: String
): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        id = id,
        category = category,
        genreIds = try {
            genreIds.split(",").map { it.toInt() }
        } catch (e: Exception){
            listOf(-1, -2)
        }
    )
}