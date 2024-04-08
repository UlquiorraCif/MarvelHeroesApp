package com.leary.marvelheroesapp.UI.Screans.HeroScrollScrean

import androidx.compose.runtime.Composable
import com.leary.marvelheroesapp.UI.Components.HeroLoading

@Composable
fun HeroScrollScrean(
    heroesUiState: HeroScrollUiState,
    onHeroImageTaped:(Int, String) -> Unit) {

    when(heroesUiState){
        is HeroScrollUiState.Loading -> HeroLoading()
        is HeroScrollUiState.Error -> HeroScrollScreanError(
            errorMessage = heroesUiState.errorMessage,
            heroValues = heroesUiState.reserveHeroUiValues,
            onHeroImageTaped = onHeroImageTaped
        )
        is HeroScrollUiState.Success -> HeroScrollScreanResult(
            heroValues = heroesUiState.heroUIValues,
            onHeroImageTaped = onHeroImageTaped
        )
    }
}
