package com.gk.kmpwallpaperapp.room

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.gk.kmpwallpaperapp.data.local.movie.MovieDatabase
import java.io.File

fun getMovieDatabase(): MovieDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "moviedb.db")

    return Room.databaseBuilder<MovieDatabase>(
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}