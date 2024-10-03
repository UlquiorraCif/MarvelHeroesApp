package com.leary.marvelheroesapp.data.network.models

import com.squareup.moshi.Json

data class MoshiResponseData(
    @Json(name = "results")
    val result: List<HeroNetwork>
)
