package com.ahuaman.pokedex.presentation.screens.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    Column() {
        Text("Favorite Screen")
    }
}

@Preview
@Composable
private fun FavoriteScreenPrev() {
    FavoriteScreen()
}