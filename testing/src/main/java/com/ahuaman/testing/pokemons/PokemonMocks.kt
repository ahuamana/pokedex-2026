package com.ahuaman.testing.pokemons

import com.ahuaman.domain.model.PokemonDetailDomainModel
import com.ahuaman.domain.model.PokemonDomainModel
import com.ahuaman.domain.model.PokemonStat

object PokemonMocks {

    val singlePokemon = PokemonDomainModel(
        id = 25,
        name = "Pikachu",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"
    )

    val fullPokedexList = listOf(
        singlePokemon,
        PokemonDomainModel(
            id = 1,
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        ),
        PokemonDomainModel(
            id = 4,
            name = "Charmander",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"
        ),
        PokemonDomainModel(
            id = 7,
            name = "Squirtle",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"
        ),
        PokemonDomainModel(
            id = 39,
            name = "Jigglypuff",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/39.png"
        ),
        PokemonDomainModel(
            id = 52,
            name = "Meowth",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/52.png"
        ),
        PokemonDomainModel(
            id = 54,
            name = "Psyduck",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/54.png"
        ),
        PokemonDomainModel(
            id = 143,
            name = "Snorlax",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/143.png"
        ),

        PokemonDomainModel(
            id = 150,
            name = "Mewtwo",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/150.png"
        ),

        PokemonDomainModel(
            id= 133,
            name = "Eevee",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/133.png"
        ),

        PokemonDomainModel(
            id = 94,
            name = "Gengar",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png"
        ),

        PokemonDomainModel(
            id = 149,
            name = "Dragonite",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/149.png"
        ),
    )

    val bulbasaurDetail = PokemonDetailDomainModel(
        id = 1,
        name = "bulbasaur",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        weightKg = 6.9,
        heightM = 0.7,
        types = listOf("grass", "poison"),
        stats = listOf(
            PokemonStat("hp", 45),
            PokemonStat("attack", 49),
            PokemonStat("defense", 49)
        )
    )
}