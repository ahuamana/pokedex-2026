package com.ahuaman.testing.pokemons

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ahuaman.domain.model.PokemonDomainModel

class PokemonListProvider : PreviewParameterProvider<List<PokemonDomainModel>> {
    override val values: Sequence<List<PokemonDomainModel>> = sequenceOf(
        PokemonMocks.fullPokedexList // Use the object you created earlier
    )
}