package com.gk.kmpwallpaperapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //val apiService = remember { MovieApiService(createHttpClient(OkHttp.create())) }
            App()
        }
    }
}