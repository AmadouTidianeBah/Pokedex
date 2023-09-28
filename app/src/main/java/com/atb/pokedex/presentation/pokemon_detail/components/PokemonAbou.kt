package com.atb.pokedex.presentation.pokemon_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AllInclusive
import androidx.compose.material.icons.outlined.Balance
import androidx.compose.material.icons.outlined.Height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.atb.pokedex.data.remote.dto.pokedex_detail.Ability

@Composable
fun PokemonAbout(
    modifier: Modifier = Modifier,
    height: String,
    weight: String,
    abilities: List<Ability>
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AboutItem(title = "HEIGHT", icon = Icons.Outlined.Height) {
            Text(text = height)
        }
        AboutItem(title = "WEIGHT", icon = Icons.Outlined.Balance) {
            Text(text = weight)
        }
        AboutItem(title = "ABILITIES", icon = Icons.Outlined.AllInclusive) {
            abilities.forEach {
                AbilityItem(ability = it.ability.name)
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

@Composable
private fun AboutItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    content: @Composable() () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.weight(1.5f)
        ){
            Icon(
                imageVector = icon,
                contentDescription = title
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(modifier = Modifier.weight(3f)){ content() }
    }
}

@Composable
private fun AbilityItem(
    modifier: Modifier = Modifier,
    ability: String
) {
    Box(
        modifier = modifier
            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = ability,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            softWrap = false
        )
    }
}