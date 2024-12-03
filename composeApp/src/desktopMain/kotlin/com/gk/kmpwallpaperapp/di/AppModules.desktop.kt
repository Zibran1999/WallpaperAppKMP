package com.gk.kmpwallpaperapp.di

import com.gk.kmpwallpaperapp.data.local.movie.MovieDatabase
import com.gk.kmpwallpaperapp.room.getMovieDatabase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val platformModule = module {
    single<HttpClientEngine> { OkHttp.create() }
    single<MovieDatabase> { getMovieDatabase() }
}