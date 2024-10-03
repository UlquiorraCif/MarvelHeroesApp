package com.leary.marvelheroesapp.assets


import com.leary.marvelheroesapp.data.database.HeroDatabaseModel


object SampleData{
    val heroesSample = listOf(
        HeroDatabaseModel(
            serverId = "91",
            name = "Deadpool",
            description = "Please don’t make the super suit green...or animated!",
            image = "https://iili.io/JMnAfIV.png"
        ),
        HeroDatabaseModel(
            serverId = "92",
            name = "Iron Man",
            description = "I AM IRON MAN",
            image = "https://iili.io/JMnuDI2.png"
        ),
        HeroDatabaseModel(
            serverId = "93",
            name = "Captain America",
            description = "I really miss the days when the weirdest thing science ever created was me",
            image = "https://i.pinimg.com/564x/92/d8/43/92d84304eb42909758a467109cb9dc2b.jpg"
        ),
        HeroDatabaseModel(
            serverId = "94",
            name = "Spiderman",
            description = "In iron suit",
            image = "https://iili.io/JMnuyB9.png"
        ),
        HeroDatabaseModel(
            serverId = "95",
            name = "Doctor Strange",
            description = "Faith is my sword, truth my shield, knowledge my armour",
            image = "https://i.pinimg.com/564x/60/ac/cf/60accf75f680a2780fc390bac3bec0e4.jpg"
        ),
        HeroDatabaseModel(
            serverId = "96",
            name = "Thor",
            description = "I have much to learn. I know that",
            image = "https://i.pinimg.com/564x/60/ac/cf/60accf75f680a2780fc390bac3bec0e4.jpg"
        ),
        HeroDatabaseModel(
            serverId = "97",
            name = "Thanos",
            description = "Fun isn't something when balancing the universe",
            image = "https://i.pinimg.com/736x/9e/9a/00/9e9a00ae817ceaccd08cd36d24b03c2d.jpg"
        )
    )
}