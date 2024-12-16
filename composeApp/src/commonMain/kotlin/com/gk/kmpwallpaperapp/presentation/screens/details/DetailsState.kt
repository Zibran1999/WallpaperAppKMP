package com.gk.kmpwallpaperapp.presentation.screens.details

import com.gk.kmpwallpaperapp.domain.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)
