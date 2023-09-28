package com.atb.pokedex.core.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.atb.pokedex.R
import kotlinx.coroutines.delay

@Composable
fun LoadingAnimationAlpha(
    modifier: Modifier = Modifier,
    image: Int = R.drawable.pokeball,
    imageSize: Dp = 50.dp,
    animationDelay: Int = 400,
    spaceBetween: Dp = 10.dp
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0.3f) },
        remember { Animatable(initialValue = 0.3f) },
        remember { Animatable(initialValue = 0.3f) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(animationDelay / circles.size * index.toLong())
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(animationDelay),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        circles.forEach { animation ->
            Image(
                painter = painterResource(image),
                contentDescription = "Loading image",
                modifier = Modifier
                    .size(imageSize)
                    .alpha(animation.value)
            )
            Spacer(modifier = Modifier.width(spaceBetween))
        }
    }
}