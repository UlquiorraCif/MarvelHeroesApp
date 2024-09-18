package com.leary.marvelheroesapp.Domain

import com.leary.marvelheroesapp.Database.HeroDatabaseModel

sealed interface HeroScrollScreanDomain {
    data class Success(val heroValues: List<HeroDatabaseModel>):HeroScrollScreanDomain
    data class Error(val errorMessage: String, val heroValues: List<HeroDatabaseModel>):HeroScrollScreanDomain
    object Loading: HeroScrollScreanDomain
}