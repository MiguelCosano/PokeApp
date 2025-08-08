package com.cosanodev.pokeapp.presentation.details.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cosanodev.pokeapp.R

@Composable
fun PokemonDetailsError(retry: () -> Unit, goBack: () -> Unit) {
    AlertDialog(
        title = { Text(stringResource(R.string.error_dialog_title)) },
        text = { Text(stringResource(R.string.pokemon_detail_load_error)) },
        confirmButton = {
            Button(onClick = { retry() }) {
                Text(stringResource(R.string.error_retry_button))
            }
        },
        dismissButton = {
            Button(onClick = { goBack() }) {
                Text(stringResource(R.string.pokemon_detail_load_error_go_back))
            }
        },
        onDismissRequest = { retry() },
    )
}