package com.leary.marvelheroesapp.UI.Navigations

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leary.marvelheroesapp.UI.Screans.HeroScrean.HeroScrean
import com.leary.marvelheroesapp.UI.Screans.HeroScrean.HeroScreanViewModel
import com.leary.marvelheroesapp.UI.Screans.HeroScrollScrean.HeroScrollScrean
import com.leary.marvelheroesapp.UI.Screans.HeroScrollScrean.HeroScrollViewModel


enum class HeroesScreen {
    Start,
    SingleHero
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController()){
    val heroViewModel: HeroScrollViewModel = viewModel()
    val singleHeroViewModel: HeroScreanViewModel = viewModel()



    NavHost(
        navController = navController,
        startDestination = HeroesScreen.Start.name
    ) {
        composable(route = HeroesScreen.Start.name){
            HeroScrollScrean(heroesUiState =  heroViewModel.heroesUiState,)
            {id, heroName ->
                singleHeroViewModel.updateHeroForHeroScrean(id = id, heroName = heroName)
                navController.navigate(HeroesScreen.SingleHero.name)
            }
        }
        composable(route = HeroesScreen.SingleHero.name){
            HeroScrean(
                singleHeroUiState = singleHeroViewModel.singleHeroUIState,
                navigateUp = {navController.navigateUp()}
            )
        }
    }
}
