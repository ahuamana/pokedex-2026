package com.ahuaman.pokedex.presentation.screens.mapper

import com.ahuaman.domain.model.PokemonDetailDomainModel
import com.ahuaman.domain.model.PokemonDomainModel
import com.ahuaman.domain.model.PokemonStat
import com.ahuaman.pokedex.presentation.screens.models.PokemonDetailPresentationModel
import com.ahuaman.pokedex.presentation.screens.models.PokemonPresentationModel
import com.ahuaman.pokedex.presentation.screens.models.PokemonStatPresentationModel

fun PokemonDetailDomainModel.toPresentation(): PokemonDetailPresentationModel {
    return PokemonDetailPresentationModel(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        imageUrl = imageUrl,
        weight = "$weightKg kg", // Formatting stays in UI Layer
        height = "$heightM m",   // Formatting stays in UI Layer
        types = types.map { it.replaceFirstChar { c -> c.uppercase() } },
        stats = stats.map { it.toPresentation() }
    )
}

fun PokemonStat.toPresentation(): PokemonStatPresentationModel {
    return PokemonStatPresentationModel(
        name = name.replaceFirstChar { it.uppercase() },
        value = value
    )
}

fun PokemonDomainModel.toPresentation(): PokemonPresentationModel {
    return PokemonPresentationModel(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        imageUrl = imageUrl
    )
}