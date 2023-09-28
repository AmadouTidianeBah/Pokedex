package com.atb.pokedex.domain.repository

import com.atb.pokedex.core.utils.Resource
import com.atb.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int, limit: Int): List<Pokemon>
    suspend fun getPokemonDetail(name: String): Pokemon
}