package com.leary.marvelheroesapp.Presentation.Screans.HeroScrollScrean

import com.leary.marvelheroesapp.Presentation.Models.ModelHero

sealed interface HeroScrollUiState{
    data class Success(val heroUIValues: List<ModelHero>): HeroScrollUiState
    data class Error(val errorMessage: String, val reserveHeroUiValues: List<ModelHero>): HeroScrollUiState
    data object Loading: HeroScrollUiState
}