package com.gk.kmpwallpaperapp.data.local.movie.roomdb.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gk.kmpwallpaperapp.data.local.movie.roomdb.entities.MovieEntity

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * FROM MovieEntity WHERE category = :category")
    suspend fun getMovieListByCategory(category: String): List<MovieEntity>
}