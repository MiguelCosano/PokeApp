package com.cosanodev.pokeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cosanodev.pokeapp.domain.entity.PokemonEntity
import com.cosanodev.pokeapp.domain.usecase.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(getPokemonUseCase: GetPokemonUseCase): ViewModel() {
    val pokemon: Flow<PagingData<PokemonEntity>> =
        getPokemonUseCase.invoke()
            .cachedIn(viewModelScope)
}