package com.ahuaman.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahuaman.domain.model.PokemonPresentationModel

class PokemonPagingSource(
    private val apiService: PokeApiService
): PagingSource<Int, PokemonPresentationModel>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonPresentationModel> {
        val position = params.key ?: 0
        return try {
            val response = apiService.getPokemonList(offset = position, limit = params.loadSize)
            val pokemonList = response.results.map { it.toDomain() }

            LoadResult.Page(
                data = pokemonList,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (pokemonList.isEmpty()) null else position + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonPresentationModel>): Int? {
        TODO("Not yet implemented")
    }

}