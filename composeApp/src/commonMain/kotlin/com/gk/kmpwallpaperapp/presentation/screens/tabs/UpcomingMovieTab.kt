package com.gk.kmpwallpaperapp.presentation.screens.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition

object UpcomingMovieTab: Tab {
    @Composable
    override fun Content() {
        Navigator(UpcomingMoviesScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 1u,
                title = "Upcoming",
                icon = null
            )
        }
}