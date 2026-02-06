package com.ahuaman.testing.pokemons

import com.ahuaman.domain.model.PokemonPresentationModel

object PokemonMocks {

    val singlePokemon = PokemonPresentationModel( name = "Pikachu",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"
    )

    val fullPokedexList = listOf(
        singlePokemon,
        PokemonPresentationModel(
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        ),
        PokemonPresentationModel(
            name = "Charmander",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"
        ),
        PokemonPresentationModel(
            name = "Squirtle",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"
        ),
        PokemonPresentationModel(
            name = "Jigglypuff",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/39.png"
        ),
        PokemonPresentationModel(
            name = "Meowth",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/52.png"
        ),
        PokemonPresentationModel(
            name = "Psyduck",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/54.png"
        ),
        PokemonPresentationModel(
            name = "Snorlax",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/143.png"
        ),

        PokemonPresentationModel(
            name = "Mewtwo",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/150.png"
        ),

        PokemonPresentationModel(
            name = "Eevee",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/133.png"
        ),

        PokemonPresentationModel(
            name = "Gengar",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png"
        ),

        PokemonPresentationModel(
            name = "Dragonite",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/149.png"
        ),
    )
}