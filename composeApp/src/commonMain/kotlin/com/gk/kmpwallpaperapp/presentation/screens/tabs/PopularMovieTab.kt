package com.gk.kmpwallpaperapp.presentation.screens.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition

object PopularMovieTab: Tab {
    @Composable
    override fun Content() {
        Navigator(PopularMoviesScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }


    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 0u,
                title = "Popular Movies",
                icon = null
            )
        }
}