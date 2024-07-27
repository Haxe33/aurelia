package com.example.aurelia.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.aurelia.R
import com.example.aurelia.logic.ZodiacSign
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

val zodiacSigns= listOf(
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

//Quelle[L1]
@OptIn(ExperimentalPagerApi::class)
@Composable
fun zodiacSignSwiper(modifier: Modifier): ZodiacSign {
    val pagerState = rememberPagerState(initialPage = 0)
    var currentZodiacSign by remember{ mutableStateOf(zodiacSigns[0]) }

    LaunchedEffect(pagerState.currentPage) {
        currentZodiacSign = zodiacSigns[pagerState.currentPage]
    }

    Column {
        HorizontalPager(
            count = zodiacSigns.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 3.dp),
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
                    painter = painterResource(id = zodiacSigns[page].drawableRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
    return currentZodiacSign
}