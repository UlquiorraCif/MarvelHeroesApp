package com.leary.marvelheroesapp.presentation.screens.HeroScrollScrean

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.leary.marvelheroesapp.presentation.components.HeroLoading
import com.leary.marvelheroesapp.presentation.screens.ActionHero
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HeroScrollScrean(
    heroesUiState: StateFlow<HeroScrollUiState>,
    onAction: (ActionHero) -> Unit
) {

    val currentHeroesUiState by heroesUiState.collectAsState()

    when (currentHeroesUiState) {
        is HeroScrollUiState.Loading -> HeroLoading()
        is HeroScrollUiState.Error -> HeroScrollScreanError(
            errorMessage = (currentHeroesUiState as HeroScrollUiState.Error).errorMessage,
            heroValues = (currentHeroesUiState as HeroScrollUiState.Error).reserveHeroUiValues,
            onAction = onAction
        )

        is HeroScrollUiState.Success -> HeroScrollScreanResult(
            heroValues = (currentHeroesUiState as HeroScrollUiState.Success).heroUIValues,
            onAction = onAction
        )
    }
}
