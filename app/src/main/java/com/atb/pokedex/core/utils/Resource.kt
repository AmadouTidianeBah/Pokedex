package com.atb.pokedex.core.utils

sealed class Resource<T>(val data: T? = null, val message: String?) {
    class Loading<T>: Resource<T>(null, null)
    class Success<T>(data: T): Resource<T>(data = data, message = null)
    class Error<T>(message: String): Resource<T>(data = null, message = message)
}
