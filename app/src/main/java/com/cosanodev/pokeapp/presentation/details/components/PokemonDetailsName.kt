package com.cosanodev.pokeapp.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.dimensionResource
import com.cosanodev.pokeapp.R
@Composable
fun PokemonDetailsName(number: Int, name: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .testTag(stringResource(R.string.pokemon_detail_name_test_tag))
            .background(
                MaterialTheme.colorScheme.tertiaryContainer
            )
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        Icon(
            painterResource(R.drawable.pokeball_icon),
            contentDescription = stringResource(R.string.pokeball_icon_content_description),
            modifier = Modifier.size(dimensionResource(R.dimen.padding_large))
        )
        Text(
            text = "$number $name",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}