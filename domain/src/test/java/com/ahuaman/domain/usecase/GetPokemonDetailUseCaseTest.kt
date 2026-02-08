package com.ahuaman.domain.usecase

import com.ahuaman.domain.repository.PokemonRepository
import com.ahuaman.testing.pokemons.PokemonMocks
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPokemonDetailUseCaseTest {
    private val repository: PokemonRepository = mockk()
    private lateinit var useCase: GetPokemonDetailUseCase

    @Before
    fun setup() {
        useCase = GetPokemonDetailUseCase(repository)
    }

    @Test
    fun `invoke should return success result when repository succeeds`() = runTest {
        // Given
        val pokemonId = 1
        val expectedPokemon = PokemonMocks.bulbasaurDetail // From your :testing module
        coEvery { repository.getPokemonDetail(pokemonId) } returns Result.success(expectedPokemon)

        // When
        val result = useCase(pokemonId)

        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(expectedPokemon)
        coVerify(exactly = 1) { repository.getPokemonDetail(pokemonId) }
    }

    @Test
    fun `invoke should return failure result when repository fails`() = runTest {
        // Given
        val pokemonId = 1
        val expectedError = Exception("Network Error")
        coEvery { repository.getPokemonDetail(pokemonId) } returns Result.failure(expectedError)

        // When
        val result = useCase(pokemonId)

        // Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(expectedError)
        coVerify(exactly = 1) { repository.getPokemonDetail(pokemonId) }
    }
}