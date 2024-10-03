package com.leary.marvelheroesapp.data.repositories

import android.annotation.SuppressLint
import com.leary.marvelheroesapp.assets.SampleData
import com.leary.marvelheroesapp.data.database.HeroDao
import com.leary.marvelheroesapp.data.database.HeroDatabaseModel
import com.leary.marvelheroesapp.data.network.api.HeroApiService
import com.leary.marvelheroesapp.data.network.enther.Either
import com.leary.marvelheroesapp.data.network.models.toEntity
import com.leary.marvelheroesapp.data.network.models.toStringType
import com.leary.marvelheroesapp.presentation.models.HeroReserve
import com.leary.marvelheroesapp.presentation.models.SingleHeroReserve
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(
    private val heroDao: HeroDao,
    private val heroApiService: HeroApiService
) : HeroRepository {

    override suspend fun upsertHero(heroDatabaseModel: HeroDatabaseModel) {
        heroDao.upsertHero(heroDatabaseModel)
    }


    override suspend fun updateHero(heroDatabaseModel: HeroDatabaseModel) {
        heroDao.updateHero(heroDatabaseModel)
    }

    override suspend fun allHeroes(): Either<HeroReserve, List<HeroDatabaseModel>> {
        val databaseHeroValues = heroDao.getAllHeroes()

        val response = heroApiService.getMarvelCharacters()

        return when (response) {
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
                    if (databaseHeroValues.find { it.serverId == heroMoshi.id } == null)
                        upsertHero(heroMoshi.toEntity())
                }

                return Either.Success(
                    heroDao.getAllHeroes()
                )
            }

        }
    }


    @SuppressLint("SuspiciousIndentation")
    override suspend fun singleHero(
        heroID: Int,
        heroServerID: String
    ): Either<SingleHeroReserve, HeroDatabaseModel> {
        val databaseHeroValue = heroDao.getSingleHero(heroID)
        val verifiedHeroServerID =
            if (heroServerID != "-1") heroServerID else databaseHeroValue.serverId
        if (databaseHeroValue.description.isEmpty()) {
            val response =
                heroApiService.getSingleMarvelCharacter(id = verifiedHeroServerID.toInt())
            when (response) {
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
            return Either.Success(
                databaseHeroValue
            )
        }
    }

    override suspend fun randSingleHero(): HeroDatabaseModel {
        return heroDao.getRandSingleHero()
    }
}
