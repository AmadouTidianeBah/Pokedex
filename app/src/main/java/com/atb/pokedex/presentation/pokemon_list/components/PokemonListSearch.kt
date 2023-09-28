@file:OptIn(ExperimentalMaterial3Api::class)

package com.atb.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.atb.pokedex.R
import com.atb.pokedex.core.components.PokemonError
import com.atb.pokedex.core.utils.LoadingAnimationAlpha
import com.atb.pokedex.presentation.pokemon_list.PokemonListViewModel

@Composable
fun PokemonListSearch(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    updateSearch: () -> Unit,
    pokemonColor: (Int) -> Unit,
    navController: NavController,
    viewModel: PokemonListViewModel
) {
    val state by viewModel.state.collectAsState()
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        placeholder = { Text(text = stringResource(R.string.charmander)) },
        modifier = modifier
    ) {
        if (state.pokemon != null) {
            PokemonListItem(
                pokemon = state.pokemon!!,
                onPokemonClick = { name, color ->
                    navController.navigate("pokemon_detail/$name/$color")
                    viewModel.updatePokemon()
                    updateSearch()
                },
                pokemonColor = {color ->
                    pokemonColor(color)
                },
                modifier = Modifier.padding(8.dp)
            )
        }
        if (state.isLoading) LoadingAnimationAlpha(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 50.dp)
        )
        if (state.error) PokemonError(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 50.dp)
        )
    }
}