package com.gk.kmpwallpaperapp.presentation.screens.tabs

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.gk.kmpwallpaperapp.presentation.screens.PopularMoviesScreen
import org.jetbrains.compose.resources.painterResource
import wallpaperapp.composeapp.generated.resources.Res
import wallpaperapp.composeapp.generated.resources.movie

object PopularMovieTab : Tab {
    @Composable
    override fun Content() {
        Navigator(PopularMoviesScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }


    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource(Res.drawable.movie)

            return TabOptions(
                index = 0u,
                title = "Popular Movies",
                icon = icon
            )
        }
}