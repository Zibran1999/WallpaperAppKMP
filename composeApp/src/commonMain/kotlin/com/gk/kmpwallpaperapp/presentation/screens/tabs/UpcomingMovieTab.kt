package com.gk.kmpwallpaperapp.presentation.screens.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.gk.kmpwallpaperapp.presentation.screens.UpcomingMoviesScreen

object UpcomingMovieTab : Tab {
    @Composable
    override fun Content() {
        Navigator(UpcomingMoviesScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            return TabOptions(
                index = 1u,
                title = "Upcoming",
                icon = rememberVectorPainter(Icons.Rounded.Upcoming)
            )
        }
}