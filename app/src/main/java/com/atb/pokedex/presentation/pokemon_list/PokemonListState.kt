package com.atb.pokedex.presentation.pokemon_list

import com.atb.pokedex.domain.model.Pokemon

data class PokemonListState(
    val pokemon: Pokemon? = null,
    val isLoading: Boolean = false,
    val error: Boolean = false
)
