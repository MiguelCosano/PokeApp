package com.cosanodev.pokeapp.data.response

import com.cosanodev.pokeapp.core.capitalizeFirstChar
import com.cosanodev.pokeapp.core.cleanFlavorText
import com.cosanodev.pokeapp.core.noData
import com.cosanodev.pokeapp.core.space
import com.cosanodev.pokeapp.core.zero
import com.cosanodev.pokeapp.data.DataConstants.LANGUAGE_ENGLISH
import com.cosanodev.pokeapp.data.DataConstants.VOCALS_WITH_ACCENT
import com.cosanodev.pokeapp.domain.entity.PokemonDetailEntity
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.text.Normalizer

@Serializable
data class PokemonDetailResponse(
    @SerializedName(value = "id") val id: Int?,
    @SerializedName(value = "name") val name: String?,
    @SerializedName(value = "height") val height: Int?,
    @SerializedName(value = "weight") val weight: Int?,
    @SerializedName(value = "types") val types: List<Type?>,
    @SerializedName(value = "stats") val stats: List<Stats?>
) {
    @Serializable
    data class Type(
        @SerializedName(value = "slot") val slot: Int?,
        @SerializedName(value = "type") val typeInfo: TypeInfo?
    ) {
        @Serializable
        data class TypeInfo(
            @SerializedName(value = "name") val typeName: String?
        )
    }

    @Serializable
    data class Stats(
        @SerializedName(value = "base_stat")val baseStat: Int?,
        @SerializedName(value = "stat") val statInfo: StatInfo?
    ) {
        @Serializable
        data class StatInfo(
            @SerializedName(value = "name")val name: String?
        )
    }
}

@Serializable
data class PokemonSpeciesDetailResponse(
    @SerializedName(value = "flavor_text_entries") val dataEntries: List<DescriptionEntries?>,
    @SerializedName(value = "genera") val shortDescription: List<ShortDescription?>
){
    @Serializable
    data class DescriptionEntries(
        @SerializedName(value = "flavor_text") val descriptionText: String?,
        @SerializedName(value = "language") val languageData: LanguageData?
    ) {
            @Serializable
            data class LanguageData(
                @SerializedName(value = "name") val name: String?
            )
    }

    @Serializable
    data class ShortDescription(
        @SerializedName(value = "genus") val descriptionText: String?,
        @SerializedName(value = "language") val languageData: LanguageData?
    ) {
        @Serializable
        data class LanguageData(
            @SerializedName(value = "name") val name: String?
        )
    }
}


fun mapToPokemonDetailEntity(pokemonDetailResponse: PokemonDetailResponse, pokemonSpeciesDetailResponse: PokemonSpeciesDetailResponse): PokemonDetailEntity {
    return PokemonDetailEntity(
        id = pokemonDetailResponse.id ?: Int.zero ,
        name = pokemonDetailResponse.name?.capitalizeFirstChar() ?: String.noData,
        types = pokemonDetailResponse.types.mapNotNull { it?.typeInfo?.typeName },
        shortDescription = pokemonSpeciesDetailResponse.shortDescription.find {
            it?.languageData?.name == LANGUAGE_ENGLISH
        }?.descriptionText?.cleanFlavorText() ?: String.noData,
        description = pokemonSpeciesDetailResponse.dataEntries.firstOrNull {
            it?.languageData?.name == LANGUAGE_ENGLISH
        }?.descriptionText?.cleanFlavorText()?.normalizeUppercaseWords()?: String.noData,
        height = pokemonDetailResponse.height ?: Int.zero,
        weight = pokemonDetailResponse.weight ?: Int.zero,
        stats = pokemonDetailResponse.stats.mapNotNull { stat ->
            stat?.baseStat?.let { nonNullBaseStat ->
                stat.statInfo?.name?.let { nonNullStatName ->
                    PokemonDetailEntity.Stat(nonNullStatName.capitalizeFirstChar(),nonNullBaseStat)
                }
            }
        }
    )
}

private fun String.normalizeUppercaseWords(): String {
    return this.split(String.space).joinToString(String.space) { word ->
        if (word.length > 1 && word.isUppercaseIgnoringAccentsAndSignsStrict()) {
            word.lowercase().replaceFirstChar { it.titlecase() }
        } else {
            word
        }
    }
}

private fun String.isUppercaseIgnoringAccentsAndSignsStrict(): Boolean {
    for (character in this) {
        when {
            !character.isLetter() -> continue
            character.isUpperCase() -> continue
            character.isMinusLetterWithAccent() -> continue
            else -> return false
        }
    }
    return true
}

private fun Char.isMinusLetterWithAccent(): Boolean {
    return this in VOCALS_WITH_ACCENT
}