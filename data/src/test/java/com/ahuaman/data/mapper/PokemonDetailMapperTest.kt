package com.ahuaman.data.mapper

import com.ahuaman.data.models.pokemon.PokemonDetailResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PokemonDetailMapperTest {

    @Test
    fun `toDomain should handle large values for weight and height`() {
        // Testing boundary values ensures no overflow or precision loss
        val response = PokemonDetailResponse(
            id = 1,
            name = "snorlax",
            weight = 4600, // 460.0 kg
            height = 21,   // 2.1 m
            types = emptyList(),
            stats = emptyList()
        )

        val domain = response.toDomain()

        assertThat(domain.weightKg).isEqualTo(460.0)
        assertThat(domain.heightM).isEqualTo(2.1)
    }

    @Test
    fun `toDomain should handle null or missing fields gracefully`() {
        // If your response allows nullable types (common in GSON/Serialization),
        // test that the mapper provides safe defaults like emptyList()
        val response = PokemonDetailResponse(
            id = 1,
            name = "missingno",
            weight = 0,
            height = 0,
            types = null,
            stats = emptyList()
        )

        val domain = response.toDomain()

        assertThat(domain.types).isEmpty() // Ensures .orEmpty() was used
    }
}