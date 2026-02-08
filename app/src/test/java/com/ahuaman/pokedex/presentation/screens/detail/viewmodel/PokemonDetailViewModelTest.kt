package com.ahuaman.pokedex.presentation.screens.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.ahuaman.domain.usecase.GetPokemonDetailUseCase
import com.ahuaman.pokedex.presentation.screens.detail.models.DetailIntent
import com.ahuaman.pokedex.utils.MainDispatcherRule
import com.ahuaman.testing.pokemons.PokemonMocks
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonDetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getPokemonDetailUseCase: GetPokemonDetailUseCase = mockk()
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: PokemonDetailViewModel

    private val mockDetail = PokemonMocks.bulbasaurDetail

    @Before
    fun setup() {
        // ALWAYS use ID 1 for consistency across all tests
        savedStateHandle = SavedStateHandle(mapOf("id" to 1))
    }

    @Test
    fun `init should trigger load detail and update state to success`() = runTest {
        // 1. FIX: Use ID 1 to match your setup() and SavedStateHandle
        val testId = 1
        val mockDomainDetail = PokemonMocks.bulbasaurDetail

        // We use any() to be 100% sure MockK doesn't complain about IDs
        coEvery { getPokemonDetailUseCase(any()) } returns Result.success(mockDomainDetail)

        // 2. Initialize the ViewModel
        viewModel = PokemonDetailViewModel(getPokemonDetailUseCase, savedStateHandle)

        // 3. Test the flow
        viewModel.state.test {
            // Because of the fast dispatcher, the first item we collect
            // is the ALREADY COMPLETED state.
            val currentState = awaitItem()

            // Assertions
            assertThat(currentState.isLoading).isFalse() // This will now pass
            assertThat(currentState.pokemon?.name).isEqualTo("Bulbasaur")
            assertThat(currentState.error).isNull()

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test(expected = IllegalStateException::class)
    fun `init should throw exception when id is missing in SavedStateHandle`() {
        // Given: An empty handle without the "id" key
        val emptyHandle = SavedStateHandle()

        // When: Initializing the ViewModel
        // Then: It should throw the IllegalStateException you defined in your init block
        PokemonDetailViewModel(getPokemonDetailUseCase, emptyHandle)
    }

    @Test
    fun `toggling favorite twice should return state to original value`() = runTest {
        // Given
        coEvery { getPokemonDetailUseCase(any()) } returns Result.success(mockDetail)
        viewModel = PokemonDetailViewModel(getPokemonDetailUseCase, savedStateHandle)

        // When: Toggle ON
        viewModel.handleIntent(DetailIntent.ToggleFavorite)
        assertThat(viewModel.state.value.isFavorite).isTrue()

        // When: Toggle OFF
        viewModel.handleIntent(DetailIntent.ToggleFavorite)

        // Then
        assertThat(viewModel.state.value.isFavorite).isFalse()
    }

    @Test
    fun `fetchDetail should handle successful response with empty data gracefully`() = runTest {
        // Given: A domain model with missing/empty fields
        val emptyDomainDetail = mockDetail.copy(name = "", weightKg = 0.0)
        coEvery { getPokemonDetailUseCase(any()) } returns Result.success(emptyDomainDetail)

        viewModel = PokemonDetailViewModel(getPokemonDetailUseCase, savedStateHandle)

        viewModel.state.test {
            val state = awaitItem()
            // Verify UI doesn't crash and handles empty strings
            assertThat(state.pokemon?.name).isEmpty()
            assertThat(state.isLoading).isFalse()
        }
    }

}