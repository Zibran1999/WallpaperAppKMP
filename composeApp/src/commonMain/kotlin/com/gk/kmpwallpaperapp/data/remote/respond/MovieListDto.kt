package com.gk.kmpwallpaperapp.data.remote.respond

import kotlinx.serialization.Serializable

@Serializable
data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)