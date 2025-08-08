package com.cosanodev.pokeapp.presentation.core

enum class PokemonStatsMax(val max: Int) {
    Hp(255),
    Attack(190),
    Defense(230),
    SpecialAttack(194),
    SpecialDefense(230),
    Speed(180),

    Unknown(0);

    companion object {
        fun fromApiName(apiName: String): PokemonStatsMax {
            return when (apiName) {
                "Hp" -> Hp
                "Attack" -> Attack
                "Defense" -> Defense
                "Special-attack" -> SpecialAttack
                "Special-defense" -> SpecialDefense
                "Speed" -> Speed
                else -> Unknown
            }
        }
    }
}

