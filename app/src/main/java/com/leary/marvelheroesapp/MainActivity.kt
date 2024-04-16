package com.leary.marvelheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.leary.marvelheroesapp.UI.Navigations.Navigation
import com.leary.marvelheroesapp.UI.Theme.MarvelHeroesAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window,false)

        setContent {
            MarvelHeroesAppTheme {

                ApplySystemBarColors()
                val backgroundColor = if (isSystemInDarkTheme()) {
                    Color.DarkGray
                } else {
                    Color.Gray
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor
                ) {
                    Navigation()
                }
            }
        }
    }
    @Composable
    fun ApplySystemBarColors(){
        val systemUiContrller = rememberSystemUiController()

        SideEffect {
            systemUiContrller.setStatusBarColor(color = Color.Transparent)
            systemUiContrller.setNavigationBarColor(color = Color.Transparent)
        }
    }
}

