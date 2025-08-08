package com.cosanodev.pokeapp.domain.repository

import androidx.paging.PagingData
import com.cosanodev.pokeapp.domain.entity.PokemonDetailEntity
import com.cosanodev.pokeapp.domain.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemon(): Flow<PagingData<PokemonEntity>>

    suspend fun getPokemonDetails(pokemonId: Int): PokemonDetailEntity
}