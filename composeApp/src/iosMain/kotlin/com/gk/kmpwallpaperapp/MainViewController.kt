package com.gk.kmpwallpaperapp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.gk.kmpwallpaperapp.data.remote.MovieApiService
import com.gk.kmpwallpaperapp.data.remote.createHttpClient
import com.gk.kmpwallpaperapp.di.initKoin
import com.gk.kmpwallpaperapp.di.platformModule
import com.gk.kmpwallpaperapp.di.sharedModule
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController(
    configure = { // this lambda function is called once per initialization of this ComposeUIViewController
        initKoin {
            modules(sharedModule, platformModule)
        }
    }
) {
    val apiService = remember { MovieApiService(createHttpClient(Darwin.create())) }
    App(
        apiService = apiService
    )
}