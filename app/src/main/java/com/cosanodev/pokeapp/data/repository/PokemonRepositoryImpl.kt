package com.cosanodev.pokeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cosanodev.pokeapp.data.DataConstants.PAGINATION_ITEMS_TO_PREFETCH
import com.cosanodev.pokeapp.data.DataConstants.POKE_API_LOADED_ITEMS
import com.cosanodev.pokeapp.data.PokemonPagingSource
import com.cosanodev.pokeapp.data.datasource.PokeApiService
import com.cosanodev.pokeapp.data.response.PokemonDetailResponse
import com.cosanodev.pokeapp.data.response.PokemonSpeciesDetailResponse
import com.cosanodev.pokeapp.data.response.mapToPokemonDetailEntity
import com.cosanodev.pokeapp.domain.entity.PokemonDetailEntity
import com.cosanodev.pokeapp.domain.entity.PokemonEntity
import com.cosanodev.pokeapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(val pokeApi: PokeApiService): PokemonRepository {
    override fun getPokemon(): Flow<PagingData<PokemonEntity>> {
        return Pager(config = PagingConfig(pageSize = POKE_API_LOADED_ITEMS, prefetchDistance = PAGINATION_ITEMS_TO_PREFETCH),
            pagingSourceFactory = {
                PokemonPagingSource(pokeApi)
            }).flow
    }

    override suspend fun getPokemonDetails(pokemonId: Int): PokemonDetailEntity {
        val detail = fetchPokemonDetail(pokemonId)
        val species = fetchPokemonSpeciesDetail(pokemonId)

        return mapToPokemonDetailEntity(detail, species)
    }

    private suspend fun fetchPokemonDetail(pokemonId: Int): PokemonDetailResponse {
        val response = pokeApi.fetchPokemonDetails(pokemonId)
        return response.body()?.let { body ->
            if (response.isSuccessful) body else throw HttpException(response)
        } ?: throw HttpException(response)
    }

    private suspend fun fetchPokemonSpeciesDetail(pokemonId: Int): PokemonSpeciesDetailResponse {
        val response = pokeApi.fetchPokemonSpeciesDetails(pokemonId)
        return response.body()?.let { body ->
            if (response.isSuccessful) body else throw HttpException(response)
        } ?: throw HttpException(response)
    }
}