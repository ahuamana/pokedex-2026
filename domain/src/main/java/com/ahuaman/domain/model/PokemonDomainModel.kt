package com.ahuaman.domain.model

data class PokemonDomainModel(
    val id: Int,
    val name: String,
    val imageUrl: String
)

data class PokemonDetailDomainModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weightKg: Double, // Pure data: 6.9
    val heightM: Double,  // Pure data: 0.7
    val types: List<String>,
    val stats: List<PokemonStat>
)

data class PokemonStat(
    val name: String,
    val value: Int
)