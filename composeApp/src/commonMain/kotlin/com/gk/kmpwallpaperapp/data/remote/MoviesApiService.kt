package com.gk.kmpwallpaperapp.data.remote

import com.gk.kmpwallpaperapp.common.Constants.API_KEY
import com.gk.kmpwallpaperapp.data.remote.respond.MovieListDto
import com.gk.kmpwallpaperapp.common.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApiService(
    private val httpClient: HttpClient
) {

    suspend fun getMoviesList(category: String, page: Int): MovieListDto {
        val url = "${BASE_URL}movie/$category"
        return httpClient.get(url) {
            parameter("page", page)
            parameter("api_key", API_KEY)
        }.body()
    }
}
