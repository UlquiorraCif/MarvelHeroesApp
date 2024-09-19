package com.leary.marvelheroesapp.UI.Screans.HeroScrean

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leary.marvelheroesapp.Database.toHeroUI
import com.leary.marvelheroesapp.Domain.HeroRepository
import com.leary.marvelheroesapp.Network.Enther.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroScreanViewModel @Inject constructor(val repository: HeroRepository): ViewModel() {

    var singleHeroUIState: HeroScreanUiState by mutableStateOf(HeroScreanUiState.Loading)

    fun updateHeroForHeroScrean(id: Int, serverId: String) {

        viewModelScope.launch {
            val heroScreanDomain = repository.singleHero(heroID = id, heroServerID = serverId)
            singleHeroUIState =
                when (heroScreanDomain) {
                    is Either.Fail -> HeroScreanUiState.Error(
                        errorMessage = heroScreanDomain.value.errorMessage,
                        reserveSingleHeroUiValue = heroScreanDomain.value.reserveHeroValue.toHeroUI()
                    )
                    is Either.Success -> HeroScreanUiState.Success(
                        singleHeroUIValue = heroScreanDomain.value.toHeroUI()
                    )
                }
        }
    }
}