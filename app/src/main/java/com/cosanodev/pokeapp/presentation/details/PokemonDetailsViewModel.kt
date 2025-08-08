package com.cosanodev.pokeapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosanodev.pokeapp.presentation.core.Resource
import com.cosanodev.pokeapp.domain.entity.PokemonDetailEntity
import com.cosanodev.pokeapp.domain.usecase.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailUseCase
): ViewModel(){

    private val _pokemonDetails = MutableStateFlow<Resource<PokemonDetailEntity>>(Resource.Loading)
    val pokemonDetails: StateFlow<Resource<PokemonDetailEntity>> = _pokemonDetails

    fun getPokemonDetails(pokemonId: Int) {
        viewModelScope.launch {
            try {
                val response = getPokemonDetailsUseCase.invoke(pokemonId)
                _pokemonDetails.value = Resource.Success(response)
            }catch (exception: Exception) {
                _pokemonDetails.value = Resource.Error(exception.message)
            }
        }
    }

}