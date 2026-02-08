package com.ahuaman.data.mapper

import com.ahuaman.data.models.pokemon.PokemonDetailResponse
import com.ahuaman.domain.model.PokemonDetailDomainModel
import com.ahuaman.domain.model.PokemonStat

fun PokemonDetailResponse.toDomain(): PokemonDetailDomainModel {
    return PokemonDetailDomainModel(
        id = id,
        name = name,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
        weightKg = weight / 10.0, // Logic stays in Data Layer
        heightM = height / 10.0,  // Logic stays in Data Layer
        types = types.map { it.type.name },
        stats = stats.map { PokemonStat(name = it.stat.name, value = it.baseStat) }
    )
}