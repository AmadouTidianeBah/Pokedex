package com.atb.pokedex.data.remote.dto.pokedex_detail

import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: BlackWhite
)