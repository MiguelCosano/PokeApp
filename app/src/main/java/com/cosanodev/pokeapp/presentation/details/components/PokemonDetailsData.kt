package com.cosanodev.pokeapp.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cosanodev.pokeapp.R
import com.cosanodev.pokeapp.domain.entity.PokemonDetailEntity
import com.cosanodev.pokeapp.presentation.core.components.BigSpacer
import com.cosanodev.pokeapp.presentation.core.components.SmallSpacer

@Composable
fun PokemonDetailsData(detail: PokemonDetailEntity, pokemonUrl: String) {

    val scrollState = rememberScrollState()
    detail.let {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PokemonDetailsImage(pokemonUrl, it.name)
                Column(
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                ) {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15))
                            .background(MaterialTheme.colorScheme.primaryContainer),
                    ) {
                        PokemonDetailsName(detail.id, it.name)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(dimensionResource(R.dimen.padding_small))
                        ) {
                            Text(
                                text = it.shortDescription,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.testTag(stringResource(R.string.pokemon_detail_short_description_test_tag))
                            )
                        }
                    }
                    SmallSpacer()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        it.types.forEach { pokemonType ->
                            PokemonDetailsType(pokemonType)
                        }
                    }
                    SmallSpacer()
                    PokemonDetailsWeightAndHeight(it.weight, it.height)
                }
            }
            SmallSpacer()
            PokemonDetailsDescription(it.description)
            BigSpacer()
            PokemonDetailsStats(it.stats)
        }
    }
}