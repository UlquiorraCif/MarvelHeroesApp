package com.leary.marvelheroesapp.Data.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leary.marvelheroesapp.Domain.HeroDatabaseModel

@Database(
    entities = [HeroDatabaseModel::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseHero: RoomDatabase() {
    abstract fun heroDao(): HeroDao
}