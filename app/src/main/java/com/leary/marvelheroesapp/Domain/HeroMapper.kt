package com.leary.marvelheroesapp.Domain

import androidx.compose.ui.graphics.Color
import com.leary.marvelheroesapp.Presentation.Models.ModelHero


fun HeroDatabaseModel.toUI(
    heroName: String,
    backgroundColor: Color
) = ModelHero(
    id = id,
    serverId = serverId,
    name = heroName,
    description = description,
    image = image,
    backgroundColor = backgroundColor
)

fun HeroDatabaseModel.toHeroUI() =
    ModelHero(
        id = id,
        serverId = serverId,
        name = name,
        description = description,
        image = image
    )