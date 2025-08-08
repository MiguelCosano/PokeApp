package com.cosanodev.pokeapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cosanodev.pokeapp.core.zero
import com.cosanodev.pokeapp.data.DataConstants.POKE_API_LOADED_ITEMS
import com.cosanodev.pokeapp.data.datasource.PokeApiService
import com.cosanodev.pokeapp.data.response.toEntity
import com.cosanodev.pokeapp.domain.entity.PokemonEntity
import okio.IOException
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(private val pokeApi: PokeApiService): PagingSource<Int, PokemonEntity>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        return try {
            val offset = params.key ?: Int.zero
            val response = pokeApi.fetchPokemonList(offset)
            val pokemon = response.results

            val prevKey = if (offset > Int.zero) offset - POKE_API_LOADED_ITEMS else null
            val nextKey = if (response.next != null) offset + POKE_API_LOADED_ITEMS else null

            LoadResult.Page(
                data = pokemon.mapNotNull { it.toEntity() },
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }

}