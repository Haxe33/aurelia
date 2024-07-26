package com.example.aurelia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aurelia.logic.ZodiacSign
import com.example.aurelia.ui.theme.AureliaTheme
import com.example.aurelia.ui.theme.Heading
import com.example.aurelia.ui.theme.zodiacSignSwiper
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

class Description : ComponentActivity() {

    private val zodiacSigns= listOf(
        ZodiacSign("Capricorn",R.drawable.steinbock),
        ZodiacSign("Aquarius",R.drawable.wassermann),
        ZodiacSign("Pisces",R.drawable.fisch),
        ZodiacSign("Aries",R.drawable.widder),
        ZodiacSign("Taurus",R.drawable.stier),
        ZodiacSign("Gemini",R.drawable.zwilling),
        ZodiacSign("Cancer",R.drawable.krebs),
        ZodiacSign("Leo",R.drawable.loewe),
        ZodiacSign("Virgo",R.drawable.jungfrau),
        ZodiacSign("Libra",R.drawable.waage),
        ZodiacSign("Scorpio",R.drawable.skorpion),
        ZodiacSign("Sagittarius",R.drawable.schuetze)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AureliaTheme {
                //Quelle[L2] for mutable structure
                var currentZodiacSign by remember { mutableStateOf(zodiacSigns[0]) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        currentZodiacSign=zodiacSignSwiper(modifier = Modifier.fillMaxWidth())
                        TopBarSwiper(currentZodiacSign)
                    }
                }
            }
        }
    }
    //Quelle[L3] for swiper logic
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun TopBarSwiper(currentZodiacSign: ZodiacSign) {
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        val tabs = listOf(
            "description", "horoscope", "ascendant", "compatibility checker"
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
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.Black,
                edgePadding = 0.dp,
                indicator = { tabPositions ->
                    SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = Color.White
                    )
                }
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Text(
                                tab,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                count = tabs.size,
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) {page ->
                when (page) {
                    0 -> DescriptionScreen(currentZodiacSign)
                    1 -> HoroscopeScreen(currentZodiacSign)
                    2 -> AscendantScreen(currentZodiacSign)
                    3 -> CompatibilityCheckerScreen(currentZodiacSign)
                }
            }
        }
    }

    @Composable
    private fun HoroscopeScreen(currentZodiacSign: ZodiacSign) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState).padding(15.dp)) {
            Heading(currentZodiacSign.name)
            Spacer(Modifier.height(15.dp))
            Text(
                "HOROSCOPE Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet\n"
            )
        }
        //TODO complete: instead of lorem ipsum one horoscope
    }

    @Composable
    private fun DescriptionScreen(currentZodiacSign: ZodiacSign) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState).padding(15.dp)) {
            Heading(currentZodiacSign.name)
            Spacer(Modifier.height(15.dp))
            Text(
                "DESCRIPTION Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet\n"
            )
            //TODO add description
        }
    }

    @Preview(showBackground = false)
    @Composable
    fun DefaultPreview() {
        AureliaTheme {
            Column {
                var currentZodiacSign by remember { mutableStateOf(zodiacSigns[0]) }
                currentZodiacSign = zodiacSignSwiper(Modifier.padding(vertical = 15.dp))
                TopBarSwiper(currentZodiacSign = currentZodiacSign)
            }
        }
    }
}
