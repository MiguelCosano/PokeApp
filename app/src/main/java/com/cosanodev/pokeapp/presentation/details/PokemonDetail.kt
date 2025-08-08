package com.cosanodev.pokeapp.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cosanodev.pokeapp.R
import com.cosanodev.pokeapp.presentation.core.Resource
import com.cosanodev.pokeapp.presentation.details.components.PokemonDetailsData
import com.cosanodev.pokeapp.presentation.details.components.PokemonDetailsError
import com.cosanodev.pokeapp.presentation.details.components.PokemonDetailsTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetail(
    navigateBack: () -> Unit,
    pokemonId: Int,
    pokemonUrl: String,
    pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(pokemonId) {
        pokemonDetailsViewModel.getPokemonDetails(pokemonId)
    }

    val state by pokemonDetailsViewModel.pokemonDetails.collectAsState()

    Scaffold(
        topBar = { PokemonDetailsTopBar(navigateBack) },
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (state) {
                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is Resource.Success -> {
                    val detail = (state as Resource.Success).data
                    PokemonDetailsData(detail, pokemonUrl)
                }

                is Resource.Error -> {
                    PokemonDetailsError(
                        retry = {
                            pokemonDetailsViewModel.getPokemonDetails(pokemonId)
                        },
                        goBack = { navigateBack() }
                    )
                }
            }
        }
    }
}