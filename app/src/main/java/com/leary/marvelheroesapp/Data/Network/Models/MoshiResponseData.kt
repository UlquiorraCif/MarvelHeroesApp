package com.leary.marvelheroesapp.Data.Network.Models

import com.squareup.moshi.Json

data class MoshiResponseData(
    @Json(name = "results")
    val result: List<HeroNetwork>
)
