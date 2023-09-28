package com.atb.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.atb.pokedex.R

@Composable
fun FloatingButton(
    modifier: Modifier = Modifier,
    isSearchShowed: Boolean,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        shape = RoundedCornerShape(
            topStart = 12.dp, topEnd =  0.dp,
            bottomEnd = 12.dp, bottomStart =  0.dp
        ),
        modifier = modifier
    ) {
        Icon(
            imageVector = if(isSearchShowed) Icons.Outlined.Done else Icons.Outlined.Search,
            contentDescription = stringResource(R.string.search),
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(32.dp)
        )
    }
}