package com.leary.marvelheroesapp.UI.Screans.HeroScrean

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leary.marvelheroesapp.Database.toHeroUI
import com.leary.marvelheroesapp.Domain.HeroRepository
import com.leary.marvelheroesapp.Domain.HeroScreanDomain
import kotlinx.coroutines.launch

class HeroScreanViewModel(val repository: HeroRepository): ViewModel() {


    var singleHeroUIState: HeroScreanUiState by mutableStateOf(HeroScreanUiState.Loading)

    fun updateHeroForHeroScrean(id: Int, serverId: String) {

        viewModelScope.launch {
            val heroScreanDomain = repository.singleHero(heroID = id, heroServerID = serverId)
            singleHeroUIState =
                when (heroScreanDomain) {
                    is HeroScreanDomain.Error -> HeroScreanUiState.Error(
                        errorMessage = heroScreanDomain.errorMessage,
                        reserveSingleHeroUiValue = heroScreanDomain.singleHeroValue.toHeroUI()
                    )
                    is HeroScreanDomain.Success -> HeroScreanUiState.Success(
                        singleHeroUIValue = heroScreanDomain.singleHeroValue.toHeroUI()
                    )
                    is HeroScreanDomain.Loading -> HeroScreanUiState.Loading
                }
        }
    }
}