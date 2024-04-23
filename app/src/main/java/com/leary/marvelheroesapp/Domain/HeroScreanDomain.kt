package com.leary.marvelheroesapp.Domain

import com.leary.marvelheroesapp.Database.HeroDatabaseModel


sealed interface HeroScreanDomain {
    data class Success(val singleHeroValue: HeroDatabaseModel): HeroScreanDomain
    data class Error(val errorMessage:String, val singleHeroValue: HeroDatabaseModel): HeroScreanDomain
    object Loading: HeroScreanDomain
}