package com.atb.pokedex.data.remote.dto.pokedex_detail

import com.google.gson.annotations.SerializedName

data class GenerationVii(
    val icons: Icons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon
)