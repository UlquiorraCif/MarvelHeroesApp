package com.leary.marvelheroesapp.presentation.models

import com.leary.marvelheroesapp.data.database.HeroDatabaseModel

data class HeroReserve(
    val errorMessage: String,
    val reserveHeroValues: List<HeroDatabaseModel>
)