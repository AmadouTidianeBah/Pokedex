package com.atb.pokedex.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.atb.pokedex.R

@Composable
fun PokemonError(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.pikachu),
            contentDescription = "pikachu"
        )
        Text(
            text = stringResource(R.string.check_your_connection_please),
            textAlign = TextAlign.Center
        )
    }
}