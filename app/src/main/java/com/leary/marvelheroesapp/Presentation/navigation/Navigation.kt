package com.leary.marvelheroesapp.Presentation.Navigations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leary.marvelheroesapp.presentation.screens.ActionHero
import com.leary.marvelheroesapp.presentation.screens.HeroScrean.HeroScrean
import com.leary.marvelheroesapp.presentation.screens.HeroScrean.HeroScreanViewModel
import com.leary.marvelheroesapp.presentation.screens.HeroScrollScrean.HeroScrollScrean
import com.leary.marvelheroesapp.presentation.screens.HeroScrollScrean.HeroScrollViewModel


enum class HeroesScreen {
    Start,
    SingleHero
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController(), heroId: Int) {
    val heroScrollViewModel = hiltViewModel<HeroScrollViewModel>()
    val heroScreanViewModel = hiltViewModel<HeroScreanViewModel>()

    val startDestination: String
    if (heroId > 0) {
        startDestination = HeroesScreen.SingleHero.name
        heroScreanViewModel.onAction(
            ActionHero.OnHeroNotificationTapped(heroId = heroId)
        )
    } else {
        startDestination = HeroesScreen.Start.name
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = HeroesScreen.Start.name) {
            HeroScrollScrean(
                heroesUiState = heroScrollViewModel.heroesUiState,
                onAction = { action ->
                    heroScreanViewModel.onAction(action)
                    navController.navigate(HeroesScreen.SingleHero.name)
                }
            )
        }
        composable(route = HeroesScreen.SingleHero.name) {
            HeroScrean(
                singleHeroUiState = heroScreanViewModel.singleHeroUIState,
                onAction = { action ->
                    val isSuccessfulNavigateUp = navController.navigateUp()
                    if (!isSuccessfulNavigateUp)
                        navController.navigate(HeroesScreen.Start.name)
                    heroScreanViewModel.onAction(action)
                }
            )
        }
    }
}