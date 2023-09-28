package com.atb.pokedex.data.remote.dto.pokedex_detail

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)