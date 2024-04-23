package com.leary.marvelheroesapp.Network.Data

import com.leary.marvelheroesapp.Database.HeroDatabaseModel


data class HeroNetwork(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

fun HeroNetwork.toEntity() =
    HeroDatabaseModel(
        serverId = id,
        name = name,
        description = description,
        image = thumbnail.path + "." +  thumbnail.extension
    )
