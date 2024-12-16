@file:OptIn(ExperimentalMaterial3Api::class)

package com.gk.kmpwallpaperapp.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.gk.kmpwallpaperapp.presentation.screens.home.events.MovieListUIEvent
import com.gk.kmpwallpaperapp.presentation.screens.home.viewmodel.MovieListViewModel
import com.gk.kmpwallpaperapp.presentation.screens.home.tabs.PopularMovieTab
import com.gk.kmpwallpaperapp.presentation.screens.home.tabs.UpcomingMovieTab
import org.koin.compose.viewmodel.koinViewModel

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val movieListViewModel: MovieListViewModel = koinViewModel<MovieListViewModel>()
        val movieListState = movieListViewModel.movieListState.collectAsState().value

        TabNavigator(PopularMovieTab) {
            Scaffold(
                bottomBar = {
                    NavigationBar(
                        containerColor = Color.DarkGray
                    ) {
                        TabNavigationItem(PopularMovieTab, movieListViewModel::onEvent)
                        TabNavigationItem(UpcomingMovieTab, movieListViewModel::onEvent)
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    CurrentTab()
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(
    tab: Tab,
    onEvent: (MovieListUIEvent) -> Unit,
) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        colors = NavigationBarItemColors(
            selectedIconColor = Color.White,
            selectedIndicatorColor = Color(0x80FFFFFF),
            unselectedIconColor = Color.LightGray,
            unselectedTextColor = Color.LightGray,
            selectedTextColor = Color.White,
            disabledIconColor = Color.Gray,
            disabledTextColor = Color.Gray
        ),
        selected = tabNavigator.current == tab,
        onClick = {
            tabNavigator.current = tab
            onEvent(MovieListUIEvent.Navigate)
        },
        label = { Text(text = tab.options.title) },
        icon = {
            tab.options.icon?.let { painter ->
                Icon(
                    painter = painter,
                    contentDescription = tab.options.title
                )
            }
        }
    )
}