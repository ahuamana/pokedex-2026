package com.ahuaman.pokedex.presentation.screens.models

data class PokemonDetailPresentationModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weight: String, // "6.0 kg"
    val height: String, // "0.7 m"
    val types: List<String>,
    val stats: List<PokemonStatPresentationModel>
)

data class PokemonStatPresentationModel(
    val name: String,
    val value: Int
)