package com.atb.pokedex.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.atb.pokedex.data.PokemonDataSource
import com.atb.pokedex.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {
    private var limit = 20
    private var _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state

    val data = Pager(config = PagingConfig(pageSize = limit)) {
        PokemonDataSource(repository)
    }.flow.cachedIn(viewModelScope)

    fun getPokemon(name: String) {
        viewModelScope.launch {
            delay(500)
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

    fun updatePokemon() {
        _state.value = PokemonListState()
    }
}