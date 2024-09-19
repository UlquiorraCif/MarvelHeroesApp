package com.leary.marvelheroesapp.Presentation.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leary.marvelheroesapp.R
import com.leary.marvelheroesapp.Presentation.Screans.ActionHero
import com.leary.marvelheroesapp.Presentation.Models.ModelHero
import com.leary.marvelheroesapp.Presentation.Theme.Shapes
import com.leary.marvelheroesapp.Presentation.Theme.Size
import com.leary.marvelheroesapp.Presentation.Theme.Spaces
import com.leary.marvelheroesapp.Presentation.Theme.interFamily

@Composable
fun HeroCard(hero: ModelHero, onAction: (ActionHero) -> Unit){
    Box(
        modifier = Modifier
            .clickable{onAction(
                ActionHero.OnHeroImageTapped(
                    hero.id,
                    hero.serverId
                )
            )}
            .shadow(
                elevation = Spaces.shadowElevation,
                shape = Shapes.medium,
                ambientColor = MaterialTheme.colorScheme.onBackground,
                spotColor = MaterialTheme.colorScheme.onBackground
            )
    ){
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(hero.image)
                .build(),
            contentDescription = hero.name,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.loading),
            modifier = Modifier
                .size(
                    width = Size.heroCard.width,
                    height = Size.heroCard.height
                )
                .clip(Shapes.medium)
        )
        Text(
            text = hero.name,
            fontFamily = interFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = Size.fontSizes.heroNameInCard,
            color = MaterialTheme.colorScheme.onSecondary,
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