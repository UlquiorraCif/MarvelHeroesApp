package com.leary.marvelheroesapp.Presentation.Screans

sealed interface ActionHero {
    data class OnHeroImageTapped(val heroId: Int, val heroSeverId: String): ActionHero
    object OnBackToScrollHero: ActionHero
}