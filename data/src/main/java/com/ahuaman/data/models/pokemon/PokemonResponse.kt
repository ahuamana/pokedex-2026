package com.ahuaman.data.models.pokemon

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val results: List<PokemonDataModel>? = null // Nullable for safety
)

@Serializable
data class PokemonDataModel(
    val name: String? = null, // Even name/url can be missing in corrupt data
    val url: String? = null
)