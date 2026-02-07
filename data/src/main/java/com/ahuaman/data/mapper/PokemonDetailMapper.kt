package com.ahuaman.data.mapper

import com.ahuaman.data.models.pokemon.PokemonDetailResponse
import com.ahuaman.domain.model.PokemonDetailPresentationModel
import com.ahuaman.domain.model.PokemonPresentationModel
import com.ahuaman.domain.model.PokemonStat

fun PokemonDetailResponse.toDomain(): PokemonDetailPresentationModel {
    return PokemonDetailPresentationModel(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
        weight = "${weight / 10.0} kg", // The API delivers weight in hectograms, so we convert it to kilograms
        height = "${height / 10.0} m",   // The API delivers height in decimeters, so we convert it to meters
        types = types.map { it.type.name },
        stats = stats.map {
            PokemonStat(name = it.stat.name, value = it.baseStat)
        }
    )
}