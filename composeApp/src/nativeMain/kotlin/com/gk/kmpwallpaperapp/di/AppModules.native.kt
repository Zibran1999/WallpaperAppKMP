package com.gk.kmpwallpaperapp.di
import com.gk.kmpwallpaperapp.data.local.movie.MovieDatabase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val platformModule = module {
    single<HttpClientEngine> { Darwin.create() }

    single<MovieDatabase> { getMovieDatabase() }
}