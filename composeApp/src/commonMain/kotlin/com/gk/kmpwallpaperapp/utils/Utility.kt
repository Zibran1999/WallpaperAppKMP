package com.gk.kmpwallpaperapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gk.kmpwallpaperapp.presentation.component.GradientCircularProgressIndicator

/**
 * Utility function to calculate the number of columns based on screen width.
 *
 * @param screenWidth The width of the screen in Dp.
 * @return The number of columns to display.
 */

fun calculateColumnCount(screenWidth: Dp): Int {
    return when {
        screenWidth >= 1800.dp -> 10 // Ultra-wide desktops
        screenWidth >= 1600.dp -> 9  // Larger desktops
        screenWidth >= 1400.dp -> 8  // Wide desktops
        screenWidth >= 1200.dp -> 7  // Standard desktops
        screenWidth >= 1000.dp -> 6  // Smaller desktops or large tablets
        screenWidth >= 800.dp -> 4   // Medium-sized screens
        screenWidth in 500.dp..799.dp -> 3 // Small tablets or large phones
        else -> 2                    // Small screens or mobile
    }
}

@Composable
fun ImageErrorState(
    isDesktop: Boolean = false,
    posterWidth: Dp = 0.dp,
    posterHeight: Dp = 0.dp
) {
    Column(
        modifier = if (isDesktop) {
            Modifier
                .width(posterWidth)
                .height(posterHeight)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.DarkGray)
        } else {
            Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
                .background(Color.DarkGray)
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "Error Icon",
            tint = MaterialTheme.colorScheme.onError,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Unable to load the image.",
            color = MaterialTheme.colorScheme.onError,
            style = MaterialTheme.typography.bodyMedium.copy(
                textAlign = TextAlign.Center
            )

        )
        Text(
            text = buildAnnotatedString {
                append("Check your internet connection and try again.\n")
                append("Or Image is not available!")
            },
            color = MaterialTheme.colorScheme.onError,
            style = MaterialTheme.typography.bodySmall.copy(
                textAlign = TextAlign.Center
            ),
            fontSize = 10.sp,
            modifier = Modifier
                .padding(10.dp)
        )
    }
}

@Composable
fun ImageLoadingState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        contentAlignment = Alignment.Center
    ) {
        GradientCircularProgressIndicator(
            progress = 0.7f, // Example progress
            modifier = Modifier.size(64.dp), // Size of the indicator
            strokeWidth = 8f // Adjust thickness
        )
    }
}
