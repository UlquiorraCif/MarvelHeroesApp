package com.leary.marvelheroesapp.Database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [HeroDatabaseModel::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseHero: RoomDatabase() {
    abstract fun heroDao(): HeroDao
    companion object{
        @Volatile
        private var INSTANCE: DatabaseHero? = null

        fun getDatabase(context: Context): DatabaseHero {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHero::class.java,
                    "marvel.db"
                ).build()
                Log.d("Database", "Database created successfully")
                INSTANCE = instance
                instance
            }

        }
    }
}