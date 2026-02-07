package com.ahuaman.pokedex.presentation.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PokemonDetailScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text("Pokemon Detail Screen")
    }
}

@Preview
@Composable
private fun PokemonDetailScreenPrev() {
    PokemonDetailScreen(modifier = Modifier.fillMaxSize())
}