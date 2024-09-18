package com.leary.marvelheroesapp.UI.Navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leary.marvelheroesapp.Database.DatabaseHero
import com.leary.marvelheroesapp.Domain.HeroRepository
import com.leary.marvelheroesapp.UI.Screans.HeroScrean.HeroScrean
import com.leary.marvelheroesapp.UI.Screans.HeroScrean.HeroScreanUiState
import com.leary.marvelheroesapp.UI.Screans.HeroScrean.HeroScreanViewModel
import com.leary.marvelheroesapp.UI.Screans.HeroScrollScrean.HeroScrollScrean
import com.leary.marvelheroesapp.UI.Screans.HeroScrollScrean.HeroScrollViewModel


enum class HeroesScreen {
    Start,
    SingleHero
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController()){
    val context = LocalContext.current
    val database by lazy {
        DatabaseHero.getDatabase(context = context)
    }
    val repository by lazy {
        HeroRepository(database.heroDao())
    }
    val heroScrollViewModel = HeroScrollViewModel(repository = repository)
    val heroScreanViewModel = HeroScreanViewModel(repository = repository)

    NavHost(
        navController = navController,
        startDestination = HeroesScreen.Start.name
    ) {
        composable(route = HeroesScreen.Start.name){
            HeroScrollScrean(
                heroesUiState = heroScrollViewModel.heroesUiState,
                onHeroImageTaped = {id, serverId ->
                    heroScreanViewModel.updateHeroForHeroScrean(id = id, serverId = serverId)
                    navController.navigate(HeroesScreen.SingleHero.name)
                }
            )

        }
        composable(route = HeroesScreen.SingleHero.name){
            HeroScrean(
                singleHeroUiState = heroScreanViewModel.singleHeroUIState,
                navigateUp = { navController.navigateUp()
                    heroScreanViewModel.singleHeroUIState = HeroScreanUiState.Loading}
            )
        }
    }
}
