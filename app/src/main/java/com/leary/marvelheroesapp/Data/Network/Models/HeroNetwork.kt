package com.leary.marvelheroesapp.Data.Network.Models

import com.leary.marvelheroesapp.Domain.HeroDatabaseModel


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
