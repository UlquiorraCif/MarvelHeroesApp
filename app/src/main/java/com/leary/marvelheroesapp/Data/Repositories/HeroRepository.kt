package com.leary.marvelheroesapp.Data.Repositories

import com.leary.marvelheroesapp.Data.Network.Enther.Either
import com.leary.marvelheroesapp.Data.Database.HeroDatabaseModel
import com.leary.marvelheroesapp.Presentation.Models.HeroReserve
import com.leary.marvelheroesapp.Presentation.Models.SingleHeroReserve

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
