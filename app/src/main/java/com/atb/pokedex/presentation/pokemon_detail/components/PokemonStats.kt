package com.atb.pokedex.presentation.pokemon_detail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.atb.pokedex.data.remote.dto.pokedex_detail.Stat

@Composable
fun PokemonStats(
    modifier: Modifier = Modifier,
    stats: List<Stat>
) {
    val maxStat = stats.maxOf { it.base_stat }
    var isAnimated by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        isAnimated = true
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        stats.forEach {stat ->
            val animationStat = animateFloatAsState(
                targetValue = if (isAnimated) stat.base_stat/maxStat.toFloat()
                else 0f,
                label = "stat animation",
                animationSpec = tween(1000)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = statName(stat.stat.name),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .weight(4f)
                ) {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(animationStat.value)
                        .clip(CircleShape)
                        .background(statColor(stat.stat.name)),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = "${stat.base_stat}/$maxStat",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun statName(name: String): String {
    return when(name) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DF"
        "special-attack" -> "SP-ATK"
        "special-defense" -> "SP-DF"
        "speed" -> "SP"
        else -> "UNKNOWN"
    }
}

private fun statColor(name: String): Color {
    return when(name) {
        "hp" -> Color(0xFF388E3C)
        "attack" -> Color(0xFFD32F2F)
        "defense" -> Color(0xFF1976D2)
        "special-attack" -> Color(0xFFF57C00)
        "special-defense" -> Color(0xFF512DA8)
        "speed" -> Color(0xFF083832)
        else -> Color.Cyan
    }
}