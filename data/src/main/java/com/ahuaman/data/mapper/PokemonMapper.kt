package com.ahuaman.data.mapper

import com.ahuaman.data.models.pokemon.PokemonDataModel
import com.ahuaman.domain.model.PokemonDomainModel

fun PokemonDataModel.toDomain(): PokemonDomainModel {
    val rawUrl = url.orEmpty()
    val rawName = name.orEmpty()

    val id = rawUrl.trimEnd('/')
        .split('/')
        .lastOrNull()
        ?.toIntOrNull() ?: 0

    return PokemonDomainModel(
        id = id,
        name = rawName,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    )
}