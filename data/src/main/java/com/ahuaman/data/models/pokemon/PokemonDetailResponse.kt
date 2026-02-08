package com.ahuaman.data.models.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    // Add '?' to make them nullable
    val types: List<TypeSlotResponse>? = null,
    val stats: List<StatResponse>? = null
)

@Serializable
data class TypeSlotResponse(
    val slot: Int,
    // Use nullable types for nested objects too
    val type: TypeResponse? = null
)

@Serializable
data class TypeResponse(
    val name: String? = null
)

@Serializable
data class StatResponse(
    @SerialName("base_stat") val baseStat: Int,
    val stat: StatInfoResponse? = null
)

@Serializable
data class StatInfoResponse(
    val name: String? = null
)
