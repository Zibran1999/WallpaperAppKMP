package com.gk.kmpwallpaperapp.di

import com.gk.kmpwallpaperapp.data.local.movie.MovieDatabase
import com.gk.kmpwallpaperapp.room.getMovieDatabase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule = module {
    single<HttpClientEngine> { OkHttp.create() }

    // Provide the PeopleDatabase instance
    single<MovieDatabase> { getMovieDatabase(get()) }

    // Provide the PeopleDao instance
    // single { get<MovieDatabase>().movieDao }
}