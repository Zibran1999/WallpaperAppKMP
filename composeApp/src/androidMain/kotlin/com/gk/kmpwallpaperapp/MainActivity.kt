package com.gk.kmpwallpaperapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.gk.kmpwallpaperapp.data.remote.MovieApiService
import com.gk.kmpwallpaperapp.data.remote.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val apiService = remember { MovieApiService(createHttpClient(OkHttp.create())) }
            App(
                apiService = apiService
            )
        }
    }
}