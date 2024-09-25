package com.leary.marvelheroesapp.Data.Database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.leary.marvelheroesapp.Domain.HeroDatabaseModel

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