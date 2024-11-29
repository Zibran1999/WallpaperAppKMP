package com.gk.kmpwallpaperapp.presentation.screens.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object PopularMoviesScreen : Tab {
    @Composable
    override fun Content() {
        Scaffold(
            topBar = {
                Text(text = "Popular Movies")
//                TopAppBar(
//                    title = {
//                        Text(text = "Popular Movies")
//                    }
//                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Popular Movies Screen")
            }
        }
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 0u,
                title = "Popular Movies",
                icon = null
            )
        }
}