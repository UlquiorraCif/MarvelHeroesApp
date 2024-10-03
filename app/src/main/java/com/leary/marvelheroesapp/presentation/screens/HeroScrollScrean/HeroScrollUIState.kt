package com.leary.marvelheroesapp.presentation.screens.HeroScrollScrean

import com.leary.marvelheroesapp.presentation.models.ModelHero

sealed interface HeroScrollUiState{
    data class Success(val heroUIValues: List<ModelHero>): HeroScrollUiState
    data class Error(val errorMessage: String, val reserveHeroUiValues: List<ModelHero>): HeroScrollUiState
    data object Loading: HeroScrollUiState
}