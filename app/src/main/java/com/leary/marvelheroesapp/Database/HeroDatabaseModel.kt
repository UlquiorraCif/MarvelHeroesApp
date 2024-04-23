package com.leary.marvelheroesapp.Database

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leary.marvelheroesapp.UI.Model.ModelHero

@Entity
data class HeroDatabaseModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val serverId: String,
    val name: String,
    val description: String,
    val image: String,
)

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
