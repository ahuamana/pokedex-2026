package com.ahuaman.domain.repository

import androidx.paging.PagingData
import com.ahuaman.domain.model.PokemonDetailDomainModel
import com.ahuaman.domain.model.PokemonDomainModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonPagingData() : Flow<PagingData<PokemonDomainModel>>

    suspend fun getPokemonDetail(id: Int) : Result<PokemonDetailDomainModel>
}