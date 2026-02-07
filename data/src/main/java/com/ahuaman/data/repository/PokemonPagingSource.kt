package com.ahuaman.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahuaman.data.mapper.toDomain
import com.ahuaman.data.remote.PokeApiService
import com.ahuaman.domain.model.PokemonPresentationModel

class PokemonPagingSource(
    private val apiService: PokeApiService
): PagingSource<Int, PokemonPresentationModel>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonPresentationModel> {
        val position = params.key ?: 0
        Log.d("PagingSource", "Cargando página: $position con loadSize: ${params.loadSize}")
        return try {
            val response = apiService.getPokemonList(offset = position, limit = params.loadSize)
            Log.d("PagingSource", "Respuesta recibida: ${response.results.size} items")

            val pokemonList = response.results.map { it.toDomain() }


            LoadResult.Page(
                data = pokemonList,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (pokemonList.isEmpty()) null else position + params.loadSize
            )
        } catch (e: Exception) {
            Log.e("PagingSource", "Error cargando datos", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonPresentationModel>): Int? {
        TODO("Not yet implemented")
    }

}