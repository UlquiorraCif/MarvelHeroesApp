package com.leary.marvelheroesapp.presentation.screens.HeroScrean

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.leary.marvelheroesapp.presentation.models.ModelHero
import com.leary.marvelheroesapp.presentation.screens.ActionHero
import com.leary.marvelheroesapp.presentation.theme.Shapes
import com.leary.marvelheroesapp.presentation.theme.Size
import com.leary.marvelheroesapp.presentation.theme.Spaces
import com.leary.marvelheroesapp.presentation.theme.interFamily
import com.leary.marvelheroesapp.R

@Composable
fun HeroScreanError(errorMessage: String, hero: ModelHero, onAction: (ActionHero) -> Unit) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spaces.errorColumn)
                .clip(Shapes.medium)
                .background(color = MaterialTheme.colorScheme.secondary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(Size.noInternetLogo.size),
                painter = painterResource(id = R.drawable.loading_img),
                contentDescription = stringResource(R.string.connection_error)
            )
            Spacer(
                modifier = Modifier.size(
                    Spaces.spacer.standartWidth,
                    Spaces.spacer.smallerHeight
                )
            )
            Text(
                text = errorMessage + stringResource(R.string.singlehero_error_message),
                fontFamily = interFamily,
                fontWeight = FontWeight.Bold,
                fontSize = Size.fontSizes.responseError,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        HeroScreanResult(hero = hero, onAction = onAction)
    }
}