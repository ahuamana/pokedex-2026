package com.ahuaman.pokedex.presentation.screens.detail.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ahuaman.pokedex.presentation.screens.detail.models.DetailIntent
import com.ahuaman.pokedex.presentation.screens.detail.models.DetailState
import com.ahuaman.pokedex.ui.theme.PokedexTheme
import com.ahuaman.testing.pokemons.PokemonDetailMocks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    state: DetailState,
    onIntent: (DetailIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            PokedexCustomTopBar(
                title = state.pokemon?.name ?: "Detalle del Pokémon",
                onBackClick = { onIntent(DetailIntent.OnBackClick) },
                isFavorite = state.isFavorite,
                onFavoriteToggle = { onIntent(DetailIntent.ToggleFavorite) }
            )
        }
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text(text = state.error)
                state.pokemon != null -> {
                    PokemonDetailContent(pokemon = state.pokemon)
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Success State")
@Composable
private fun DetailSuccessPreview() {
    PokedexTheme {
        PokemonDetailScreen(
            state = DetailState(
                pokemon = PokemonDetailMocks.bulbasaurDetail,
                isLoading = false,
                isFavorite = true // Probamos cómo se ve con el corazón lleno
            ),
            onIntent = {}
        )
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
private fun DetailLoadingPreview() {
    PokedexTheme {
        PokemonDetailScreen(
            state = DetailState(isLoading = true),
            onIntent = {}
        )
    }
}

@Preview(showBackground = true, name = "Error State")
@Composable
private fun DetailErrorPreview() {
    PokedexTheme {
        PokemonDetailScreen(
            state = DetailState(
                error = "No se pudo conectar con el servidor",
                isLoading = false
            ),
            onIntent = {}
        )
    }
}

