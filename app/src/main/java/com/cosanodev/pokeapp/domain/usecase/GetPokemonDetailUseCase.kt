package com.cosanodev.pokeapp.domain.usecase

import com.cosanodev.pokeapp.domain.entity.PokemonDetailEntity
import com.cosanodev.pokeapp.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemonId: Int): PokemonDetailEntity {
        return pokemonRepository.getPokemonDetails(pokemonId)
    }
}