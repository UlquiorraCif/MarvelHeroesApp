package com.leary.marvelheroesapp.UI.Screans.HeroScrean

import com.leary.marvelheroesapp.UI.Model.ModelHero

sealed interface HeroScreanUiState{
    data class Success(val singleHeroUIValue: ModelHero): HeroScreanUiState
    data class Error(val errorMessage:String, val reserveSingleHeroUiValue: ModelHero): HeroScreanUiState
    object Loading: HeroScreanUiState
}