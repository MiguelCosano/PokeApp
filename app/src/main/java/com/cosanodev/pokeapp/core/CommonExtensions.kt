package com.cosanodev.pokeapp.core

val String.Companion.noData get() = "-"
val String.Companion.space get() = " "
val String.Companion.empty get() = ""
fun String.capitalizeFirstChar(): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

val Int.Companion.zero get() = 0
fun String.cleanFlavorText(): String =
    this.replace(Regex("(?<![-\\w])\n|\n(?!\\w)"), String.empty)
        .replace("\n", String.space)
        .replace("\u000C", String.space)
        .replace(".", ". ")
        .replace(",", ", ")
        .replace("  ", String.space)
        .trim()