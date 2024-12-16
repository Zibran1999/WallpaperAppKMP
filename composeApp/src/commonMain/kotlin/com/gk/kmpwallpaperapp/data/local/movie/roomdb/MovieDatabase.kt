package com.gk.kmpwallpaperapp.data.local.movie.roomdb

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.gk.kmpwallpaperapp.data.local.movie.roomdb.entities.MovieEntity
import com.gk.kmpwallpaperapp.data.local.movie.roomdb.dao.MovieDao

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MovieDatabaseConstructor : RoomDatabaseConstructor<MovieDatabase>

@Database(entities = [MovieEntity::class], version = 1, exportSchema = true)
@ConstructedBy(MovieDatabaseConstructor::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao
}