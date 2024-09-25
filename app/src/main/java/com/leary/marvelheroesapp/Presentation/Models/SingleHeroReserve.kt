package com.leary.marvelheroesapp.Presentation.Models

import com.leary.marvelheroesapp.Data.Database.HeroDatabaseModel

data class SingleHeroReserve(
    val errorMessage: String,
    val reserveHeroValue: HeroDatabaseModel
)
