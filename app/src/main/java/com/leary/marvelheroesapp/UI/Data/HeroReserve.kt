package com.leary.marvelheroesapp.UI.Data

import com.leary.marvelheroesapp.Database.HeroDatabaseModel

data class HeroReserve(
    val errorMessage: String,
    val reserveHeroValues: List<HeroDatabaseModel>
)