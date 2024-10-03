package com.leary.marvelheroesapp.presentation.screens.HeroScrean

import com.leary.marvelheroesapp.presentation.models.ModelHero

sealed interface HeroScreanUiState {
    data class Success(val singleHeroUIValue: ModelHero) : HeroScreanUiState
    data class Error(val errorMessage: String, val reserveSingleHeroUiValue: ModelHero) :
        HeroScreanUiState

    object Loading : HeroScreanUiState
}