package com.cosanodev.pokeapp

import com.cosanodev.pokeapp.data.response.PokemonDetailResponse
import com.cosanodev.pokeapp.data.response.PokemonSpeciesDetailResponse
import com.cosanodev.pokeapp.data.response.mapToPokemonDetailEntity
import org.junit.Test

class PokemonDetailResponseTest {
    @Test
    fun mapToPokemonDetailEntity_returnsCorrectEntity_whenAllFieldsAreValid() {
        val pokemonDetailResponse = PokemonDetailResponse(
            id = 1,
            name = "pikachu",
            height = 4,
            weight = 60,
            types = listOf(
                PokemonDetailResponse.Type(slot = 1, typeInfo = PokemonDetailResponse.Type.TypeInfo(typeName = "electric"))
            ),
            stats = listOf(
                PokemonDetailResponse.Stats(baseStat = 35, statInfo = PokemonDetailResponse.Stats.StatInfo(name = "speed"))
            )
        )
        val pokemonSpeciesDetailResponse = PokemonSpeciesDetailResponse(
            dataEntries = listOf(
                PokemonSpeciesDetailResponse.DescriptionEntries(
                    descriptionText = "A yellow mouse Pokémon.",
                    languageData = PokemonSpeciesDetailResponse.DescriptionEntries.LanguageData(name = "en")
                )
            ),
            shortDescription = listOf(
                PokemonSpeciesDetailResponse.ShortDescription(
                    descriptionText = "Mouse Pokémon",
                    languageData = PokemonSpeciesDetailResponse.ShortDescription.LanguageData(name = "en")
                )
            )
        )

        val result = mapToPokemonDetailEntity(pokemonDetailResponse, pokemonSpeciesDetailResponse)

        assert(result.id == 1)
        assert(result.name == "Pikachu")
        assert(result.types == listOf("electric"))
        assert(result.shortDescription == "Mouse Pokémon")
        assert(result.description == "A yellow mouse Pokémon.")
        assert(result.height == 4)
        assert(result.weight == 60)
        assert(result.stats.size == 1)
        assert(result.stats[0].name == "Speed")
        assert(result.stats[0].baseValue == 35)
    }

    @Test
    fun mapToPokemonDetailEntity_returnsDefaultValues_whenFieldsAreNull() {
        val pokemonDetailResponse = PokemonDetailResponse(
            id = null,
            name = null,
            height = null,
            weight = null,
            types = emptyList(),
            stats = emptyList()
        )
        val pokemonSpeciesDetailResponse = PokemonSpeciesDetailResponse(
            dataEntries = emptyList(),
            shortDescription = emptyList()
        )

        val result = mapToPokemonDetailEntity(pokemonDetailResponse, pokemonSpeciesDetailResponse)

        assert(result.id == 0)
        assert(result.name == "-")
        assert(result.types.isEmpty())
        assert(result.shortDescription == "-")
        assert(result.description == "-")
        assert(result.height == 0)
        assert(result.weight == 0)
        assert(result.stats.isEmpty())
    }

    @Test
    fun mapToPokemonDetailEntity_ignoresInvalidTypesAndStats() {
        val pokemonDetailResponse = PokemonDetailResponse(
            id = 1,
            name = "bulbasaur",
            height = 7,
            weight = 69,
            types = listOf(
                null,
                PokemonDetailResponse.Type(slot = 1, typeInfo = null),
                PokemonDetailResponse.Type(slot = 2, typeInfo = PokemonDetailResponse.Type.TypeInfo(typeName = "grass"))
            ),
            stats = listOf(
                null,
                PokemonDetailResponse.Stats(baseStat = null, statInfo = null),
                PokemonDetailResponse.Stats(baseStat = 49, statInfo = PokemonDetailResponse.Stats.StatInfo(name = "attack"))
            )
        )
        val pokemonSpeciesDetailResponse = PokemonSpeciesDetailResponse(
            dataEntries = emptyList(),
            shortDescription = emptyList()
        )

        val result = mapToPokemonDetailEntity(pokemonDetailResponse, pokemonSpeciesDetailResponse)

        assert(result.types == listOf("grass"))
        assert(result.stats.size == 1)
        assert(result.stats[0].name == "Attack")
        assert(result.stats[0].baseValue == 49)
    }
}