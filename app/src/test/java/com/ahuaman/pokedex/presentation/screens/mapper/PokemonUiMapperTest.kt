package com.ahuaman.pokedex.presentation.screens.mapper

import com.ahuaman.domain.model.PokemonDetailDomainModel
import com.ahuaman.domain.model.PokemonDomainModel
import com.ahuaman.domain.model.PokemonStat
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.*
import org.junit.Test

class PokemonUiMapperTest {

    @Test
    fun `toPresentation for List should capitalize name`() {
        // Given: A pure Domain model (raw data)
        val domainModel = PokemonDomainModel(
            id = 25,
            name = "pikachu",
            imageUrl = "https://example.com/25.png"
        )

        // When: Mapping to Presentation
        val uiModel = domainModel.toPresentation()

        // Then: The name must be capitalized for the UI
        assertThat(uiModel.name).isEqualTo("Pikachu")
        assertThat(uiModel.id).isEqualTo(25)
    }

    @Test
    fun `toPresentation for Detail should format units and types`() {
        // Given: A pure Domain model with numeric values
        val detailDomain = PokemonDetailDomainModel(
            id = 1,
            name = "bulbasaur",
            imageUrl = "...",
            weightKg = 6.9, // Pure double
            heightM = 0.7,  // Pure double
            types = listOf("grass", "poison"),
            stats = listOf(PokemonStat("hp", 45))
        )

        // When
        val uiModel = detailDomain.toPresentation()

        // Then: Check UI formatting
        assertThat(uiModel.name).isEqualTo("Bulbasaur")
        assertThat(uiModel.weight).isEqualTo("6.9 kg") // Unit added
        assertThat(uiModel.height).isEqualTo("0.7 m")   // Unit added
        assertThat(uiModel.types).containsExactly("Grass", "Poison") // Capitalized types
    }
}