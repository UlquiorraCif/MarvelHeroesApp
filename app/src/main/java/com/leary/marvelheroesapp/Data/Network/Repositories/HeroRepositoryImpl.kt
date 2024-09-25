package com.leary.marvelheroesapp.Data.Network.Repositories

import android.annotation.SuppressLint
import com.leary.marvelheroesapp.Assets.SampleData
import com.leary.marvelheroesapp.Data.Database.HeroDao
import com.leary.marvelheroesapp.Data.Network.Api.HeroApiService
import com.leary.marvelheroesapp.Data.Network.Enther.Either
import com.leary.marvelheroesapp.Data.Network.Models.toEntity
import com.leary.marvelheroesapp.Data.Network.Models.toStringType
import com.leary.marvelheroesapp.Domain.HeroDatabaseModel
import com.leary.marvelheroesapp.Domain.Repositories.HeroRepository
import com.leary.marvelheroesapp.Presentation.Models.HeroReserve
import com.leary.marvelheroesapp.Presentation.Models.SingleHeroReserve
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(
    private val heroDao: HeroDao,
    private val heroApiService: HeroApiService
): HeroRepository {

    override suspend fun upsertHero (heroDatabaseModel: HeroDatabaseModel){
        heroDao.upsertHero(heroDatabaseModel)
    }


    override suspend fun updateHero(heroDatabaseModel: HeroDatabaseModel){
        heroDao.updateHero(heroDatabaseModel)
    }

    override suspend fun allHeroes(): Either<HeroReserve, List<HeroDatabaseModel>> {
        val databaseHeroValues = heroDao.getAllHeroes()

        val response = heroApiService.getMarvelCharacters()

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
            val response = heroApiService.getSingleMarvelCharacter(id = heroServerID.toInt())
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
