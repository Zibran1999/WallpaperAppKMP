package com.gk.kmpwallpaperapp

import androidx.compose.ui.window.ComposeUIViewController
import com.gk.kmpwallpaperapp.di.initKoin
import com.gk.kmpwallpaperapp.di.platformModule
import com.gk.kmpwallpaperapp.di.sharedModule

fun MainViewController() = ComposeUIViewController(
    configure = { // this lambda function is called once per initialization of this ComposeUIViewController
        initKoin {
            modules(sharedModule, platformModule)
        }
    }
) {
    //val apiService = remember { MovieApiService(createHttpClient(Darwin.create())) }
    App()
}