package com.ahuaman.pokedex.presentation.screens.dashboard.models

import androidx.annotation.DrawableRes
import com.ahuaman.pokedex.R
import kotlinx.serialization.Serializable

@Serializable
sealed class ScreensDashboard(val title: String, @DrawableRes val icon: Int) {
    @Serializable
    data object Home :
        ScreensDashboard(title = "Inicio", icon = R.drawable.ic_home_filled)
    @Serializable
    data object Favorite :
        ScreensDashboard( title = "Favorites", icon = R.drawable.ic_favorite_filled)

    companion object {
        val items = listOf(Home, Favorite)
    }
}
