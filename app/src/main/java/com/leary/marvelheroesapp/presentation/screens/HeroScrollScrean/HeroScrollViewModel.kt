package com.leary.marvelheroesapp.presentation.screens.HeroScrollScrean

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leary.marvelheroesapp.data.network.enther.Either
import com.leary.marvelheroesapp.data.repositories.HeroRepository
import com.leary.marvelheroesapp.domain.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroScrollViewModel @Inject constructor(val repository: HeroRepository) : ViewModel() {

    // Используем MutableStateFlow для состояния UI
    private val _heroesUiState = MutableStateFlow<HeroScrollUiState>(HeroScrollUiState.Loading)
    val heroesUiState: StateFlow<HeroScrollUiState> = _heroesUiState

    init {
        getHeroesInfo()
    }

    // Функция для получения информации о героях
    fun getHeroesInfo() {
        viewModelScope.launch {
            val heroScrollScreenDomain = repository.allHeroes()
            _heroesUiState.value =
                when (heroScrollScreenDomain) {
                    is Either.Fail -> HeroScrollUiState.Error(
                        errorMessage = heroScrollScreenDomain.value.errorMessage,
                        reserveHeroUiValues = heroScrollScreenDomain.value.reserveHeroValues.mapIndexed { index, heroDatabaseModel ->
                            heroDatabaseModel.toUI(
                                toDetermineHeroNameVisiblePart(heroDatabaseModel.name),
                                toDetermineBackgroundColor(index)
                            )
                        }
                    )
                    is Either.Success -> HeroScrollUiState.Success(
                        heroUIValues = heroScrollScreenDomain.value.mapIndexed { index, heroDatabaseModel ->
                            heroDatabaseModel.toUI(
                                toDetermineHeroNameVisiblePart(heroDatabaseModel.name),
                                toDetermineBackgroundColor(index)
                            )
                        }
                    )
                }
        }
    }



    private fun toDetermineBackgroundColor(index: Int): Color {
        val determinedColor =
            when (index % 7) {
                0 -> Color(119, 3, 8)
                1 -> Color(152, 21, 26)
                2 -> Color(7, 31, 173, 255)
                3 -> Color(2, 92, 5, 255)
                4 -> Color(114, 122, 7, 255)
                5 -> Color(64, 12, 186, 255)
                else -> Color(44, 2, 85, 255)
            }
        return determinedColor
    }

    private fun toDetermineHeroNameVisiblePart(inputHeroName: String): String {
        if (inputHeroName.length > 15) {
            var outputHeroName = ""
            val heroNameArray = inputHeroName.split(" ")

            heroNameArray.forEach { namePart ->
                if ((outputHeroName + namePart).length > 15) {
                    return "$outputHeroName..."
                }
                outputHeroName += namePart

            }
        }
        return inputHeroName
    }
}
