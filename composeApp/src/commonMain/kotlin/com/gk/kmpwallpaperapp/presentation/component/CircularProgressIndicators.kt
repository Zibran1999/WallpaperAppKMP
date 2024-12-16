package com.gk.kmpwallpaperapp.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// 1. For colorful gradient CircularProgressIndicator()
@Composable
fun GradientCircularProgressIndicator(
    progress: Float = 0.75f, // Adjust progress value as needed (0f to 1f)
    modifier: Modifier = Modifier,
    strokeWidth: Float = 10f,
    colors: List<Color> = listOf(Color.Cyan, Color.Magenta, Color.Yellow, Color.Cyan)
) {
    Canvas(modifier = modifier) {
        val sweepGradient = Brush.sweepGradient(colors)
        val diameter = size.minDimension
        val radius = diameter / 2

        drawCircle(
            brush = Brush.sweepGradient(colors),
            radius = radius,
            center = center,
            style = androidx.compose.ui.graphics.drawscope.Stroke(strokeWidth)
        )

        drawArc(
            brush = sweepGradient,
            startAngle = -90f, // Start at top
            sweepAngle = progress * 360f, // Map progress to the arc
            useCenter = false,
            style = androidx.compose.ui.graphics.drawscope.Stroke(strokeWidth),
            topLeft = center - androidx.compose.ui.geometry.Offset(radius, radius),
            size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2)
        )
    }
}


/*
  2. This code is for showing a rotating refresh icon for loading state

        val infiniteTransition = rememberInfiniteTransition()
        val rotation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1200, easing = LinearEasing)
            )
        )

        Box(
        Modifier
        .fillMaxWidth()
        .height(250.dp),
        contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Loading",
                modifier = Modifier
                    .size(48.dp)
                    .rotate(rotation),
                tint = MaterialTheme.colorScheme.primary
            )
}*/

/*
    3.  Animated Stroke Width:
        Make the stroke width of the CircularProgressIndicator vary dynamically for a pulsating effect:

        val infiniteTransition = rememberInfiniteTransition()
        val strokeWidth by infiniteTransition.animateFloat(
            initialValue = 2f,
            targetValue = 8f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 600, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Box(
        Modifier
        .fillMaxWidth()
        .height(250.dp),
        contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = strokeWidth.dp
            )
        }

*/
/*
    4. Change Animation Curve
       Modify the progress animation with a custom easing curve:

        val infiniteTransition = rememberInfiniteTransition()
        val progress by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        Box(
        Modifier
        .fillMaxWidth()
        .height(250.dp),
        contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { progress },
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 4.dp,
                trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
            )
        }

*/
