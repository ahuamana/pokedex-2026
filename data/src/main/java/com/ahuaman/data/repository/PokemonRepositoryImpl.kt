package com.ahuaman.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahuaman.data.mapper.toDomain
import com.ahuaman.data.remote.PokeApiService
import com.ahuaman.domain.model.PokemonDetailDomainModel
import com.ahuaman.domain.model.PokemonDomainModel
import com.ahuaman.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService
) : PokemonRepository {
    override fun getPokemonPagingData(): Flow<PagingData<PokemonDomainModel>> {
        return Pager(
            config = PagingConfig (pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { PokemonPagingSource(apiService) }
        ).flow
    }

    override suspend fun getPokemonDetail(id: Int): Result<PokemonDetailDomainModel> {
        return try {
            val response = apiService.getPokemonDetail(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}