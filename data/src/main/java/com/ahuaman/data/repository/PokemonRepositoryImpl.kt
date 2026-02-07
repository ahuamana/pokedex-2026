package com.ahuaman.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahuaman.data.remote.PokeApiService
import com.ahuaman.domain.model.PokemonPresentationModel
import com.ahuaman.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService
) : PokemonRepository {
    override fun getPokemonPagingData(): Flow<PagingData<PokemonPresentationModel>> {
        return Pager(
            config = PagingConfig (pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { PokemonPagingSource(apiService) }
        ).flow
    }
}