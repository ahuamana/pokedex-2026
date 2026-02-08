package com.ahuaman.data.repository

import com.ahuaman.data.models.pokemon.PokemonDetailResponse
import com.ahuaman.data.remote.PokeApiService
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class PokemonRepositoryImplTest {

    private val apiService: PokeApiService = mockk()
    private lateinit var repository: PokemonRepositoryImpl

    @Before
    fun setup() {
        // We inject the mocked API service into the implementation
        repository = PokemonRepositoryImpl(apiService)
    }

    @Test
    fun `getPokemonPagingData should return a flow of paging data`() = runTest {
        // When: Calling the repository
        val result = repository.getPokemonPagingData()

        // Then: Assert that we receive a Flow (PagingData is hard to verify content
        // directly, but we verify the stream exists)
        assertThat(result).isNotNull()
    }

    @Test
    fun `getPokemonDetail should return success when api succeeds`() = runTest {
        // Given
        val pokemonId = 1
        val mockResponse = PokemonDetailResponse(
            id = 1,
            name = "bulbasaur",
            weight = 69,
            height = 7,
            types = emptyList(),
            stats = emptyList()
        )
        coEvery { apiService.getPokemonDetail(pokemonId) } returns mockResponse

        // When
        val result = repository.getPokemonDetail(pokemonId)

        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()?.name).isEqualTo("bulbasaur")
        // Verify math from mapper is applied (69 / 10.0)
        assertThat(result.getOrNull()?.weightKg).isEqualTo(6.9)
    }

    @Test
    fun `getPokemonDetail should return failure when api throws exception`() = runTest {
        // Given
        val pokemonId = 1
        coEvery { apiService.getPokemonDetail(pokemonId) } throws Exception("Network Error")

        // When
        val result = repository.getPokemonDetail(pokemonId)

        // Then
        assertThat(result.isFailure).isTrue()
    }

}