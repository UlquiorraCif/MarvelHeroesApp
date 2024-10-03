package com.leary.marvelheroesapp.data.network.models

import com.leary.marvelheroesapp.data.database.HeroDatabaseModel


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
        image = thumbnail.path + "." + thumbnail.extension
    )
