package com.ahuaman.pokedex.presentation.screens.detail.models

import com.ahuaman.pokedex.presentation.screens.home.viewmodel.HomeEffect

sealed class DetailEffect {
    object  OnClickArrowBack : DetailEffect()
}