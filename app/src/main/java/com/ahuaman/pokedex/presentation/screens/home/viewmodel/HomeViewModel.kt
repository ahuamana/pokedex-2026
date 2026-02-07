package com.ahuaman.pokedex.presentation.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahuaman.domain.model.PokemonPresentationModel
import com.ahuaman.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private var _pokemonPagingData = MutableStateFlow<PagingData<PokemonPresentationModel>>(PagingData.empty())
    val pokemonPagingData = _pokemonPagingData.asStateFlow()


    init {
        handleIntent(HomeIntent.LoadPokemon)
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadPokemon -> fetchPokemon()
            is HomeIntent.RetryLoading -> fetchPokemon()
            is HomeIntent.OnPokemonClick -> { /* Navegación */ }
        }
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            getPokemonListUseCase()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _pokemonPagingData.value = pagingData
                }
        }
    }
}