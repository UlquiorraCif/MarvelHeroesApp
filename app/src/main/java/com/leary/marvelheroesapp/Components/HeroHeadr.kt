package com.leary.marvelheroesapp.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.leary.marvelheroesapp.R
import com.leary.marvelheroesapp.UI.Theme.Theme.Size
import com.leary.marvelheroesapp.UI.Theme.Theme.Spaces
import com.leary.marvelheroesapp.UI.Theme.Theme.interFamily

@Composable
fun HeroHeader(){
    Image(
        painter = painterResource(id = R.drawable.marvel_logo),
        contentDescription = stringResource(id = R.string.marvel),
        modifier = Modifier.size(
            width = Size.marvelLogo.width,
            height = Size.marvelLogo.height
        )
    )
    Spacer(
        modifier = Modifier.size(
            width = Spaces.spacer.standartWidth,
            height = Spaces.spacer.extendedHeight
        )
    )
    Text(
        text = stringResource(id = R.string.choose_hero),
        fontFamily = interFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = Size.fontSizes.underLogoText,
        color = MaterialTheme.colorScheme.onSecondary
    )
    Spacer(
        modifier = Modifier.size(
            width = Spaces.spacer.standartWidth,
            height = Spaces.spacer.theMostExtendedHeight
        )
    )
}