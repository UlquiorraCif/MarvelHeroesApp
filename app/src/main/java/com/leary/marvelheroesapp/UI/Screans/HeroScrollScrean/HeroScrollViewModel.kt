package com.leary.marvelheroesapp.UI.Screans.HeroScrollScrean

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leary.marvelheroesapp.Database.HeroMapper
import com.leary.marvelheroesapp.Domain.HeroRepository
import com.leary.marvelheroesapp.Domain.HeroScrollScreanDomain
import kotlinx.coroutines.launch

class HeroScrollViewModel(val repository: HeroRepository) : ViewModel() {

    var heroesUiState: HeroScrollUiState by mutableStateOf(HeroScrollUiState.Loading)

    init {
        getHeroesInfo()
    }

    fun getHeroesInfo() {
        viewModelScope.launch {
            val heroScrollScreenDomain = repository.allHeroes()
            heroesUiState = when (heroScrollScreenDomain) {
                is HeroScrollScreanDomain.Error -> {
                    val reserveHeroUiValues = heroScrollScreenDomain.heroValues.mapIndexed { index, heroEntity ->
                        HeroMapper.toUI(
                            heroDatabaseModel = heroEntity,
                            heroName = toDetermineHeroNameVisiblePart(heroEntity.name),
                            backgroundColor = toDetermineBackgroundColor(index)
                        )
                    }
                    HeroScrollUiState.Error(errorMessage = heroScrollScreenDomain.errorMessage, reserveHeroUiValues)
                }
                is HeroScrollScreanDomain.Success -> {
                    val heroUIValues = heroScrollScreenDomain.heroValues.mapIndexed { index, heroEntity ->
                        HeroMapper.toUI(
                            heroDatabaseModel = heroEntity,
                            heroName = toDetermineHeroNameVisiblePart(heroEntity.name),
                            backgroundColor = toDetermineBackgroundColor(index)
                        )
                    }
                    HeroScrollUiState.Success(heroUIValues)
                }
                is HeroScrollScreanDomain.Loading -> HeroScrollUiState.Loading
            }
        }
    }


    private    fun toDetermineBackgroundColor(index: Int): Color {
        val determinedColor =
            when(index % 7){
                0 -> Color(119, 3,8)
                1 -> Color(152, 21,26)
                2 -> Color(7, 31, 173, 255)
                3 -> Color(2, 92, 5, 255)
                4 -> Color(114, 122, 7, 255)
                5 -> Color(64, 12, 186, 255)
                else -> Color(44, 2, 85, 255)
            }
        return determinedColor
    }

    private   fun toDetermineHeroNameVisiblePart(inputHeroName: String): String{
        if(inputHeroName.length > 15){
            var outputHeroName = ""
            val heroNameArray = inputHeroName.split(" ")

            heroNameArray.forEach { namePart ->
                if((outputHeroName + namePart).length > 15){
                    return "$outputHeroName..."
                }
                outputHeroName += namePart

            }
        }
        return inputHeroName
    }
}
