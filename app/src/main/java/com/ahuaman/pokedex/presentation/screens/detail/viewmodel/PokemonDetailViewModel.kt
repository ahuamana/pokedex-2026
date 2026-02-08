package com.ahuaman.pokedex.presentation.screens.detail.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahuaman.domain.usecase.GetPokemonDetailUseCase
import com.ahuaman.pokedex.presentation.screens.detail.models.DetailEffect
import com.ahuaman.pokedex.presentation.screens.detail.models.DetailIntent
import com.ahuaman.pokedex.presentation.screens.detail.models.DetailState
import com.ahuaman.pokedex.presentation.screens.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(DetailState(isLoading = true))
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<DetailEffect>()
    val effect: SharedFlow<DetailEffect> = _effect

    private val pokemonId: Int = try {
        checkNotNull(savedStateHandle.get<Int>("id"))
    } catch (e: Exception) {
        val availableKeys = savedStateHandle.keys().joinToString(", ")
        throw IllegalStateException("Key 'id' not found. Available keys: $availableKeys")
    }

    init {
        savedStateHandle.keys().forEach { key ->
            Log.d("ViewModelDebug", "Key found in SavedState: $key")
        }

        handleIntent(DetailIntent.LoadDetail(pokemonId))
    }

    fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.LoadDetail -> fetchDetail(intent.id)
            is DetailIntent.ToggleFavorite -> {
                _state.update { it.copy(isFavorite = !it.isFavorite) }
            }
            is DetailIntent.OnBackClick -> {
                viewModelScope.launch {
                    _effect.emit(DetailEffect.OnClickArrowBack)
                }
            }
            else -> {}
        }
    }

    private fun fetchDetail(id: Int) {
        viewModelScope.launch {
            getPokemonDetailUseCase(id).onSuccess { detail ->
                _state.update { it.copy(pokemon = detail.toPresentation(), isLoading = false) }
            }.onFailure { error ->
                _state.update { it.copy(error = error.message, isLoading = false) }
            }
        }
    }
}