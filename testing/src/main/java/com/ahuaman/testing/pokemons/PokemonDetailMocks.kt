package com.ahuaman.testing.pokemons

import com.ahuaman.domain.model.PokemonDetailPresentationModel
import com.ahuaman.domain.model.PokemonStat

object PokemonDetailMocks {
    val bulbasaurDetail = PokemonDetailPresentationModel(
        id = 1,
        name = "Bulbasaur",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        weight = "6.9 kg",
        height = "0.7 m",
        types = listOf("Grass", "Poison"),
        stats = listOf(
            PokemonStat("hp", 45),
            PokemonStat("attack", 49),
            PokemonStat("defense", 49)
        )
    )
}