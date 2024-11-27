package com.gk.kmpwallpaperapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform