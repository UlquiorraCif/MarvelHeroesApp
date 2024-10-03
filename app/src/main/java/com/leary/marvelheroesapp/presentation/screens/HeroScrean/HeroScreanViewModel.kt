package com.leary.marvelheroesapp.presentation.screens.HeroScrean

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leary.marvelheroesapp.data.network.enther.Either
import com.leary.marvelheroesapp.data.repositories.HeroRepository
import com.leary.marvelheroesapp.domain.toHeroUI
import com.leary.marvelheroesapp.presentation.screens.ActionHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroScreanViewModel @Inject constructor(private val repository: HeroRepository) :
    ViewModel() {

    // Используем MutableStateFlow вместо mutableStateOf
    private val _singleHeroUIState = MutableStateFlow<HeroScreanUiState>(HeroScreanUiState.Loading)
    val singleHeroUIState: StateFlow<HeroScreanUiState> = _singleHeroUIState

    fun onAction(action: ActionHero) {
        when (action) {
            is ActionHero.OnHeroImageTapped ->
                updateHeroForHeroScrean(
                    id = action.heroId,
                    serverId = action.heroSeverId
                )

            ActionHero.OnBackToScrollHero ->
                _singleHeroUIState.value = HeroScreanUiState.Loading

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
            _singleHeroUIState.value =
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