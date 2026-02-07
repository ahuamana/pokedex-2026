package com.ahuaman.pokedex.presentation.navigation.models

import kotlinx.serialization.Serializable

sealed class ScreensRoot{
    @Serializable
    data object AuthenticationScreen : ScreensRoot()

    @Serializable
    data object DashboardScreen : ScreensRoot()
}