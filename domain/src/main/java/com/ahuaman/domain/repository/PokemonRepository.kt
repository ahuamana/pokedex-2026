package com.ahuaman.domain.repository

import androidx.paging.PagingData
import com.ahuaman.domain.model.PokemonPresentationModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonPagingData() : Flow<PagingData<PokemonPresentationModel>>
}