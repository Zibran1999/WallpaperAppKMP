package com.gk.kmpwallpaperapp.data.remote

import com.gk.kmpwallpaperapp.utils.constants.Constants.API_KEY
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(ContentNegotiation) {
           json(Json {
               prettyPrint = true
               isLenient = true
               ignoreUnknownKeys = true
           })
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL // Log request and response details, including body
        }

        defaultRequest {
            url {
                parameters.append("api_key", API_KEY)
            }
        }

    }
}