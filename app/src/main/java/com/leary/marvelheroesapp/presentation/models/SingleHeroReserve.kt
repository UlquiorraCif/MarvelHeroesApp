package com.leary.marvelheroesapp.presentation.models

import com.leary.marvelheroesapp.data.database.HeroDatabaseModel

data class SingleHeroReserve(
    val errorMessage: String,
    val reserveHeroValue: HeroDatabaseModel
)
