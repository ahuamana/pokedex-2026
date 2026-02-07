package com.ahuaman.data.mapper

import com.ahuaman.data.models.pokemon.PokemonDataModel
import com.ahuaman.domain.model.PokemonPresentationModel

fun PokemonDataModel.toDomain(): PokemonPresentationModel {
    val id = url.split("/").dropLast(1).lastOrNull()?.toInt() ?: 0
    return PokemonPresentationModel(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    )
}