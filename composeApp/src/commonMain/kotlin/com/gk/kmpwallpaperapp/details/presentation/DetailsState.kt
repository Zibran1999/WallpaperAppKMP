package com.gk.kmpwallpaperapp.details.presentation

import com.gk.kmpwallpaperapp.domain.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)
