package com.leary.marvelheroesapp.Domain

import com.leary.marvelheroesapp.Database.HeroDatabaseModel
import com.leary.marvelheroesapp.Network.Enther.Either
import com.leary.marvelheroesapp.UI.Data.HeroReserve
import com.leary.marvelheroesapp.UI.Data.SingleHeroReserve

interface HeroRepository{
    suspend fun upsertHero(heroDatabaseModel: HeroDatabaseModel)

    suspend fun updateHero(heroDatabaseModel: HeroDatabaseModel)

    suspend fun allHeroes(): Either<HeroReserve, List<HeroDatabaseModel>>


    suspend fun singleHero(heroID: Int, heroServerID: String): Either<SingleHeroReserve, HeroDatabaseModel>
}
