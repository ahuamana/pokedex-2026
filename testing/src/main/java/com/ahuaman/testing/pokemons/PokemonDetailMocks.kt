package com.ahuaman.testing.pokemons

import com.ahuaman.domain.model.PokemonDetailDomainModel
import com.ahuaman.domain.model.PokemonStat

object PokemonDetailMocks {
    val bulbasaurDetail = PokemonDetailDomainModel(
        id = 1,
        name = "Bulbasaur",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        weightKg = 6.9,
        heightM = 0.7,
        types = listOf("Grass", "Poison"),
        stats = listOf(
            PokemonStat("hp", 45),
            PokemonStat("attack", 49),
            PokemonStat("defense", 49)
        )
    )
}