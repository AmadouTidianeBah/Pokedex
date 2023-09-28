@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.atb.pokedex.presentation.pokemon_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.atb.pokedex.R
import com.atb.pokedex.core.components.PokemonError
import com.atb.pokedex.core.components.PokemonTopBar
import com.atb.pokedex.core.utils.LoadingAnimationAlpha
import com.atb.pokedex.core.utils.LoadingAnimationScale
import com.atb.pokedex.presentation.pokemon_list.components.FloatingButton
import com.atb.pokedex.presentation.pokemon_list.components.PokemonListItem
import com.atb.pokedex.presentation.pokemon_list.components.PokemonListSearch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel(),
    navController: NavController
) {
    val data = viewModel.data.collectAsLazyPagingItems()
    val state by viewModel.state.collectAsState()
    val loadState = data.loadState
    var query by rememberSaveable {
        mutableStateOf("")
    }
    var pokemonColor by rememberSaveable {
        mutableIntStateOf(-1)
    }
    var isSearchShowed by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            PokemonTopBar(text = stringResource(id = R.string.pokemon))
        },
        floatingActionButton = {
            FloatingButton(isSearchShowed = isSearchShowed) {
                isSearchShowed = !isSearchShowed
            }
        },
        modifier = modifier
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Center
        ) {
            if (isSearchShowed){
                PokemonListSearch(
                    query = query,
                    onQueryChange = {
                        query = it
                        viewModel.getPokemon(query)
                    },
                    onSearch = {
                        if (state.pokemon != null) {
                            navController.navigate("pokemon_detail/${state.pokemon!!.name}/$pokemonColor")
                        }
                        query = ""
                        viewModel.updatePokemon()
                        isSearchShowed = false
                    },
                    active = isSearchShowed,
                    onActiveChange = {
                        isSearchShowed = it
                        query = ""
                        viewModel.updatePokemon()
                    },
                    updateSearch = {
                        query = ""
                        isSearchShowed = false
                    },
                    pokemonColor = {color ->
                        pokemonColor = color
                    },
                    navController = navController,
                    viewModel = viewModel
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(data.itemCount) {index ->
                    data[index]?.let { PokemonListItem(
                        pokemon = it,
                        onPokemonClick = {name, color ->
                            navController.navigate("pokemon_detail/$name/$color")
                        })
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    when(loadState.append) {
                        is LoadState.Error -> {
                            Text(
                                text = stringResource(R.string.check_your_connection_please),
                                modifier = Modifier.align(Center)
                            )
                        }
                        is LoadState.Loading -> {
                            LoadingAnimationAlpha()
                        }
                        is LoadState.NotLoading -> Unit
                    }
                }
            }

            when(loadState.refresh) {
                is LoadState.Error -> {
                    PokemonError()
                }
                is LoadState.Loading -> {
                    LoadingAnimationScale()
                }
                is LoadState.NotLoading -> Unit
            }
        }
    }
}




