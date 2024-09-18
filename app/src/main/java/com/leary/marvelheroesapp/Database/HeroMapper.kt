package com.leary.marvelheroesapp.Database

import androidx.compose.ui.graphics.Color
import com.leary.marvelheroesapp.UI.Model.ModelHero

object HeroMapper {
    fun toUI(heroDatabaseModel: HeroDatabaseModel, heroName: String, backgroundColor: Color): ModelHero {
        return ModelHero(
            id = heroDatabaseModel.id,
            serverId = heroDatabaseModel.serverId,
            name = heroName,
            description = heroDatabaseModel.description,
            image = heroDatabaseModel.image,
            backgroundColor = backgroundColor
        )
    }

    fun toHeroUI(heroDatabaseModel: HeroDatabaseModel): ModelHero {
        return ModelHero(
            id = heroDatabaseModel.id,
            serverId = heroDatabaseModel.serverId,
            name = heroDatabaseModel.name,
            description = heroDatabaseModel.description,
            image = heroDatabaseModel.image
        )
    }
}