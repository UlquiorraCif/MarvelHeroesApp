package com.leary.marvelheroesapp.Domain

import com.leary.marvelheroesapp.Database.HeroDao
import com.leary.marvelheroesapp.Database.HeroDatabaseModel
import com.leary.marvelheroesapp.Network.Api.HeroApi
import com.leary.marvelheroesapp.Network.Data.toEntity
import com.leary.marvelheroesapp.Network.Data.toStringType
import com.leary.marvelheroesapp.Network.Enther.Either
import com.leary.marvelheroesapp.UI.Assets.SampleData

class HeroRepository(private val heroDao: HeroDao) {

    suspend fun upsertHero(heroDatabaseModel: HeroDatabaseModel){
        heroDao.upsertHero(heroDatabaseModel)
    }

    suspend fun updateHero(heroDatabaseModel: HeroDatabaseModel){
        heroDao.updateHero(heroDatabaseModel)
    }

    suspend fun allHeroes(): HeroScrollScreanDomain {
        val databaseHeroValues = heroDao.getAllHeroes()

        val response = HeroApi.heroesRetrofitService.getMarvelCharacters()

        return when(response){
            is Either.Fail -> {
                if(databaseHeroValues.isEmpty()){
                    SampleData.heroesSample.map { heroDatabaseModel ->
                        upsertHero(heroDatabaseModel)
                    }
                }

                HeroScrollScreanDomain.Error(
                    errorMessage = response.value.toStringType(),
                    heroValues = heroDao.getAllHeroes()
                )
            }
            is Either.Success -> {
                response.value.data.result.map { heroNetwork ->
                    if(databaseHeroValues.find { it.serverId == heroNetwork.id } == null)
                        upsertHero(heroNetwork.toEntity())
                }
                HeroScrollScreanDomain.Success(heroValues = heroDao.getAllHeroes())
            }
        }
    }

    suspend fun singleHero(heroID: Int, heroServerID: String): HeroScreanDomain {
        val databaseHeroValue = heroDao.getSingleHero(heroID)

        return if(databaseHeroValue.description.isEmpty()){
            val response = HeroApi.heroesRetrofitService.getSingleMarvelCharacter(id = heroServerID.toInt())
            when(response){
                is Either.Fail -> HeroScreanDomain.Error(
                    errorMessage = response.value.toStringType(),
                    singleHeroValue =  databaseHeroValue
                )
                is Either.Success -> {
                    updateHero(response.value.data.result[0].toEntity())
                    HeroScreanDomain.Success(
                        singleHeroValue = heroDao.getSingleHero(heroID)
                    )
                }
            }
        } else {
            HeroScreanDomain.Success(
                singleHeroValue = databaseHeroValue
            )
        }
    }
}
