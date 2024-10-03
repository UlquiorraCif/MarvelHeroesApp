package com.leary.marvelheroesapp.Presentation.Screans

sealed interface ActionHero {
    data class OnHeroImageTapped(val heroId: Int, val heroSeverId: String) : ActionHero
    data class OnHeroNotificationTapped(val heroId: Int) : ActionHero
    object OnBackToScrollHero : ActionHero
}