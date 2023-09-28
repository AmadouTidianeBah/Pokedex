package com.atb.pokedex.presentation.pokemon_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atb.pokedex.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var _state = MutableStateFlow(PokemonDetailState())
    val state: StateFlow<PokemonDetailState> = _state
    var pokemonColor = -1

    init {
        savedStateHandle.get<String>("pokemon_name")?.let {name ->
            getPokemon(name)
        }
        savedStateHandle.get<Int>("pokemon_color")?.let {color ->
            pokemonColor = color
        }
    }

    private fun getPokemon(name: String) {
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(isLoading = true, pokemon = null, error = false)
                }
                val data = repository.getPokemonDetail(name.lowercase().trim())
                _state.update {
                    it.copy(pokemon = data, isLoading = false, error = false)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(pokemon = null, isLoading = false, error = true)
                }
            }
        }
    }
}