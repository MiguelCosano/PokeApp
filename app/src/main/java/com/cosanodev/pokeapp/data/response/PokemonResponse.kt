package com.cosanodev.pokeapp.data.response

import androidx.core.net.toUri
import com.cosanodev.pokeapp.core.capitalizeFirstChar
import com.cosanodev.pokeapp.data.DataConstants.POKE_API_IMAGE_BASE_URL
import com.cosanodev.pokeapp.data.DataConstants.POKE_API_IMAGE_FILE_EXTENSION
import com.cosanodev.pokeapp.domain.entity.PokemonEntity
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse (
    @SerializedName(value = "count") val count: Int?,
    @SerializedName(value = "next") val next: String?,
    @SerializedName(value = "previous") val previous: String?,
    @SerializedName(value = "results") val results: List<PokemonDirection>,
)

@Serializable
data class PokemonDirection (
    @SerializedName(value = "name") val name: String?,
    @SerializedName(value = "url") val url: String?
)

fun PokemonDirection.toEntity(): PokemonEntity? {
    val pokemonId = url?.toUri()?.pathSegments?.last()?.toInt()

    if (pokemonId == null || name == null)
        return null

    val image = pokemonId.let { pokemonId ->
        "$POKE_API_IMAGE_BASE_URL${pokemonId}$POKE_API_IMAGE_FILE_EXTENSION"
    }

    return PokemonEntity(pokemonId, name.capitalizeFirstChar(), image)
}