package com.leary.marvelheroesapp.UI.Screans.HeroScrollScrean

import com.leary.marvelheroesapp.UI.Model.ModelHero

sealed interface HeroScrollUiState{
    data class Success(val heroUIValues: List<ModelHero>): HeroScrollUiState
    data class Error(val errorMessage: String, val reserveHeroUiValues: List<ModelHero>): HeroScrollUiState
    object Loading: HeroScrollUiState
}