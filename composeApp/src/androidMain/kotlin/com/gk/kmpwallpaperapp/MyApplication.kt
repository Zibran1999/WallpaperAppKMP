package com.gk.kmpwallpaperapp

import android.app.Application
import com.gk.kmpwallpaperapp.di.initKoin
import com.gk.kmpwallpaperapp.di.platformModule
import com.gk.kmpwallpaperapp.di.sharedModule
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApplication)
            modules(sharedModule, platformModule)
        }
    }
}