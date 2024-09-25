package com.leary.marvelheroesapp.Presentation.Screans.HeroScrean

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leary.marvelheroesapp.Data.Network.Enther.Either
import com.leary.marvelheroesapp.Domain.Repositories.HeroRepository
import com.leary.marvelheroesapp.Domain.toHeroUI
import com.leary.marvelheroesapp.Presentation.Screans.ActionHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroScreanViewModel @Inject constructor(private val repository: HeroRepository) :
    ViewModel() {

    var singleHeroUIState: HeroScreanUiState by mutableStateOf(HeroScreanUiState.Loading)
    fun onAction(action: ActionHero) {
        when (action) {
            is ActionHero.OnHeroImageTapped ->
                updateHeroForHeroScrean(
                    id = action.heroId,
                    serverId = action.heroSeverId
                )

            ActionHero.OnBackToScrollHero ->
                singleHeroUIState = HeroScreanUiState.Loading

            is ActionHero.OnHeroNotificationTapped ->
                updateHeroForHeroScrean(
                    id = action.heroId,
                    serverId = "-1"
                )
        }
    }

    private fun updateHeroForHeroScrean(id: Int, serverId: String) {

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