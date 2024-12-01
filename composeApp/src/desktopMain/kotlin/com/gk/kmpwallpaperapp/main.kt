package com.gk.kmpwallpaperapp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.gk.kmpwallpaperapp.data.remote.MovieApiService
import com.gk.kmpwallpaperapp.data.remote.createHttpClient
import com.gk.kmpwallpaperapp.di.initKoin
import com.gk.kmpwallpaperapp.di.platformModule
import com.gk.kmpwallpaperapp.di.sharedModule
import io.ktor.client.engine.okhttp.OkHttp

fun main() {
    initKoin {
        modules(sharedModule, platformModule)
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Wallpaper App",
        ) {
            val apiService = remember { MovieApiService(createHttpClient(OkHttp.create())) }
            App(
                apiService = apiService
            )
        }
    }
}