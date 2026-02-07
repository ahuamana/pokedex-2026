package com.ahuaman.pokedex.presentation.screens.home.viewmodel

sealed class HomeEffect {
    data class NavigateToDetail(val id: Int) : HomeEffect()
}