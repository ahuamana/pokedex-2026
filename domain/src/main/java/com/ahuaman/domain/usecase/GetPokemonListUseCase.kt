package com.ahuaman.domain.usecase

import androidx.paging.PagingData
import com.ahuaman.domain.model.PokemonPresentationModel
import com.ahuaman.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
){
    operator fun invoke(): Flow<PagingData<PokemonPresentationModel>> = repository.getPokemonPagingData()
}