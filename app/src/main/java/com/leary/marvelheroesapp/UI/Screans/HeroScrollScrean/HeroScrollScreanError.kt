package com.leary.marvelheroesapp.UI.Screans.HeroScrollScrean

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
import com.leary.marvelheroesapp.R
import com.leary.marvelheroesapp.UI.Model.ModelHero
import com.leary.marvelheroesapp.UI.Theme.Shapes
import com.leary.marvelheroesapp.UI.Theme.Size
import com.leary.marvelheroesapp.UI.Theme.Spaces
import com.leary.marvelheroesapp.UI.Theme.interFamily

@Composable
fun HeroScrollScreanError(errorMessage: String, heroValues: List<ModelHero>, onHeroImageTaped:(Int, String) -> Unit){
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
                text = errorMessage + stringResource(R.string.choosehero_error_message),
                fontFamily = interFamily,
                fontWeight = FontWeight.Bold,
                fontSize = Size.fontSizes.responseError,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }

        HeroScrollScreanResult(heroValues = heroValues, onHeroImageTaped = onHeroImageTaped)
    }
}