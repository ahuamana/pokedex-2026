package com.ahuaman.data.mapper

import com.ahuaman.data.models.pokemon.PokemonDataModel
import com.ahuaman.data.models.pokemon.PokemonDetailResponse
import com.ahuaman.data.models.pokemon.StatInfoResponse
import com.ahuaman.data.models.pokemon.StatResponse
import com.ahuaman.data.models.pokemon.TypeResponse
import com.ahuaman.data.models.pokemon.TypeSlotResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PokemonMapperTest {

    @Test
    fun `toDomain should extract ID and format image URL correctly`() {
        // Given: A raw API model with a typical PokeAPI URL
        val dataModel = PokemonDataModel(
            name = "bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        )

        // When: We map it to the Domain
        val domainModel = dataModel.toDomain()

        // Then: The ID must be 1 and the name remains raw
        assertThat(domainModel.id).isEqualTo(1)
        assertThat(domainModel.name).isEqualTo("bulbasaur")
        assertThat(domainModel.imageUrl).contains("1.png")
    }

    @Test
    fun `toDomain for Detail should convert units correctly`() {
        // Given: weight is 69 (hectograms) and height is 7 (decimeters)
        val response = PokemonDetailResponse(
            id = 1,
            name = "bulbasaur",
            weight = 69,
            height = 7,
            types = listOf(),
            stats = listOf()
        )

        // When
        val domain = response.toDomain()

        // Then: Verify mathematical conversion
        assertThat(domain.weightKg).isEqualTo(6.9)
        assertThat(domain.heightM).isEqualTo(0.7)
    }

    @Test
    fun `toDomain should handle URL without trailing slash`() {
        // Scenario: API changes format from ".../1/" to ".../1"
        val dataModel = PokemonDataModel(name = "pichu", url = "https://pokeapi.co/api/v2/pokemon/172")

        val domain = dataModel.toDomain()

        // If your current logic uses .dropLast(1), this might fail.
        // A senior fix would be url.trimEnd('/').split('/').last().toInt()
        assertThat(domain.id).isEqualTo(172)
    }

    @Test
    fun `toDomain should return ID 0 when URL is malformed`() {
        val dataModel = PokemonDataModel(name = "unknown", url = "not-a-url")

        val domain = dataModel.toDomain()

        assertThat(domain.id).isEqualTo(0)
    }

    @Test
    fun `toDomain should keep name in raw lowercase`() {
        val dataModel = PokemonDataModel(name = "BULBASAUR", url = ".../1/")

        val domain = dataModel.toDomain()

        // In Data layer, we keep it raw. UI layer (Presentation) will fix the casing.
        assertThat(domain.name).isEqualTo("BULBASAUR")
    }

    @Test
    fun `toDomain for Detail should map types and stats`() {
        val response = PokemonDetailResponse(
            id = 1,
            name = "bulbasaur",
            weight = 69,
            height = 7,
            types = listOf(TypeSlotResponse(1, TypeResponse("grass"))),
            stats = listOf(StatResponse(45, StatInfoResponse("hp")))
        )

        val domain = response.toDomain()

        // These assertions force the coverage tool to execute the list mapping logic
        assertThat(domain.types).containsExactly("grass")
        assertThat(domain.stats.first().name).isEqualTo("hp")
    }
}