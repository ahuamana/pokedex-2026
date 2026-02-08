package com.ahuaman.domain.usecase

import androidx.paging.PagingData
import app.cash.turbine.test
import com.ahuaman.domain.repository.PokemonRepository
import com.ahuaman.testing.pokemons.PokemonMocks
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetPokemonListUseCaseTest {

    private val repository: PokemonRepository = mockk()
    private lateinit var useCase: GetPokemonListUseCase

    @Before
    fun setup() {
        useCase = GetPokemonListUseCase(repository)
    }

    @Test
    fun `invoke should call repository and return paging data`() = runTest {
        // Given
        val mockPagingData = PagingData.from(listOf(PokemonMocks.singlePokemon))
        val mockFlow = flowOf(mockPagingData)
        coEvery { repository.getPokemonPagingData() } returns mockFlow

        // When & Then: We use Turbine to collect the flow
        useCase().test {
            val emittedData = awaitItem()
            // Here we are actually "touching" the data, which triggers the coverage
            assertThat(emittedData).isEqualTo(mockPagingData)
            awaitComplete()
        }

        coVerify(exactly = 1) { repository.getPokemonPagingData() }
    }
}