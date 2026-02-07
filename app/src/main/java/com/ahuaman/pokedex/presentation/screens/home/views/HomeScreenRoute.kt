package com.ahuaman.pokedex.presentation.screens.home.views

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    LaunchedEffect(pokemonItems.loadState) {
        Log.d("PagingDebug", "Refresh: ${pokemonItems.loadState.refresh}")
        Log.d("PagingDebug", "Append: ${pokemonItems.loadState.append}")
    }

    val currentState = uiState.copy(
        pokemonList = pokemonItems.itemSnapshotList.items,
        isLoadingInitial = pokemonItems.loadState.refresh is LoadState.Loading,
        isLoadingMore = pokemonItems.loadState.append is LoadState.Loading
    )

    HomeScreenContent(
        state = currentState,
        pokemonItems = pokemonItems,
        onIntent = { intent -> viewModel.handleIntent(intent) },
        modifier = Modifier.fillMaxSize()
    )
}

