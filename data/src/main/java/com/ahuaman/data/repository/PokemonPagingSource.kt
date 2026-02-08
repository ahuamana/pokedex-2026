package com.ahuaman.data.repository

import android.net.http.HttpException
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahuaman.data.mapper.toDomain
import com.ahuaman.data.remote.PokeApiService
import com.ahuaman.domain.model.PokemonDomainModel
import timber.log.Timber
import java.io.IOException

class PokemonPagingSource(
    private val apiService: PokeApiService
): PagingSource<Int, PokemonDomainModel>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDomainModel> {
        val position = params.key ?: 0
        Timber.tag("PagingSource").d("Cargando página: $position con loadSize: ${params.loadSize}")
        return try {
            val response = apiService.getPokemonList(offset = position, limit = params.loadSize)
            Timber.tag("PagingSource").d("Respuesta recibida: ${response.results.size} items")

            val pokemonList = response.results.map { it.toDomain() }


            LoadResult.Page(
                data = pokemonList,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (pokemonList.isEmpty() || pokemonList.size < params.loadSize) {
                    null
                } else {
                    position + params.loadSize
                }
            )
        } catch (e: IOException) {
            // Specific handling for network issues
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // Specific handling for 4xx or 5xx server errors
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonDomainModel>): Int? {
        TODO("Not yet implemented")
    }

}