package com.leary.marvelheroesapp.Presentation.Models

import com.leary.marvelheroesapp.Domain.HeroDatabaseModel

data class SingleHeroReserve(
    val errorMessage: String,
    val reserveHeroValue: HeroDatabaseModel
)
