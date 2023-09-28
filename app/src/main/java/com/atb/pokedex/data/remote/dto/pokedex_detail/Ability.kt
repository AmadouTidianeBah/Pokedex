package com.atb.pokedex.data.remote.dto.pokedex_detail

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)