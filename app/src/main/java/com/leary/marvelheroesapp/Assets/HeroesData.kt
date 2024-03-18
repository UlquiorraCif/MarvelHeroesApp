package com.leary.marvelheroesapp.Assets


import com.leary.marvelheroesapp.Model.ModelHero
import com.leary.marvelheroesapp.R

object SampleData{
    val heroesSample = listOf(
        ModelHero(
            "Deadpool",
            "Please don’t make the super suit green...or animated!",
            "https://iili.io/JMnAfIV.png",
            backgroundColor=R.color.redDeadpool
        ),
        ModelHero(
            "Iron Man",
            "I AM IRON MAN",
            "https://iili.io/JMnuDI2.png",
            backgroundColor=R.color.redIronmean
        ),
        ModelHero(
            "Captain America",
            "I really miss the days when the weirdest thing science ever created was me",
            "https://i.pinimg.com/564x/92/d8/43/92d84304eb42909758a467109cb9dc2b.jpg",
            backgroundColor=R.color.AmericanCap
        ),
        ModelHero(
            "Spiderman",
            "In iron suit",
            "https://iili.io/JMnuyB9.png",
            backgroundColor=R.color.Spiderman
        ),
        ModelHero(
            "Doctor Strange",
            "Faith is my sword, truth my shield, knowledge my armour",
            "https://i.pinimg.com/originals/77/d2/2c/77d22c7e3251e3dfb7197ff6715802c0.jpg",
            backgroundColor= R.color.DoctorStrange
        ),
        ModelHero(
            "Thor",
            "I have much to learn. I know that",
            "https://i.pinimg.com/564x/60/ac/cf/60accf75f680a2780fc390bac3bec0e4.jpg",
            backgroundColor= R.color.Thor
        ),
        ModelHero(
            "Thanos",
            "Fun isn't something when balancing the universe",
            "https://i.pinimg.com/736x/9e/9a/00/9e9a00ae817ceaccd08cd36d24b03c2d.jpg",
            backgroundColor= R.color.Thanos
        ),
    )
}