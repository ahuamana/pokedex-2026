package com.ahuaman.domain.usecase

import com.ahuaman.domain.model.PokemonDetailDomainModel
import com.ahuaman.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Result<PokemonDetailDomainModel> = pokemonRepository.getPokemonDetail(id)
}