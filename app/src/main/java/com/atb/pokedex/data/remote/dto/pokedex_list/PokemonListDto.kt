package com.atb.pokedex.data.remote.dto.pokedex_list

data class PokemonListDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)