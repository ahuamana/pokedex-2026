package com.ahuaman.data.mapper

import com.ahuaman.data.models.pokemon.PokemonDetailResponse
import com.ahuaman.domain.model.PokemonDetailDomainModel
import com.ahuaman.domain.model.PokemonStat

fun PokemonDetailResponse.toDomain(): PokemonDetailDomainModel {
    return PokemonDetailDomainModel(
        id = id,
        name = name,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
        weightKg = weight / 10.0,
        heightM = height / 10.0,
        // Senior Move: Sanitize the data at the boundary
        types = types?.mapNotNull { it.type?.name }.orEmpty(),
        stats = stats?.mapNotNull {
            val statName = it.stat?.name ?: "unknown"
            PokemonStat(name = statName, value = it.baseStat)
        }.orEmpty()
    )
}