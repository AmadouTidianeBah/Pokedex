package com.atb.pokedex.data.remote.dto.pokedex_detail

import com.atb.pokedex.domain.model.Pokemon

data class PokemonDetailDto(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {
    fun toPokemon(): Pokemon {
        return Pokemon(
            id = id,
            name = name,
            imgUrl = sprites.other.officialArtwork.front_default,
            type = types.map { it.type.name },
            stats = stats,
            abilities = abilities,
            height = height,
            weight = weight,
            moves = moves
        )
    }
}