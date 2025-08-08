package com.cosanodev.pokeapp.presentation.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cosanodev.pokeapp.presentation.details.PokemonDetail
import com.cosanodev.pokeapp.presentation.home.Home

@Composable
fun NavigationController() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Home) {
        composable<Home>{
            Home(navigateToPokemonDetail = { id, url ->
                navController.navigate(PokemonDetail(id,url))
            })
        }

        composable<PokemonDetail>{ navBackStackEntry ->
            val pokemonDetail: PokemonDetail = navBackStackEntry.toRoute()
            PokemonDetail(navigateBack = {navController.popBackStack()}, pokemonDetail.pokemonId,pokemonDetail.pokemonImg)
        }
    }
}