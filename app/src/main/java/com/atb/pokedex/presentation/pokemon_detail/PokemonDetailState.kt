package com.atb.pokedex.presentation.pokemon_detail

import com.atb.pokedex.domain.model.Pokemon

data class PokemonDetailState(
    val pokemon: Pokemon? = null,
    val isLoading: Boolean = false,
    val error: Boolean = false
)
