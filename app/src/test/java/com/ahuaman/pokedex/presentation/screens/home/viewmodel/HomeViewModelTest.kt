package com.ahuaman.pokedex.presentation.screens.home.viewmodel

import androidx.paging.PagingData
import app.cash.turbine.test
import com.ahuaman.domain.usecase.GetPokemonListUseCase
import com.ahuaman.pokedex.utils.MainDispatcherRule
import com.ahuaman.testing.pokemons.PokemonMocks
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getPokemonListUseCase: GetPokemonListUseCase = mockk()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        // We mock the UseCase to return an empty Flow of PagingData by default
        coEvery { getPokemonListUseCase() } returns flowOf(PagingData.empty())
    }

    @Test
    fun `init should trigger fetchPokemon and collect paging data`() = runTest {
        // Given
        val pagingData = PagingData.from(listOf(PokemonMocks.singlePokemon))
        coEvery { getPokemonListUseCase() } returns flowOf(pagingData)

        // When
        viewModel = HomeViewModel(getPokemonListUseCase)

        // Then
        viewModel.pokemonPagingData.test {
            // PagingData is a wrapper, so we verify it's not null/empty
            val item = awaitItem()
            assertThat(item).isNotNull()

            coVerify { getPokemonListUseCase() }
        }
    }

    @Test
    fun `handleIntent OnPokemonClick should emit NavigateToDetail effect`() = runTest {
        // Given
        viewModel = HomeViewModel(getPokemonListUseCase)
        val pokemonId = 25

        viewModel.effect.test {
            // When
            viewModel.handleIntent(HomeIntent.OnPokemonClick(pokemonId))

            // Then
            val effect = awaitItem()
            assertThat(effect).isInstanceOf(HomeEffect.NavigateToDetail::class.java)
            assertThat((effect as HomeEffect.NavigateToDetail).id).isEqualTo(pokemonId)
        }
    }

    @Test
    fun `handleIntent RetryLoading should trigger fetchPokemon again`() = runTest {
        // Given
        viewModel = HomeViewModel(getPokemonListUseCase)

        // When
        viewModel.handleIntent(HomeIntent.RetryLoading)

        // Then
        // Verified 2 times: once in init, once in handleIntent
        coVerify(exactly = 2) { getPokemonListUseCase() }
    }
}