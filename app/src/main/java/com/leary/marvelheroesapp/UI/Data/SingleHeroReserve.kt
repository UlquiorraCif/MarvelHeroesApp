package com.leary.marvelheroesapp.UI.Data

import com.leary.marvelheroesapp.Database.HeroDatabaseModel

data class SingleHeroReserve(
    val errorMessage: String,
    val reserveHeroValue: HeroDatabaseModel
)
