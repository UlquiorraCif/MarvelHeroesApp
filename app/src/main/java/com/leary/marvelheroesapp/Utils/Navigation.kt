package com.leary.marvelheroesapp.Utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leary.marvelheroesapp.Assets.SampleData
import com.leary.marvelheroesapp.VievModel.HeroScrollScreen
import com.leary.marvelheroesapp.VievModel.HeroScreen

enum class HeroesScreen {
    Start,
    SingleHero
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController()){

    val heroesState = remember {
        mutableStateOf(
            SampleData.heroesSample[0]
        )
    }

    NavHost(
        navController = navController,
        startDestination = HeroesScreen.Start.name
    ) {
        composable(route = HeroesScreen.Start.name){
            HeroScrollScreen(
                onHeroImageTaped = {heroName ->
                    heroesState.value = FindHero(name = heroName)
                    navController.navigate(HeroesScreen.SingleHero.name)
                }
            )
        }
        composable(route = HeroesScreen.SingleHero.name){
            HeroScreen(
                hero = heroesState.value,
                navigateUp = {navController.navigateUp()}
            )
        }
    }
}
