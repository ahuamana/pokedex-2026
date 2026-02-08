package com.ahuaman.pokedex.presentation.screens.detail.models

import com.ahuaman.pokedex.presentation.screens.models.PokemonDetailPresentationModel

data class DetailState(
    val pokemon: PokemonDetailPresentationModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isFavorite: Boolean = false
)
