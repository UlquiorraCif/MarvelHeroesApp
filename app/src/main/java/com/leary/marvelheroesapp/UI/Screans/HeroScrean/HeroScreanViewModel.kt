package com.leary.marvelheroesapp.UI.Screans.HeroScrean

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leary.marvelheroesapp.Network.Api.HeroApi
import com.leary.marvelheroesapp.Network.Data.toSingleUI
import com.leary.marvelheroesapp.Network.Data.toStringType
import com.leary.marvelheroesapp.Network.Enther.Either
import com.leary.marvelheroesapp.UI.Model.ModelHero
import com.leary.marvelheroesapp.UI.Screans.HeroScrollScrean.HeroScrollViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HeroScreanViewModel: ViewModel() {

    private var _reserveSingleHeroUIState = MutableStateFlow(ModelHero())

    var singleHeroUIState: HeroScreanUiState by mutableStateOf(HeroScreanUiState.Loading)

    fun updateHeroForHeroScrean(id: Int, heroName: String) {

        viewModelScope.launch {
            val response = HeroApi.heroesRetrofitService.getSingleMarvelCharacter(id = id)
            singleHeroUIState =
                when (response) {
                    is Either.Fail -> HeroScreanUiState.Error(
                        errorMessage = response.value.toStringType(),
                        reserveSingleHeroUiValue = reserveUpdateHero(heroName = heroName)
                    )
                    is Either.Success -> HeroScreanUiState.Success(
                        singleHeroUIValue = response.value.data.result[0].toSingleUI()
                    )
                }
        }
    }
    fun reserveUpdateHero(heroName: String): ModelHero {
        val chooseHeroViewModel = HeroScrollViewModel()
        val currentHeroValues = chooseHeroViewModel.reserveHeroUIState.value
        _reserveSingleHeroUIState.value = currentHeroValues.find { it.name == heroName }?: ModelHero()

        return _reserveSingleHeroUIState.value
    }
}