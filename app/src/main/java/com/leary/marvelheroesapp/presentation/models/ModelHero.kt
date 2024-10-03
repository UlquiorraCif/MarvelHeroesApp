package com.leary.marvelheroesapp.presentation.models

import androidx.compose.ui.graphics.Color

data class ModelHero(
    val id: Int = 0,
    val serverId: String = "1111",
    val name: String = "",
    val description: String = "",
    val image: String=" ",
    val backgroundColor: Color = Color(119, 3,8)
)