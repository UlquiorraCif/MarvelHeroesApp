package com.leary.marvelheroesapp.Presentation.Screans.HeroScrean

import com.leary.marvelheroesapp.Presentation.Models.ModelHero

sealed interface HeroScreanUiState {
    data class Success(val singleHeroUIValue: ModelHero) : HeroScreanUiState
    data class Error(val errorMessage: String, val reserveSingleHeroUiValue: ModelHero) :
        HeroScreanUiState

    object Loading : HeroScreanUiState
}