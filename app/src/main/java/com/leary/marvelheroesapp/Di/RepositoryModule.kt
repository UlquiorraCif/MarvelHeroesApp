package com.leary.marvelheroesapp.Di

import com.leary.marvelheroesapp.Data.Network.Repositories.HeroRepositoryImpl
import com.leary.marvelheroesapp.Domain.Repositories.HeroRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsHeroRepository(heroRepositoryImpl: HeroRepositoryImpl): HeroRepository
}