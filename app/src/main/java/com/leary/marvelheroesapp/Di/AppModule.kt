package com.leary.marvelheroesapp.Di

import android.app.Application
import androidx.room.Room
import com.leary.marvelheroesapp.Data.Database.DatabaseHero
import com.leary.marvelheroesapp.Data.Database.HeroDao
import com.leary.marvelheroesapp.Data.Network.Api.HeroApiService
import com.leary.marvelheroesapp.Data.Network.Api.HeroApiService.Companion.BASE_URL
import com.leary.marvelheroesapp.Data.Network.Enther.EitherCallAdapterFactory
import com.leary.marvelheroesapp.Data.Network.Interceptors.AuthInterceptor.Companion.authInterceptor
import com.leary.marvelheroesapp.Data.Network.Interceptors.LogInterceptor.Companion.loggingInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHeroDatabase(app: Application): DatabaseHero {
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

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addNetworkInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideHeroApi(moshi: Moshi, client: OkHttpClient): HeroApiService {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            )
            .addCallAdapterFactory(EitherCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()
            .create(HeroApiService::class.java)
    }

}