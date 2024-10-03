package com.leary.marvelheroesapp.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface HeroDao {
    @Update
    suspend fun updateHero(hero: HeroDatabaseModel)

    @Upsert
    suspend fun upsertHero(hero: HeroDatabaseModel)

    @Query("SELECT * FROM HeroDatabaseModel")
    suspend fun getAllHeroes(): List<HeroDatabaseModel>

    @Query("SELECT * FROM HeroDatabaseModel WHERE HeroDatabaseModel.id == :heroID")
    suspend fun getSingleHero(heroID: Int): HeroDatabaseModel

    @Query("SELECT * FROM HeroDatabaseModel order by random() limit 1")
    suspend fun getRandSingleHero(): HeroDatabaseModel
}