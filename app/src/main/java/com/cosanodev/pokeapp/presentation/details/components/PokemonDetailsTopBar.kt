package com.cosanodev.pokeapp.presentation.details.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.cosanodev.pokeapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsTopBar(navigateBack: () -> Unit) {
    val isGoingBack = remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        navigationIcon = {
            IconButton(
                onClick = {
                    if (isGoingBack.value.not()) {
                        navigateBack()
                        isGoingBack.value = true
                    }
                }
            ) {
                Icon(
                    painterResource(R.drawable.back_icon),
                    contentDescription = stringResource(R.string.back_icon_content_description),
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        }
    )
}