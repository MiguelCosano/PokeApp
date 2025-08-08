package com.cosanodev.pokeapp.presentation.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class PokemonDetail(val pokemonId: Int, val pokemonImg: String)