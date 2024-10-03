package com.leary.marvelheroesapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.leary.marvelheroesapp.R
import com.leary.marvelheroesapp.presentation.theme.Size
import com.leary.marvelheroesapp.presentation.theme.Spaces

@Composable
fun HeroLoading(){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Image(
            modifier = Modifier.size(Size.loadingLogo.size),
            painter = painterResource(R.drawable.marvel_logo),
            contentDescription = stringResource(R.string.loading)
        )
        Spacer(
            modifier = Modifier.size(
                Spaces.spacer.standartWidth,
                Spaces.spacer.smallerHeight
            )
        )

    }
}

