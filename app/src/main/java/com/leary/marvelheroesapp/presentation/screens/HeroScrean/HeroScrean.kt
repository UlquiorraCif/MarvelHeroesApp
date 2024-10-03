package com.leary.marvelheroesapp.presentation.screens.HeroScrean

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.leary.marvelheroesapp.presentation.components.HeroLoading
import com.leary.marvelheroesapp.presentation.screens.ActionHero
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HeroScrean(singleHeroUiState: StateFlow<HeroScreanUiState>, onAction: (ActionHero) -> Unit) {

    val heroScreanUiState by singleHeroUiState.collectAsState()

    when (heroScreanUiState) {
        is HeroScreanUiState.Loading -> HeroLoading()
        is HeroScreanUiState.Error -> HeroScreanError(
            errorMessage = (heroScreanUiState as HeroScreanUiState.Error).errorMessage,
            hero = (heroScreanUiState as HeroScreanUiState.Error).reserveSingleHeroUiValue,
            onAction = onAction
        )
        is HeroScreanUiState.Success -> HeroScreanResult(
            hero = (heroScreanUiState as HeroScreanUiState.Success).singleHeroUIValue,
            onAction = onAction
        )
    }
}
