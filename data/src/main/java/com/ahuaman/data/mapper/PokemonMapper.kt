package com.ahuaman.data.mapper

import com.ahuaman.data.models.pokemon.PokemonDataModel
import com.ahuaman.domain.model.PokemonDomainModel

fun PokemonDataModel.toDomain(): PokemonDomainModel {
    val id = url.split("/").filter { it.isNotEmpty() }.lastOrNull()?.toInt() ?: 0
    return PokemonDomainModel(
        id = id,
        name = name, // Keep it raw; UI will capitalize
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    )
}