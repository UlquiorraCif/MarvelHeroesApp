package com.leary.marvelheroesapp.data.repositories

import com.leary.marvelheroesapp.data.network.enther.Either
import com.leary.marvelheroesapp.data.database.HeroDatabaseModel
import com.leary.marvelheroesapp.presentation.models.HeroReserve
import com.leary.marvelheroesapp.presentation.models.SingleHeroReserve

interface HeroRepository {
    suspend fun upsertHero(heroDatabaseModel: HeroDatabaseModel)

    suspend fun updateHero(heroDatabaseModel: HeroDatabaseModel)

    suspend fun allHeroes(): Either<HeroReserve, List<HeroDatabaseModel>>

    suspend fun singleHero(
        heroID: Int,
        heroServerID: String
    ): Either<SingleHeroReserve, HeroDatabaseModel>

    suspend fun randSingleHero(): HeroDatabaseModel
}
