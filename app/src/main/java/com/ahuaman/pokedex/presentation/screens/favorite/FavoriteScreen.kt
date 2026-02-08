package com.ahuaman.pokedex.presentation.screens.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahuaman.pokedex.presentation.screens.models.PokemonPresentationModel
import com.ahuaman.pokedex.presentation.screens.home.components.PokemonItem
import com.ahuaman.pokedex.presentation.screens.mapper.toPresentation
import com.ahuaman.testing.pokemons.PokemonMocks

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    favoritePokemonList: List<PokemonPresentationModel> = emptyList()
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
            items(favoritePokemonList.count()) { index ->
                PokemonItem(
                    pokemon = favoritePokemonList[index],
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Preview
@Composable
private fun FavoriteScreenPrev() {
    FavoriteScreen(
        modifier = Modifier.fillMaxSize(),
        favoritePokemonList = PokemonMocks.fullPokedexList.map { it.toPresentation() }
    )
}