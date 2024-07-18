package com.example.aurelia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.aurelia.ui.theme.AureliaTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

class Description : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AureliaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ZodiacSignSwiper(modifier = Modifier.fillMaxWidth())
                        TopBarSwiper()
                    }
                }
            }
        }
    }
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun TopBarSwiper() {
        var selectedTabIndex by remember { mutableStateOf(0) }

        val tabs = listOf(
            "description", "horoscope", "ascendant", "compatibility check"
        )

        val pagerState = rememberPagerState(initialPage = 0)

        LaunchedEffect(key1 = selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }

        LaunchedEffect(key1 = pagerState.currentPage, key2 = pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress) {
                selectedTabIndex = pagerState.currentPage
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.Black,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = Color.White,
                    )
                }
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = { Text(tab, textAlign = TextAlign.Center, color = Color.White) },
                    )
                }
            }

            HorizontalPager(
                count = tabs.size,
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) {/*page ->
                when (page) {
                    0 -> DescriptionScreen()
                    1 -> HoroscopeScreen()
                    2 -> AscendantScreen()
                    3 -> CompatibilityCheckScreen()
                }*/
            }
        }
    }

    private @Composable
    fun CompatibilityCheckScreen() {
        TODO("Not yet implemented")
    }

    private @Composable
    fun AscendantScreen() {
        TODO("Not yet implemented")
    }

    private @Composable
    fun HoroscopeScreen() {
        TODO("Not yet implemented")
    }

    private @Composable
    fun DescriptionScreen() {
        TODO("Not yet implemented")
    }

    //Quelle: [L1]
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun ZodiacSignSwiper(modifier: Modifier){
        val pagerState = rememberPagerState(initialPage = 0)
        val imageSlider = listOf(
            painterResource(id = R.drawable.steinbock),
            painterResource(id = R.drawable.wassermann),
            painterResource(id = R.drawable.fisch),
            painterResource(id = R.drawable.widder),
            painterResource(id = R.drawable.stier),
            painterResource(id = R.drawable.zwilling),
            painterResource(id = R.drawable.krebs),
            painterResource(id = R.drawable.loewe),
            painterResource(id = R.drawable.jungfrau),
            painterResource(id = R.drawable.waage),
            painterResource(id = R.drawable.skorpion),
            painterResource(id = R.drawable.schuetze)
        )
        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(2600)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % (pagerState.pageCount)
                )
            }
        }
        Column {
            HorizontalPager(
                count = imageSlider.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth()
            ) { page ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }

                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                ) {
                    Image(
                        painter = imageSlider[page],
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AureliaTheme {
            Column {
                ZodiacSignSwiper(Modifier.padding(vertical = 15.dp))
                TopBarSwiper()
            }
        }
    }
}
