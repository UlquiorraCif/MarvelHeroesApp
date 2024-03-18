package com.leary.marvelheroesapp.Utils

import com.leary.marvelheroesapp.Assets.SampleData
import com.leary.marvelheroesapp.Model.ModelHero

fun FindHero(name: String): ModelHero {
    val heroValues = SampleData.heroesSample
    heroValues.forEach { hero ->
        if(hero.name == name){
            return hero
        }
    }
    return heroValues[0]
}