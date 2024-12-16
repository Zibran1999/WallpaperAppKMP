package com.gk.kmpwallpaperapp.di

import com.gk.kmpwallpaperapp.data.local.movie.roomdb.MovieDatabase
import com.gk.kmpwallpaperapp.data.remote.apis.MovieApiService
import com.gk.kmpwallpaperapp.data.remote.createHttpClient
import com.gk.kmpwallpaperapp.data.remote.repository.MovieListRepositoryImpl
import com.gk.kmpwallpaperapp.domain.repository.MovieListRepository
import com.gk.kmpwallpaperapp.presentation.screens.details.DetailsViewModel
import com.gk.kmpwallpaperapp.presentation.screens.home.viewmodel.MovieListViewModel
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
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
        MovieListRepositoryImpl(
           apiService =  get(),
            movieDatabase = get<MovieDatabase>()
        )
    }.bind<MovieListRepository>()

    // short way
    //singleOf(::MoviesRepositoryImpl) bind MoviesRepository::class
    viewModelOf(::MovieListViewModel)
    viewModelOf(::DetailsViewModel)
}