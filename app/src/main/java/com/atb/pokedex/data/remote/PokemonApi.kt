package com.atb.pokedex.data.remote

import com.atb.pokedex.data.remote.dto.pokedex_detail.PokemonDetailDto
import com.atb.pokedex.data.remote.dto.pokedex_list.PokemonListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListDto

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailDto

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}