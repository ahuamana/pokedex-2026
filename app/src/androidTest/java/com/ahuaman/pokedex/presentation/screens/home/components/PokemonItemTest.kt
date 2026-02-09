package com.ahuaman.pokedex.presentation.screens.home.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.ahuaman.pokedex.presentation.screens.models.PokemonPresentationModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class PokemonItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pokemonItem_displaysNameAndImage() {
        // Given
        val pokemon = PokemonPresentationModel(
            id = 1,
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        )

        // When
        composeTestRule.setContent {
            PokemonItem(pokemon = pokemon)
        }

        // Then: Verify the name is displayed
        composeTestRule.onNodeWithText("Bulbasaur").assertIsDisplayed()

        // Verify the image exists via its accessibility description
        composeTestRule.onNodeWithContentDescription("Image of Bulbasaur").assertExists()
    }
}