package com.example.aurelia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
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
           ZodiacSignSwiper(
           )
        }
    }
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun ZodiacSignSwiper(){
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
                    .height(114.dp)
                    .fillMaxWidth()
            ) { page ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
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

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MaterialTheme {
            ZodiacSignSwiper()
        }
    }
}
