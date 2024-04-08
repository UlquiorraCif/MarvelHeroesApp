package com.leary.marvelheroesapp.UI.Components

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
import androidx.compose.ui.tooling.preview.Preview
import com.leary.marvelheroesapp.R
import com.leary.marvelheroesapp.UI.Theme.Size
import com.leary.marvelheroesapp.UI.Theme.Spaces

@Composable
fun HeroLoading(){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Image(
            modifier = androidx.compose.ui.Modifier.size(Size.loadingLogo.size),
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

@Preview
@Composable
fun HeroLoadingPreview(){
    HeroLoading()
}