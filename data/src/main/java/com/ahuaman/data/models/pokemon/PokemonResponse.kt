package com.ahuaman.data.models.pokemon

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val results: List<PokemonDataModel>
)

@Serializable
data class PokemonDataModel(
    val name: String,
    val url: String
)