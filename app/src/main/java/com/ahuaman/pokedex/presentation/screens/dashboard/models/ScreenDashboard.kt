package com.ahuaman.pokedex.presentation.screens.dashboard.models

import androidx.annotation.DrawableRes
import com.ahuaman.pokedex.R
import kotlinx.serialization.Serializable
@Serializable
sealed class ScreensDashboard {
    // These are not "arguments", just properties we can access
    abstract val title: String
    abstract val icon: Int

    @Serializable
    data object Home : ScreensDashboard() {
        override val title: String = "Inicio"
        override val icon: Int = R.drawable.ic_home_filled
    }

    @Serializable
    data object Favorite : ScreensDashboard() {
        override val title: String = "Favorites"
        override val icon: Int = R.drawable.ic_favorite_filled
    }

    @Serializable
    data class Detail(val id: Int) : ScreensDashboard() {
        override val title: String = "Detail"
        override val icon: Int = R.drawable.ic_home_filled
    }

    companion object {
        val items = listOf(Home, Favorite)
    }
}
