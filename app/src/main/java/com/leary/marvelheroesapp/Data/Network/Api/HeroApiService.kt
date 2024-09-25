package com.leary.marvelheroesapp.Data.Network.Api

import com.leary.marvelheroesapp.Data.Network.Enther.Either
import com.leary.marvelheroesapp.Data.Network.Models.ErrorResponse
import com.leary.marvelheroesapp.Data.Network.Models.MoshiResponse
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

