package com.cosanodev.pokeapp.domain.usecase

import androidx.paging.PagingData
import com.cosanodev.pokeapp.domain.entity.PokemonEntity
import com.cosanodev.pokeapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(): Flow<PagingData<PokemonEntity>> {
        return pokemonRepository.getPokemon()
    }
}