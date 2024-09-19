package com.leary.marvelheroesapp.Di

import android.app.Application
import androidx.room.Room
import com.leary.marvelheroesapp.Database.DatabaseHero
import com.leary.marvelheroesapp.Database.HeroDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHeroDatabase(app: Application): DatabaseHero{
        return Room.databaseBuilder(
            app,
            DatabaseHero::class.java,
            "marvel.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHeroDao(database: DatabaseHero): HeroDao {
        return database.heroDao()
    }
    //    @Provides
    //    @Singleton
    //    fun provideHeroRepository(heroDao: HeroDao): HeroRepository {
    //        return HeroRepositoryImpl(heroDao)
    //    }
}