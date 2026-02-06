package com.ahuaman.pokedex.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.ahuaman.domain.model.PokemonPresentationModel
import com.ahuaman.pokedex.presentation.screens.home.components.PokemonItem
import com.ahuaman.testing.pokemons.PokemonListProvider
import com.ahuaman.testing.pokemons.PokemonMocks

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               pokemonList: List<PokemonPresentationModel> = emptyList()
               ) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 16.dp, top = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            items(pokemonList.count()) { index ->
                PokemonItem(
                    pokemon = pokemonList[index],
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        pokemonList = PokemonMocks.fullPokedexList
    )
}