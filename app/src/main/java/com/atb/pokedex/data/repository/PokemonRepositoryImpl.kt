package com.atb.pokedex.data.repository

import com.atb.pokedex.data.remote.PokemonApi
import com.atb.pokedex.domain.model.Pokemon
import com.atb.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi
) : PokemonRepository {
    override suspend fun getPokemonList(offset: Int, limit: Int): List<Pokemon> {
        val data = api.getPokemonList(offset, limit)
        return data.results.map {
            api.getPokemonDetail(it.name).toPokemon()
        }
    }

    override suspend fun getPokemonDetail(name: String): Pokemon {
        val data = api.getPokemonDetail(name)
        return data.toPokemon()
    }
}