package com.ahuaman.pokedex.presentation.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ahuaman.pokedex.presentation.screens.mapper.toPresentation
import com.ahuaman.pokedex.presentation.screens.models.PokemonPresentationModel
import com.ahuaman.testing.pokemons.PokemonMocks

@Composable
fun PokemonItem(pokemon: PokemonPresentationModel,
                modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(200.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(8.dp),

    ) {

        Text(modifier= Modifier.padding(16.dp).fillMaxWidth(),
            text = pokemon.name,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = null,
        )

    }
}


@Preview
@Composable
private fun PokemonItemPrev() {
    PokemonItem(
       pokemon = PokemonMocks.singlePokemon.toPresentation()
    )
}