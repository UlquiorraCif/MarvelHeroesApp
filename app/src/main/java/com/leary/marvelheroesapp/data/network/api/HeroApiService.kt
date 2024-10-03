package com.leary.marvelheroesapp.data.network.api

import com.leary.marvelheroesapp.data.network.enther.Either
import com.leary.marvelheroesapp.data.network.models.ErrorResponse
import com.leary.marvelheroesapp.data.network.models.MoshiResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface HeroApiService {


    @GET("characters")
    suspend fun getMarvelCharacters(
    ): Either<ErrorResponse, MoshiResponse>

    @GET("characters/{characterId}")
    suspend fun getSingleMarvelCharacter(
        @Path("characterId") id: Int
    ): Either<ErrorResponse, MoshiResponse>

    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
    }
}

