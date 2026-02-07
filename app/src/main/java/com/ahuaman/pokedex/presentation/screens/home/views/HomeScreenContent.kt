package com.ahuaman.pokedex.presentation.screens.home.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahuaman.domain.model.PokemonPresentationModel
import com.ahuaman.pokedex.presentation.screens.home.components.PokemonItem
import com.ahuaman.pokedex.presentation.screens.home.viewmodel.HomeIntent
import com.ahuaman.pokedex.presentation.screens.home.viewmodel.HomeState
import com.ahuaman.testing.pokemons.PokemonMocks

@Composable
fun HomeScreenContent(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if(state.isLoadingInitial) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if(state.error != null) {
            Button(onClick = { onIntent(HomeIntent.RetryLoading) }) {
                Text("Reintentar")
            }
        } else {
            PokemonGrid(state = state, pokemonItems = state.pokemonList)
        }

    }
}


@Composable
fun PokemonGrid(state: HomeState,pokemonItems: List<PokemonPresentationModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(bottom = 16.dp, top = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = pokemonItems.count(),
            key = { index -> pokemonItems[index]?.id ?: index }) { index ->
            pokemonItems[index]?.let { pokemon ->
                PokemonItem(pokemon = pokemon)
            }
        }

        if(state.isLoadingMore) {
            item(span = { GridItemSpan(2) }) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    HomeScreenContent(
        state = HomeState(
            pokemonList = PokemonMocks.fullPokedexList,
            isLoadingInitial = false,
            isLoadingMore = false,
            error = null
        ),
        onIntent =  {

        },
        modifier = Modifier.fillMaxSize()
    )
}