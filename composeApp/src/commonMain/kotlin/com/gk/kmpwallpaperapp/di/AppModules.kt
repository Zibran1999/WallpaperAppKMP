package com.gk.kmpwallpaperapp.di

import com.gk.kmpwallpaperapp.data.local.movie.MovieDatabase
import com.gk.kmpwallpaperapp.data.remote.MovieApiService
import com.gk.kmpwallpaperapp.data.remote.createHttpClient
import com.gk.kmpwallpaperapp.data.repository.MovieListRepositoryImpl
import com.gk.kmpwallpaperapp.data.repository.MoviesRepository
import com.gk.kmpwallpaperapp.data.repository.MoviesRepositoryImpl
import com.gk.kmpwallpaperapp.domain.repository.MovieListRepository
import com.gk.kmpwallpaperapp.presentation.MovieListViewModel
import com.gk.kmpwallpaperapp.presentation.viewmodel.MoviesViewModel
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module
val sharedModule = module {
    single {
        createHttpClient(get<HttpClientEngine>())
    }

    single {
        MovieApiService(get())
    }

    single {
        MoviesRepositoryImpl(get())
    }.bind<MoviesRepository>()

    single {
        MovieListRepositoryImpl(
           apiService =  get(),
            movieDatabase = get<MovieDatabase>()
        )
    }.bind<MovieListRepository>()

    // short way
    //singleOf(::MoviesRepositoryImpl) bind MoviesRepository::class
    viewModelOf(::MoviesViewModel)
    viewModelOf(::MovieListViewModel)

}