package com.cosanodev.pokeapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.cosanodev.pokeapp.R
import com.cosanodev.pokeapp.core.zero
import com.cosanodev.pokeapp.presentation.home.components.BottomLoadMoreErrorMessage
import com.cosanodev.pokeapp.presentation.home.components.BottomLoader
import com.cosanodev.pokeapp.presentation.home.components.HomeLoader
import com.cosanodev.pokeapp.presentation.home.components.PokemonList
import com.cosanodev.pokeapp.presentation.home.components.PokemonListErrorDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navigateToPokemonDetail: (Int, String) -> Unit,
    homeViewmodel: HomeViewmodel = hiltViewModel()
) {
    val pokemon = homeViewmodel.pokemon.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                pokemon.loadState.refresh is LoadState.Loading && pokemon.itemCount == Int.zero -> {
                    HomeLoader()
                }

                pokemon.loadState.refresh is LoadState.Error && pokemon.itemCount == Int.zero -> {
                    PokemonListErrorDialog {
                        pokemon.retry()
                    }
                }

                else -> {
                    PokemonList(pokemon, navigateToPokemonDetail)

                    when {
                        pokemon.loadState.append is LoadState.Loading -> {
                            BottomLoader()
                        }

                        pokemon.loadState.append is LoadState.Error -> {
                            BottomLoadMoreErrorMessage {
                                pokemon.retry()
                            }
                        }
                    }
                }
            }
        }
    }
}