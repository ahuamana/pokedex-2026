package com.ahuaman.testing.pokemons

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ahuaman.domain.model.PokemonPresentationModel

class PokemonListProvider : PreviewParameterProvider<List<PokemonPresentationModel>> {
    override val values: Sequence<List<PokemonPresentationModel>> = sequenceOf(
        PokemonMocks.fullPokedexList // Use the object you created earlier
    )
}