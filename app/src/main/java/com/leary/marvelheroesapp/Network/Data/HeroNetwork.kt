package com.leary.marvelheroesapp.Network.Data

import androidx.compose.ui.graphics.Color
import com.leary.marvelheroesapp.UI.Model.ModelHero


data class HeroNetwork(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)


fun HeroNetwork.toUI(
    heroName: String,
    backgroundColor: Color
) =
    ModelHero(
        id = id.toInt(),
        name = if(id.toInt() < 100) name else heroName,
        image = thumbnail.path + "." +  thumbnail.extension,
        backgroundColor = backgroundColor
    )

fun HeroNetwork.toSingleUI() =
    ModelHero(
        id = id.toInt(),
        name = name,
        description = description,
        image = thumbnail.path + "." +  thumbnail.extension
    )