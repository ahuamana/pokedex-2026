package com.ahuaman.data.repository

import androidx.paging.PagingSource
import com.ahuaman.data.mapper.toDomain
import com.ahuaman.data.models.pokemon.PokemonDataModel
import com.ahuaman.data.models.pokemon.PokemonResponse
import com.ahuaman.data.remote.PokeApiService
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class PokemonPagingSourceTest {

    private val apiService: PokeApiService = mockk()
    private lateinit var pagingSource: PokemonPagingSource

    @Before
    fun setup() {
        pagingSource = PokemonPagingSource(apiService)
    }

    @Test
    fun `Initial load success state - returns Page`() = runTest {
        // 1. Create a list that matches the loadSize (20)
        val pokemonList = List(20) { i ->
            PokemonDataModel(name = "Pokemon $i", url = "...")
        }
        val response = PokemonResponse(results = pokemonList)

        coEvery { apiService.getPokemonList(offset = 0, limit = 20) } returns response

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 20, placeholdersEnabled = false)
        )

        // 2. Now nextKey will be 20 because list.size == loadSize
        val expected = PagingSource.LoadResult.Page(
            data = pokemonList.map { it.toDomain() },
            prevKey = null,
            nextKey = 20
        )
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `Initial load - Empty list - Returns Page with null nextKey`() = runTest {
        // Given
        val response = PokemonResponse(results = emptyList())
        coEvery { apiService.getPokemonList(any(), any()) } returns response

        // When
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(null, 20, false)
        )

        // Then
        val page = result as PagingSource.LoadResult.Page
        assertThat(page.data).isEmpty()
        assertThat(page.nextKey).isNull() // Important: Stop pagination here
    }

    @Test
    fun `Initial load - API Error - Returns LoadResult Error`() = runTest {
        // Given
        val exception = RuntimeException("PokeAPI is down")
        coEvery { apiService.getPokemonList(any(), any()) } throws exception

        // When
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(null, 20, false)
        )

        // Then
        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
        val errorResult = result as PagingSource.LoadResult.Error
        assertThat(errorResult.throwable.message).isEqualTo("PokeAPI is down")
    }

    @Test
    fun `Append load - Success - Returns Page with correct nextKey`() = runTest {
        // 1. Create a full list of 20 items to satisfy the loadSize
        val pokemonList = List(20) { i ->
            PokemonDataModel(name = "Pokemon ${i + 20}", url = "...")
        }
        val response = PokemonResponse(results = pokemonList)

        // Ensure the mock matches exactly what the PagingSource will ask for
        coEvery { apiService.getPokemonList(offset = 20, limit = 20) } returns response

        // When: Simulation of "Load More" starting at offset 20
        val result = pagingSource.load(
            PagingSource.LoadParams.Append(key = 20, loadSize = 20, placeholdersEnabled = false)
        )

        // Then
        val page = result as PagingSource.LoadResult.Page
        assertThat(page.data).hasSize(20)

        // 2. Logic check: If we are on Page 2 (offset 20), prevKey should be 0
        assertThat(page.prevKey).isEqualTo(0)

        // 3. Logic check: Since we got 20 items, nextKey should be 20 + 20
        assertThat(page.nextKey).isEqualTo(40)
    }

    @Test
    fun `Append load - Last Page - Returns Page with null nextKey`() = runTest {
        // Given: We request 20 items, but the API only returns 5 (the end of the data)
        val pokemonList = List(5) { i ->
            PokemonDataModel(name = "Last Pokemon $i", url = "...")
        }
        val response = PokemonResponse(results = pokemonList)

        coEvery { apiService.getPokemonList(offset = 100, limit = 20) } returns response

        // When
        val result = pagingSource.load(
            PagingSource.LoadParams.Append(key = 100, loadSize = 20, placeholdersEnabled = false)
        )

        // Then
        val page = result as PagingSource.LoadResult.Page
        assertThat(page.data).hasSize(5)
        assertThat(page.nextKey).isNull()
    }
}