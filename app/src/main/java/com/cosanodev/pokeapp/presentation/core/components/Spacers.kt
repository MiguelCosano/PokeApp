package com.cosanodev.pokeapp.presentation.core.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.cosanodev.pokeapp.R

@Composable
fun SmallSpacer() {
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
}

@Composable
fun MediumSpacer() {
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
}

@Composable
fun BigSpacer() {
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_x_large)))
}
