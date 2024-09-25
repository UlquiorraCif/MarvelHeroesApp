package com.leary.marvelheroesapp.Presentation.Screans.HeroScrollScrean

import androidx.compose.runtime.Composable
import com.leary.marvelheroesapp.Presentation.Screans.ActionHero
import com.leary.marvelheroesapp.Presentation.Components.HeroLoading

@Composable
fun HeroScrollScrean(
    heroesUiState: HeroScrollUiState,
    onAction: (ActionHero) -> Unit) {

    when(heroesUiState){
        is HeroScrollUiState.Loading -> HeroLoading()
        is HeroScrollUiState.Error -> HeroScrollScreanError(
            errorMessage = heroesUiState.errorMessage,
            heroValues = heroesUiState.reserveHeroUiValues,
            onAction = onAction
        )
        is HeroScrollUiState.Success -> HeroScrollScreanResult(
            heroValues = heroesUiState.heroUIValues,
            onAction = onAction
        )
    }
}
