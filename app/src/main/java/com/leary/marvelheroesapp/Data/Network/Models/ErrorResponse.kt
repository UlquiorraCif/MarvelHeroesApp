package com.leary.marvelheroesapp.Data.Network.Models

data class ErrorResponse(
    val errorNumber: Int
){
    companion object{
        fun getValue(code: Int): ErrorResponse {
            return ErrorResponse(code)
        }
    }
}

fun ErrorResponse.toStringType()=
    when(errorNumber){
        404 -> "404: Character not found."
        409 -> "409: Invalid or unrecognized parameter."
        700 -> "Network error."
        in 400..499 -> "4XX: Unknown client error."
        in 500..599 -> "5XX: Unknown server error."
        else -> "Unknown error."
    }

