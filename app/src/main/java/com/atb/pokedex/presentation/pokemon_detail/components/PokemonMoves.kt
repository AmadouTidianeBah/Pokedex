package com.atb.pokedex.presentation.pokemon_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.atb.pokedex.data.remote.dto.pokedex_detail.Move

@Composable
fun PokemonMoves(
    modifier: Modifier = Modifier,
    moves: List<Move>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(moves) {move ->
            MovesItem(move = move.move.name)
        }
    }
}

@Composable
private fun MovesItem(
    modifier: Modifier = Modifier,
    move: String
) {
    Box(
        modifier = modifier
            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = move,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge
        )
    }
}
