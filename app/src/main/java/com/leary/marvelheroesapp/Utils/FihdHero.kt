package com.leary.marvelheroesapp.Utils

import com.leary.marvelheroesapp.Assets.SampleData
import com.leary.marvelheroesapp.Model.ModelHero

fun FindHero(name: String): ModelHero {
    val heroValues = SampleData.heroesSample
    return heroValues.find { it.name == name } ?: heroValues[0]
}