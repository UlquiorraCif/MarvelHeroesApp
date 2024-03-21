package com.leary.marvelheroesapp.Screans

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leary.marvelheroesapp.Components.HeroText
import com.leary.marvelheroesapp.Model.ModelHero
import com.leary.marvelheroesapp.R
import com.leary.marvelheroesapp.UI.Theme.Theme.Size
import com.leary.marvelheroesapp.UI.Theme.Theme.Spaces

@Composable
fun HeroScrean(hero: ModelHero, navigateUp: () -> Unit){
    Box (modifier = Modifier.fillMaxSize()){
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(hero.image)
                .build(),
            contentDescription = hero.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = Spaces.singleHeroColumn
                )
        ) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.size(
                        width = Size.backIcon.width,
                        height = Size.backIcon.height
                    ),
                    contentDescription = stringResource(R.string.back_button)
                )
            }
            HeroText(hero = hero)

        }
    }

}

