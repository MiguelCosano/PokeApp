package com.cosanodev.pokeapp.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cosanodev.pokeapp.R
import com.cosanodev.pokeapp.domain.entity.PokemonDetailEntity
import com.cosanodev.pokeapp.presentation.core.PokemonStatsMax

@Composable
fun PokemonDetailsStats(stats: List<PokemonDetailEntity.Stat>) {
    Text(stringResource(R.string.pokemon_detail_initial_stats), style  = MaterialTheme.typography.headlineSmall)
    stats.forEach { stat ->
        PokemonStat(stat.name, stat.baseValue)
    }
}

@Composable
fun PokemonStat(statName: String, statBaseValue: Int) {
    val maxValue = PokemonStatsMax.fromApiName(statName).max
    Column(modifier = Modifier
        .testTag("${stringResource(R.string.pokemon_detail_stats_test_tag)}_$statName")
        .fillMaxWidth()
        .padding(vertical = dimensionResource(R.dimen.padding_small))) {
        Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
            Text(
                text = "$statName: $statBaseValue",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
            )
            Text(
                text = maxValue.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        LinearProgressIndicator(
            progress = { statBaseValue.coerceIn(0, maxValue) / maxValue.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.padding_small))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_x_small))),
        )
    }
}

