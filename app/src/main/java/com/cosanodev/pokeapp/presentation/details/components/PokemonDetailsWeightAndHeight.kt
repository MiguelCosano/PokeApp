package com.cosanodev.pokeapp.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cosanodev.pokeapp.R

@Composable
fun PokemonDetailsWeightAndHeight(weight: Int, height: Int) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(dimensionResource(R.dimen.padding_small))
            .testTag(stringResource(R.string.pokemon_detail_weight_and_height_tag))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.pokemon_detail_height)
            )
            Text(
                text = height.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        HorizontalDivider(thickness = dimensionResource(R.dimen.divider_basic_thickness), color = Color.Black)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.pokemon_detail_weight)
            )
            Text(
                text = weight.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}