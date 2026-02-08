package com.ahuaman.pokedex.presentation.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.ahuaman.pokedex.presentation.screens.models.PokemonPresentationModel
import com.ahuaman.domain.usecase.GetPokemonListUseCase
import com.ahuaman.pokedex.presentation.screens.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeEffect>()
    val effect = _effect.asSharedFlow()

    private var _pokemonPagingData = MutableStateFlow<PagingData<PokemonPresentationModel>>(PagingData.empty())
    val pokemonPagingData = _pokemonPagingData.asStateFlow()


    init {
        handleIntent(HomeIntent.LoadPokemon)
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadPokemon -> fetchPokemon()
            is HomeIntent.RetryLoading -> fetchPokemon()
            is HomeIntent.OnPokemonClick -> {
                viewModelScope.launch {
                    _effect.emit(HomeEffect.NavigateToDetail(intent.id))
                }
            }
        }
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            getPokemonListUseCase()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    val mappedData = pagingData.map { it.toPresentation() }
                    _pokemonPagingData.value = mappedData
                }
        }
    }
}