package com.gk.kmpwallpaperapp

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.gk.kmpwallpaperapp.data.remote.MovieApiService
import com.gk.kmpwallpaperapp.presentation.screens.tabs.PopularMoviesScreen
import com.gk.kmpwallpaperapp.presentation.screens.tabs.UpcomingMoviesScreen
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App(apiService: MovieApiService) {
    KoinContext {
        MaterialTheme {
            TabNavigator(PopularMoviesScreen) {
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            TabNavigationItem(PopularMoviesScreen)
                            TabNavigationItem(UpcomingMoviesScreen)
                        }
                    }
                ) {
                    CurrentTab()
                }
            }

            val scope = rememberCoroutineScope()
            scope.launch {
                val response = apiService.getMoviesList("popular", 1)
                println("moviesResponse: $response")
            }
            //Navigator(HomeScreen(modifier = Modifier))
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = { Text(text = tab.options.title) },
        icon = {}
    )
}