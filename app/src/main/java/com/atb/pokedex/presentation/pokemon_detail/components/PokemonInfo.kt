package com.atb.pokedex.presentation.pokemon_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PokemonInfo(
    modifier: Modifier = Modifier,
    name: String,
    types: List<String>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            types.forEach { type ->
                TypeItem(type = type)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
private fun TypeItem(
    modifier: Modifier = Modifier,
    type: String
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .width(100.dp)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = type,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}