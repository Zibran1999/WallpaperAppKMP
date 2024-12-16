package com.gk.kmpwallpaperapp.presentation.screens.home.events

sealed interface MovieListUIEvent {
    data class Paginate(val category: String): MovieListUIEvent
    data object Navigate: MovieListUIEvent
}