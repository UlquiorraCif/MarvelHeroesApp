package com.leary.marvelheroesapp.Presentation.Models

import com.leary.marvelheroesapp.Domain.HeroDatabaseModel

data class HeroReserve(
    val errorMessage: String,
    val reserveHeroValues: List<HeroDatabaseModel>
)