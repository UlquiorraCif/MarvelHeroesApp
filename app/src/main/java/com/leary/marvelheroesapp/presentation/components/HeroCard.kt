package com.leary.marvelheroesapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leary.marvelheroesapp.presentation.models.ModelHero
import com.leary.marvelheroesapp.presentation.screens.ActionHero
import com.leary.marvelheroesapp.presentation.theme.Shapes
import com.leary.marvelheroesapp.presentation.theme.Size
import com.leary.marvelheroesapp.presentation.theme.Spaces
import com.leary.marvelheroesapp.presentation.theme.interFamily
import com.leary.marvelheroesapp.presentation.utils.isLandscape
import com.leary.marvelheroesapp.R

@Composable
fun HeroCard(hero: ModelHero, onAction: (ActionHero) -> Unit) {
    Box(
        modifier = Modifier
            .clickable {
                onAction(
                    ActionHero.OnHeroImageTapped(
                        hero.id,
                        hero.serverId
                    )
                )
            }
            .size(
                width =
                if (isLandscape())
                    Size.heroCardLandscape.width
                else
                    Size.heroCard.width,
                height =
                if (isLandscape())
                    Size.heroCardLandscape.height
                else
                    Size.heroCard.height
            )
            .shadow(
                elevation = Spaces.shadowElevation,
                shape = Shapes.medium,
                ambientColor = MaterialTheme.colorScheme.onBackground,
                spotColor = MaterialTheme.colorScheme.onBackground
            )
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(hero.image)
                .build(),
            contentDescription = hero.name,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.loading),
            modifier = Modifier
                .fillMaxSize()
                .clip(Shapes.medium)
        )
        Text(
            text = hero.name,
            fontFamily = interFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize =
            if(isLandscape())
                Size.fontSizes.heroNameInCardLandscape
            else
                Size.fontSizes.heroNameInCard,
            color = MaterialTheme.colorScheme.onSecondary,
            style = LocalTextStyle.current.copy(textDirection = TextDirection.Content),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = Spaces.heroCardText.start,
                    bottom = Spaces.heroCardText.bottom,
                    end = Spaces.heroCardText.end
                )
        )
    }
}