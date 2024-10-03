package com.leary.marvelheroesapp.presentation.screens

sealed interface ActionHero {
    data class OnHeroImageTapped(val heroId: Int, val heroSeverId: String) : ActionHero
    data class OnHeroNotificationTapped(val heroId: Int) : ActionHero
    object OnBackToScrollHero : ActionHero
}