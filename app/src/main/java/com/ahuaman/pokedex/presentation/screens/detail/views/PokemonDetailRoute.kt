package com.ahuaman.pokedex.presentation.screens.detail.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahuaman.pokedex.presentation.screens.detail.models.DetailEffect
import com.ahuaman.pokedex.presentation.screens.detail.models.DetailIntent
import com.ahuaman.pokedex.presentation.screens.detail.viewmodel.PokemonDetailViewModel
import com.ahuaman.pokedex.presentation.screens.home.viewmodel.HomeEffect

@Composable
fun PokemonDetailRoute(viewModel: PokemonDetailViewModel = hiltViewModel(),
                       onBackClick: () ->Unit) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                DetailEffect.OnClickArrowBack -> {
                        onBackClick()
                }
            }
        }
    }

    PokemonDetailScreen(
        state = state,
        onIntent = { intent ->
            when (intent) {
                is DetailIntent.OnBackClick -> onBackClick()
                else -> viewModel.handleIntent(intent)
            }
        },
        modifier = Modifier
    )

}