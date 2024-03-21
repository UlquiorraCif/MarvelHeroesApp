package com.leary.marvelheroesapp.Screans

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.leary.marvelheroesapp.Assets.SampleData
import com.leary.marvelheroesapp.Components.HeroCard
import com.leary.marvelheroesapp.Components.HeroHeader

import com.leary.marvelheroesapp.UI.Theme.Theme.MarvelHeroesAppTheme
import com.leary.marvelheroesapp.UI.Theme.Theme.Size
import com.leary.marvelheroesapp.UI.Theme.Theme.Spaces

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroScrollScreen(onHeroImageTaped: (String) -> Unit) {
    val heroValues = SampleData.heroesSample
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
    var rectangleColorState by remember { mutableStateOf(
        heroValues.firstOrNull()?.backgroundColor?.let {
            val colorRes = ContextCompat.getColor(context, it)
            Color(colorRes)
        } ?: Color.Red
    ) }


    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val firstVisibleIndex = lazyListState.layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: -1

                if (firstVisibleIndex != -1) {
                    val currentHero = heroValues.getOrNull(firstVisibleIndex)
                    currentHero?.let {

                        val backgroundColor = ContextCompat.getColor(context, it.backgroundColor)
                        rectangleColorState = Color(backgroundColor)
                    }
                }

                return super.onPostScroll(consumed, available, source)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(color = Color.DarkGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Canvas(
                modifier = Modifier
                    .size(
                        width = Size.rectanglesSizes.width,
                        height = Size.rectanglesSizes.height
                    )
            ) {
                val trianglePath = Path().apply {
                    moveTo(size.width / 1, 0f)
                    lineTo(0f, size.height)
                    lineTo(size.width, size.height)
                    close()
                }
                drawPath(path = trianglePath, color = rectangleColorState)
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(top = Spaces.chooseHeroColumn)
        ) {
            HeroHeader()

            LazyRow(
                modifier = Modifier.fillMaxWidth().nestedScroll(connection = nestedScrollConnection),
                contentPadding = PaddingValues(horizontal = Spaces.chooseHeroLazyRow.horizontalPadding),
                horizontalArrangement = Arrangement.spacedBy(space = Spaces.chooseHeroLazyRow.horizontalArrangement),
                state = lazyListState,
                flingBehavior = snapBehavior
            ) {
                items(heroValues) { hero ->
                    HeroCard(hero, onHeroImageTaped)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarvelHeroesAppTheme{
        HeroScrollScreen(onHeroImageTaped = {})
    }
}