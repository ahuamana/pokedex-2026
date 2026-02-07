package com.ahuaman.pokedex.presentation.screens.home.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahuaman.domain.model.PokemonPresentationModel
import com.ahuaman.pokedex.presentation.screens.home.components.PokemonItem
import com.ahuaman.pokedex.presentation.screens.home.viewmodel.HomeIntent
import com.ahuaman.pokedex.presentation.screens.home.viewmodel.HomeState
import com.ahuaman.testing.pokemons.PokemonMocks
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreenContent(
    state: HomeState,
    pokemonItems: LazyPagingItems<PokemonPresentationModel>,
    onIntent: (HomeIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when{
            state.isLoadingInitial -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error != null -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Ocurrió un error: ${state.error}")
                    Button(onClick = { onIntent(HomeIntent.RetryLoading) }) {
                        Text("Reintentar")
                    }
                }
            }
            else -> {
                PokemonGrid(
                    state = state,
                    pokemonItems = pokemonItems,
                    modifier = modifier,
                    onIntent = onIntent
                )
            }

        }
    }
}


@Composable
fun PokemonGrid(state: HomeState,
                pokemonItems: LazyPagingItems<PokemonPresentationModel>,
                modifier: Modifier = Modifier,
                onIntent: (HomeIntent) -> Unit) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = pokemonItems.itemCount,
            key = { index -> pokemonItems[index]?.id ?: index }
        ) { index ->

            pokemonItems[index]?.let { pokemon ->

                PokemonItem(pokemon = pokemon, modifier = Modifier.clickable {
                    onIntent(HomeIntent.OnPokemonClick(pokemon.id))
                })
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
    val fakeData = PokemonMocks.fullPokedexList
    val pagingData = PagingData.from(fakeData)
    val fakePagingItems = flowOf(pagingData).collectAsLazyPagingItems()
    HomeScreenContent(
        state = HomeState(
            pokemonList = PokemonMocks.fullPokedexList,
            isLoadingInitial = false,
            isLoadingMore = false,
            error = null
        ),
        pokemonItems = fakePagingItems,
        onIntent =  {

        },
        modifier = Modifier.fillMaxSize()
    )

}