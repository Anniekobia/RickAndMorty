package com.example.rickandmorty.util

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class APIError(val errorMessage: String) : NetworkResult<Nothing>()
    object NoInternetError : NetworkResult<Nothing>()
    object ServerError : NetworkResult<Nothing>()
}
