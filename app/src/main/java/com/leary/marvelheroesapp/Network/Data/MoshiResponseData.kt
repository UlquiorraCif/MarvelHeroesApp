package com.leary.marvelheroesapp.Network.Data

import com.squareup.moshi.Json

data class MoshiResponseData(
    @Json(name = "results")
    val result: List<HeroMoshi>
)
