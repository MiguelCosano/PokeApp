package com.cosanodev.pokeapp.domain.entity

data class PokemonDetailEntity (
    val id: Int,
    val name: String,
    val types: List<String>,
    val stats: List<Stat>,
    val shortDescription: String,
    val description: String,
    val height: Int,
    val weight: Int
){
    data class Stat(
        val name: String,
        val baseValue: Int
    )
}