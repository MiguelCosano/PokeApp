package com.cosanodev.pokeapp.presentation.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.cosanodev.pokeapp.domain.entity.PokemonEntity

@Composable
fun PokemonList(
    pokemon: LazyPagingItems<PokemonEntity>,
    navigateToPokemonDetail: (Int, String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(pokemon.itemCount) { index ->
            pokemon[index]?.let { pokemon ->
                PokemonItem(pokemon.id, pokemon.name, pokemon.image) {
                    navigateToPokemonDetail(pokemon.id, pokemon.image)
                }
            }
        }
    }
}