package com.leary.marvelheroesapp.Domain

import android.annotation.SuppressLint
import com.leary.marvelheroesapp.Database.HeroDao
import com.leary.marvelheroesapp.Database.HeroDatabaseModel
import com.leary.marvelheroesapp.Network.Api.HeroApi
import com.leary.marvelheroesapp.Network.Data.toEntity
import com.leary.marvelheroesapp.Network.Data.toStringType
import com.leary.marvelheroesapp.Network.Enther.Either
import com.leary.marvelheroesapp.UI.Assets.SampleData
import com.leary.marvelheroesapp.UI.Data.HeroReserve
import com.leary.marvelheroesapp.UI.Data.SingleHeroReserve
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(
private val heroDao: HeroDao
): HeroRepository {

    override suspend fun upsertHero (heroDatabaseModel: HeroDatabaseModel){
        heroDao.upsertHero(heroDatabaseModel)
    }


    override suspend fun updateHero(heroDatabaseModel: HeroDatabaseModel){
        heroDao.updateHero(heroDatabaseModel)
    }

    override suspend fun allHeroes(): Either<HeroReserve, List<HeroDatabaseModel>> {
        val databaseHeroValues = heroDao.getAllHeroes()

        val response = HeroApi.heroesRetrofitService.getMarvelCharacters()

        return when(response){
            is Either.Fail -> {
                if (databaseHeroValues.isEmpty()) {
                    SampleData.heroesSample.map { heroDatabaseModel ->
                        upsertHero(heroDatabaseModel)
                    }
                }

                return Either.fail(
                    HeroReserve(
                        errorMessage = response.value.toStringType(),
                        reserveHeroValues = heroDao.getAllHeroes()
                    )

                )
            }
            is Either.Success -> {

                response.value.data.result.map { heroMoshi ->
                    if(databaseHeroValues.find { it.serverId == heroMoshi.id } == null)
                        upsertHero(heroMoshi.toEntity())
                }

                return Either.Success(
                    heroDao.getAllHeroes()
                )
            }

        }
    }


    @SuppressLint("SuspiciousIndentation")
    override suspend fun singleHero(heroID: Int, heroServerID: String): Either<SingleHeroReserve, HeroDatabaseModel> {
        val databaseHeroValue = heroDao.getSingleHero(heroID)

        return if(databaseHeroValue.description.isEmpty()){
            val response = HeroApi.heroesRetrofitService.getSingleMarvelCharacter(id = heroServerID.toInt())
            when(response) {
                is Either.Fail ->
                    return Either.fail(
                        SingleHeroReserve(
                            errorMessage = response.value.toStringType(),
                            reserveHeroValue = databaseHeroValue
                        )
                    )

                is Either.Success -> {
                    updateHero(response.value.data.result[0].toEntity())
                    return Either.Success(
                        heroDao.getSingleHero(heroID)
                    )
                }
            }
        } else {
            Either.Success(
                databaseHeroValue
            )
        }
    }
}
