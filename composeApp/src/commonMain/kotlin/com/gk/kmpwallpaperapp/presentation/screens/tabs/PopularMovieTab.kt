package com.gk.kmpwallpaperapp.presentation.screens.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.gk.kmpwallpaperapp.presentation.screens.PopularMoviesScreen

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
            return TabOptions(
                index = 0u,
                title = "Popular Movies",
                icon = rememberVectorPainter(Icons.Rounded.Movie)
            )
        }
}