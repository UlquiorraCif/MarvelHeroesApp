package com.leary.marvelheroesapp.UI.Screans.HeroScrean

import androidx.compose.runtime.Composable
import com.leary.marvelheroesapp.UI.Components.HeroLoading

@Composable
fun HeroScrean(singleHeroUiState: HeroScreanUiState, navigateUp: () -> Unit){

    when(singleHeroUiState){
        is HeroScreanUiState.Loading -> HeroLoading()
        is HeroScreanUiState.Error -> HeroScreanError(
            errorMessage = singleHeroUiState.errorMessage,
            hero = singleHeroUiState.reserveSingleHeroUiValue,
            navigateUp = navigateUp
        )
        is HeroScreanUiState.Success -> HeroScreanResult(
            hero = singleHeroUiState.singleHeroUIValue,
            navigateUp = navigateUp
        )

    }
}