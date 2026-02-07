package com.ahuaman.pokedex.presentation.screens.home.viewmodel

sealed class HomeIntent {
    data object LoadPokemon : HomeIntent()
    data class OnPokemonClick(val id: Int) : HomeIntent()
    data object RetryLoading : HomeIntent()
}