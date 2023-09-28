package com.atb.pokedex.core.utils

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.atb.pokedex.R

@Composable
fun LoadingAnimationScale(
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(label = "infinite transition")
    val scale = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale animation"
    )
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 560f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotation"
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.pokeball),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .scale(scale.value)
                .rotate(rotateAnimation)
        )
    }
}