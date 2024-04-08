package com.leary.marvelheroesapp.Network.Api

import com.leary.marvelheroesapp.Network.Data.ErrorResponse
import com.leary.marvelheroesapp.Network.Data.MoshiResponse
import com.leary.marvelheroesapp.Network.Enther.Either
import com.leary.marvelheroesapp.Network.Enther.EitherCallAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "http://gateway.marvel.com/v1/public/"

val moshi = Moshi.Builder()
    .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(
        MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(EitherCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface HeroApiService{
    @GET("characters")
    suspend fun getMarvelCharacters(
    ): Either<ErrorResponse, MoshiResponse>

    @GET("characters/{characterId}")
    suspend fun getSingleMarvelCharacter(
        @Path("characterId") id: Int
    ): Either<ErrorResponse, MoshiResponse>

}

object HeroApi{
    val heroesRetrofitService: HeroApiService by lazy {
        retrofit.create(HeroApiService::class.java)
    }
}