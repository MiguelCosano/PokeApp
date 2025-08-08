package com.cosanodev.pokeapp.presentation.core

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()   // data no nullable
    data class Error(val message: String?) : Resource<Nothing>()
}
