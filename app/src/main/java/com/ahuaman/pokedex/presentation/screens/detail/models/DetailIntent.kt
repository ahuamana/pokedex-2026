package com.ahuaman.pokedex.presentation.screens.detail.models

sealed class DetailIntent {
    data class LoadDetail(val id: Int) : DetailIntent()
    data object ToggleFavorite : DetailIntent()
    data object OnBackClick : DetailIntent()
}