package com.ahuaman.domain.model

data class PokemonDetailPresentationModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weight: String, // "6.0 kg"
    val height: String, // "0.7 m"
    val types: List<String>,
    val stats: List<PokemonStat>
)

data class PokemonStat(
    val name: String,
    val value: Int
)