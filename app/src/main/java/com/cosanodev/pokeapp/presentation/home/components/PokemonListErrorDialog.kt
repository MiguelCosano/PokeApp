package com.cosanodev.pokeapp.presentation.home.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.cosanodev.pokeapp.R

@Composable
fun PokemonListErrorDialog(retry: () -> Unit) {
    AlertDialog(
        title = { Text(stringResource(R.string.error_dialog_title)) },
        text = { Text(stringResource(R.string.pokemon_list_load_error)) },
        confirmButton = {
            Button(onClick = { retry() }) {
                Text(stringResource(R.string.error_retry_button))
            }
        },
        onDismissRequest = { retry() },
        modifier = Modifier.testTag(stringResource(R.string.home_error_dialog_test_tag))
    )
}