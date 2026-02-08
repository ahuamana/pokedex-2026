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
) : PagingSource<Int, PokemonDomainModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDomainModel> {
        val position = params.key ?: 0

        return try {
            val response = apiService.getPokemonList(
                offset = position,
                limit = params.loadSize
            )

            // Senior Fix: Handle nullable results list using .orEmpty()
            val results = response.results.orEmpty()
            val pokemonList = results.map { it.toDomain() }

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
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    // Senior Implementation: Essential for state restoration (e.g., orientation changes)
    override fun getRefreshKey(state: PagingState<Int, PokemonDomainModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
        }
    }
}