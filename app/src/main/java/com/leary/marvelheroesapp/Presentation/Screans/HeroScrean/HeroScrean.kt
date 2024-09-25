package com.leary.marvelheroesapp.Presentation.Screans.HeroScrean

import androidx.compose.runtime.Composable
import com.leary.marvelheroesapp.Presentation.Screans.ActionHero
import com.leary.marvelheroesapp.Presentation.Components.HeroLoading

@Composable
fun HeroScrean(singleHeroUiState: HeroScreanUiState, onAction: (ActionHero) -> Unit){

    when(singleHeroUiState){
        is HeroScreanUiState.Loading -> HeroLoading()
        is HeroScreanUiState.Error -> HeroScreanError(
            errorMessage = singleHeroUiState.errorMessage,
            hero = singleHeroUiState.reserveSingleHeroUiValue,
            onAction = onAction
        )
        is HeroScreanUiState.Success -> HeroScreanResult(
            hero = singleHeroUiState.singleHeroUIValue,
            onAction = onAction
        )

    }
}