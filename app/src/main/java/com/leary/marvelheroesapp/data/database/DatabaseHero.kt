package com.leary.marvelheroesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HeroDatabaseModel::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseHero : RoomDatabase() {
    abstract fun heroDao(): HeroDao
}