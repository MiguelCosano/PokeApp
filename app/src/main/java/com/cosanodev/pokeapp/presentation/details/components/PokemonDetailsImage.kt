package com.cosanodev.pokeapp.presentation.details.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.cosanodev.pokeapp.R

@Composable
fun PokemonDetailsImage(url: String, name: String) {
    AsyncImage(
        model = url,
        modifier = Modifier
            .testTag(stringResource(R.string.pokemon_detail_image_test_tag))
            .fillMaxWidth(0.5f)
            .aspectRatio(1f),
        placeholder = painterResource(R.drawable.no_pokemon),
        contentDescription = "${stringResource(R.string.pokemon_image_content_description)} $name",
        contentScale = ContentScale.Fit,
    )
}