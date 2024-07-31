package com.example.aurelia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.aurelia.logic.ZodiacSign
import com.example.aurelia.logic.getDescription
import com.example.aurelia.ui.theme.AureliaTheme
import com.example.aurelia.ui.theme.FontText
import com.example.aurelia.ui.theme.Heading
import com.example.aurelia.ui.theme.aTypewriter
import com.example.aurelia.ui.theme.zodiacSignSwiper
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

class Description : ComponentActivity() {

    private val zodiacSigns= listOf(
        ZodiacSign("Capricorn", R.drawable.steinbock,R.raw.steinbock_compatibility),
        ZodiacSign("Aquarius", R.drawable.wassermann, R.raw.wassermann_compatibility),
        ZodiacSign("Pisces", R.drawable.fisch,R.raw.fisch_compatibility),
        ZodiacSign("Aries", R.drawable.widder,R.raw.widder_compatibility),
        ZodiacSign("Taurus", R.drawable.stier,R.raw.stier_compatibility),
        ZodiacSign("Gemini", R.drawable.zwilling,R.raw.zwilling_compatibility),
        ZodiacSign("Cancer", R.drawable.krebs,R.raw.krebs_compatibility),
        ZodiacSign("Leo", R.drawable.loewe,R.raw.loewe_compatibility),
        ZodiacSign("Virgo", R.drawable.jungfrau,R.raw.jungfrau_compatibility),
        ZodiacSign("Libra", R.drawable.waage,R.raw.waage_compatibility),
        ZodiacSign("Scorpio", R.drawable.skorpion,R.raw.skorpion_compatibility),
        ZodiacSign("Sagittarius", R.drawable.schuetze,R.raw.schuetze_compatibility)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AureliaTheme {
                //Quelle[L2] for mutable structure
                val chosenSign = intent.getStringExtra("Zodiac")
                Log.d("Zodiac", "Description!!! $chosenSign")

                var count = 0
                var finalCount = 0

                zodiacSigns.forEach {
                    if(it.name == chosenSign){
                        finalCount = count
                    }
                    count++
                }

                var currentZodiacSign by remember { mutableStateOf(zodiacSigns[0]) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        currentZodiacSign=zodiacSignSwiper(modifier = Modifier.fillMaxWidth(), finalCount)
                        TapSwiper(currentZodiacSign)
                    }
                }
            }
        }
    }

    /**
     * The TapSwiper manages all the screens that are shown for each tab according to every zodiacSign which is chosen by the ZodiacSignSwiper.
     * One can decide between the description, the horoscope, the ascendant and the compatibility checker
     */
    //Quelle[L3] for swiper logic
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun TapSwiper(currentZodiacSign: ZodiacSign) {
        var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

        val tabs = listOf(
            "description", "horoscope", "ascendant", "compatibility checker"
        )

        val pagerState = rememberPagerState(initialPage = 0)

        //effect for switching between tabs
        LaunchedEffect(key1 = selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
        //effect for switching the content of the page below
        LaunchedEffect(key1 = pagerState.currentPage, key2 = pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress) {
                selectedTabIndex = pagerState.currentPage
            }
        }
        val backgroundImage: Painter = painterResource(R.drawable.backg)
        Box(modifier = Modifier.fillMaxSize()){
            Image( //ChatGPT als Hilfe
                painter = backgroundImage,
                contentDescription = null, // Set a suitable description
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
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
                                    fontFamily = aTypewriter,
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
                    when (page) { //display screen disposables according to tabs
                        0 -> DescriptionScreen(currentZodiacSign)
                        1 -> HoroscopeScreen(currentZodiacSign)
                        2 -> AscendantScreen(currentZodiacSign)
                        3 -> CompatibilityCheckerScreen(currentZodiacSign)
                    }
                }
            }
        }

    }

    /**
     * The DescriptionScreen simply displays an description for every zodiacSign in the first tab
     */
    @Composable
    private fun DescriptionScreen(currentZodiacSign: ZodiacSign) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState).padding(15.dp)) {
            Heading(currentZodiacSign.name)
            Spacer(Modifier.height(15.dp))
            val context = LocalContext.current
            FontText(getDescription(context,currentZodiacSign,R.raw.sign_descriptions))
        }
    }

}

