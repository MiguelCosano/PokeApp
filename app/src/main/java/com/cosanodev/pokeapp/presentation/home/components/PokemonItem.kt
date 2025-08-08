package com.cosanodev.pokeapp.presentation.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.cosanodev.pokeapp.R
import com.cosanodev.pokeapp.presentation.core.components.SmallSpacer

@Composable
fun PokemonItem(id: Int, name: String, img: String, onClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(if (isLandscape) 0.4f else 1f)
            .padding(dimensionResource(R.dimen.padding_large)),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.padding_small)),
        onClick = { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                val showImageLoading = rememberSaveable { mutableStateOf(false) }

                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = img,
                        contentDescription = "${stringResource(R.string.pokemon_image_content_description)} $name",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.fillMaxSize(),
                        onLoading = {
                            showImageLoading.value = true
                        },
                        onSuccess = {
                            showImageLoading.value = false
                        },
                        onError = {
                            showImageLoading.value = false
                        }
                    )

                    if (showImageLoading.value) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(dimensionResource(R.dimen.padding_x_large))
                        )
                    }
                }
            }
            SmallSpacer()
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(R.drawable.pokeball_icon),
                    contentDescription = stringResource(R.string.pokeball_icon_content_description)
                )
                Text(
                    text = "$id $name",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }
            SmallSpacer()
        }
    }
}