package com.ahuaman.pokedex.presentation.screens.home.viewmodel

import com.ahuaman.domain.model.PokemonPresentationModel

data class HomeState(
    val pokemonList: List<PokemonPresentationModel> = emptyList(),
    val isLoadingInitial: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null
)