@file:OptIn(ExperimentalMaterial3Api::class)

package com.atb.pokedex.core.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopBar(
    modifier: Modifier = Modifier,
    text: String,
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    )
}