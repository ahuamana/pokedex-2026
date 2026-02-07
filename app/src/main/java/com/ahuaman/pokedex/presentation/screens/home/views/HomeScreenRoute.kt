package com.ahuaman.pokedex.presentation.screens.home.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahuaman.domain.model.PokemonPresentationModel
import com.ahuaman.pokedex.presentation.screens.home.viewmodel.HomeViewModel

@Composable
fun HomeScreenRoute(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val pokemonItems = viewModel.pokemonPagingData.collectAsLazyPagingItems()

    val currentState = uiState.copy(
        pokemonList = pokemonItems.itemSnapshotList.items,
        isLoadingInitial = pokemonItems.loadState.refresh is LoadState.Loading,
        isLoadingMore = pokemonItems.loadState.append is LoadState.Loading
    )

    HomeScreenContent(
        state = currentState,
        onIntent = { intent -> viewModel.handleIntent(intent) },
        modifier = Modifier.fillMaxSize()
    )
}

