package com.ahuaman.data.models.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val types: List<TypeSlotResponse>,
    val stats: List<StatResponse>
)

@Serializable
data class TypeSlotResponse(
    val slot: Int,
    val type: TypeResponse
)

@Serializable
data class TypeResponse(
    val name: String
)

@Serializable
data class StatResponse(
    @SerialName("base_stat") val baseStat: Int,
    val stat: StatInfoResponse
)

@Serializable
data class StatInfoResponse(
    val name: String
)
