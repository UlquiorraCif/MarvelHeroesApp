package com.leary.marvelheroesapp.UI.Screans.HeroScrollScrean

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leary.marvelheroesapp.Network.Api.HeroApi
import com.leary.marvelheroesapp.Network.Data.toStringType
import com.leary.marvelheroesapp.Network.Data.toUI
import com.leary.marvelheroesapp.Network.Enther.Either
import com.leary.marvelheroesapp.UI.Assets.SampleData
import com.leary.marvelheroesapp.UI.Model.ModelHero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeroScrollViewModel: ViewModel() {

    private var _reserveHeroUIState = MutableStateFlow(listOf( ModelHero()))
    val reserveHeroUIState: StateFlow<List<ModelHero>> = _reserveHeroUIState.asStateFlow()

    var heroesUiState: HeroScrollUiState by mutableStateOf(HeroScrollUiState.Loading)

    init {
        getHeroesInfo()
    }

    fun getHeroesInfo() {
        _reserveHeroUIState.value = SampleData.heroesSample

        viewModelScope.launch {
            val response = HeroApi.heroesRetrofitService.getMarvelCharacters()
            heroesUiState =
                when (response) {
                    is Either.Fail -> HeroScrollUiState.Error(
                        errorMessage = response.value.toStringType(),
                        reserveHeroUiValues = _reserveHeroUIState.value
                    )
                    is Either.Success -> HeroScrollUiState.Success(
                        heroUIValues = response.value.data.result.mapIndexed { index, heroMoshi ->
                            heroMoshi.toUI(
                                toDetermineHeroNameVisiblePart(heroMoshi.name),
                                toDetermineBackgroundColor(index)
                            )
                        }
                    )
                }
        }
    }

    fun toDetermineBackgroundColor(index: Int): Color {
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

    fun toDetermineHeroNameVisiblePart(inputHeroName: String): String{
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
