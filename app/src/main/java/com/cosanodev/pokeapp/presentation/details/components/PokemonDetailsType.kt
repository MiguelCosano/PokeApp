package com.cosanodev.pokeapp.presentation.details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cosanodev.pokeapp.R
import com.cosanodev.pokeapp.core.capitalizeFirstChar
import com.cosanodev.pokeapp.presentation.core.PokemonType

@Composable
fun PokemonDetailsType(pokemonType: String) {
    ElevatedCard(
        modifier = Modifier.testTag(stringResource(R.string.pokemon_detail_type_test_tag)),
        colors = CardDefaults.elevatedCardColors(
            containerColor = PokemonType.valueOf(pokemonType.capitalizeFirstChar()).color
        )
    ) {
        Text(
            pokemonType,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
        )
    }
}