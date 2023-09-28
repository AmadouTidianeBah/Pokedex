package com.atb.pokedex.domain.model

import com.atb.pokedex.data.remote.dto.pokedex_detail.Ability
import com.atb.pokedex.data.remote.dto.pokedex_detail.Move
import com.atb.pokedex.data.remote.dto.pokedex_detail.Stat

data class Pokemon(
    val id: Int,
    val name: String,
    val imgUrl: String,
    val type: List<String>,
    val stats: List<Stat>,
    val abilities: List<Ability>,
    val height: Int,
    val weight: Int,
    val moves: List<Move>
)
