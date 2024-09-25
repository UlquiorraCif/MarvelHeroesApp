package com.leary.marvelheroesapp.Data.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeroDatabaseModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val serverId: String,
    val name: String,
    val description: String,
    val image: String,
)


