package com.gk.kmpwallpaperapp.room

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.gk.kmpwallpaperapp.data.local.movie.roomdb.MovieDatabase

fun getMovieDatabase(context: Context): MovieDatabase {
    val dbFile =
        context.getDatabasePath("moviedb.db") // this is the file where our database gets saved to.
    return Room.databaseBuilder<MovieDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}