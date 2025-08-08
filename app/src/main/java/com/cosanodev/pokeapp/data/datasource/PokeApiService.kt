package com.cosanodev.pokeapp.data.datasource

import com.cosanodev.pokeapp.data.DataConstants.POKE_API_LOADED_ITEMS
import com.cosanodev.pokeapp.data.DataConstants.POKE_API_OFFSET
import com.cosanodev.pokeapp.data.response.PokemonDetailResponse
import com.cosanodev.pokeapp.data.response.PokemonResponse
import com.cosanodev.pokeapp.data.response.PokemonSpeciesDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("offset") offset: Int = POKE_API_OFFSET,
        @Query("limit") limit: Int = POKE_API_LOADED_ITEMS
    ): PokemonResponse

    @GET("pokemon/{pokemonId}")
    suspend fun fetchPokemonDetails(
        @Path("pokemonId") pokemonId: Int
    ): Response<PokemonDetailResponse>

    @GET("pokemon-species/{pokemonId}")
    suspend fun fetchPokemonSpeciesDetails(
        @Path("pokemonId") pokemonId: Int
    ): Response<PokemonSpeciesDetailResponse>
}
