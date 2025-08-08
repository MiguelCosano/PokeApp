package com.cosanodev.pokeapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.cosanodev.pokeapp.domain.entity.PokemonDetailEntity
import com.cosanodev.pokeapp.presentation.details.components.PokemonDetailsData
import org.junit.Rule
import org.junit.Test

class PokemonDetailsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pokemonDetailsComposable_rendersCorrectlyOnSmallScreen() {
        testPokemonDetailsOnSize(DpSize(320.dp, 480.dp))
    }

    @Test
    fun pokemonDetailsComposable_rendersCorrectlyOnMediumScreen() {
        testPokemonDetailsOnSize(DpSize(480.dp, 800.dp))
    }

    @Test
    fun pokemonDetailsComposable_rendersCorrectlyOnLargeScreen() {
        testPokemonDetailsOnSize(DpSize(720.dp, 1280.dp))
    }

    private fun testPokemonDetailsOnSize(size: DpSize) {
        val testPokemonDetailEntity = PokemonDetailEntity(
            id = 25,
            name = "Pikachu",
            types = listOf("electric"),
            stats = listOf(
                PokemonDetailEntity.Stat("HP", 35),
                PokemonDetailEntity.Stat("Attack", 55),
                PokemonDetailEntity.Stat("Defense", 40),
                PokemonDetailEntity.Stat("Special-Attack", 50),
                PokemonDetailEntity.Stat("Special-Defense", 50),
                PokemonDetailEntity.Stat("Speed", 90)
            ),
            shortDescription = "Mouse Pokémon",
            description = "When several of these Pokémon gather, their electricity can cause lightning storms.",
            height = 4,
            weight = 60
        )

        val imgURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"

        composeTestRule.setContent {
            Box(modifier = Modifier.size(size.width, size.height)) {
                PokemonDetailsData(
                    detail = testPokemonDetailEntity,
                    pokemonUrl = imgURL
                )
            }
        }

        composeTestRule.onNodeWithTag("pokemon_detail_description").assertIsDisplayed()
        composeTestRule.onNodeWithTag("pokemon_detail_image").assertIsDisplayed()
        composeTestRule.onNodeWithTag("pokemon_detail_type").assertIsDisplayed()
        composeTestRule.onNodeWithTag("pokemon_detail_name").assertIsDisplayed()
        composeTestRule.onNodeWithTag("pokemon_detail_weight_height").assertIsDisplayed()
        composeTestRule.onNodeWithTag("pokemon_detail_short_description").assertIsDisplayed()
        composeTestRule.onNodeWithTag("pokemon_detail_stats_HP").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithTag("pokemon_detail_stats_Attack").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithTag("pokemon_detail_stats_Defense").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithTag("pokemon_detail_stats_Special-Attack").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithTag("pokemon_detail_stats_Special-Defense").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithTag("pokemon_detail_stats_Speed").performScrollTo().assertIsDisplayed()
    }

}