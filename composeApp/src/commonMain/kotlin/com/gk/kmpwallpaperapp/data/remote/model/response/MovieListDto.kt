package com.gk.kmpwallpaperapp.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)