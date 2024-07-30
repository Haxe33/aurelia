package com.example.aurelia

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aurelia.logic.ZodiacSign
import com.example.aurelia.ui.theme.FontText
import com.example.aurelia.ui.theme.Heading
import java.io.BufferedReader
import kotlin.random.Random
import android.icu.util.Calendar
import androidx.compose.ui.platform.LocalContext

@Composable
fun HoroscopeScreen(currentZodiacSign: ZodiacSign) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState).padding(15.dp)) {
        Heading(currentZodiacSign.name)
        Spacer(Modifier.height(15.dp))
        FontText(
            getHoroscope(currentZodiacSign)
        )
    }
}

@Composable
fun getHoroscope(zodiacSign: ZodiacSign): String {
    val resources = LocalContext.current.resources
    val horoscopeTexts = when (zodiacSign.name) {
        "Aries" -> listOf(R.raw.aries1, R.raw.aries2, R.raw.aries3)
        "Taurus" -> listOf(R.raw.taurus1, R.raw.taurus2, R.raw.taurus3)
        "Gemini" -> listOf(R.raw.gemini1, R.raw.gemini2, R.raw.gemini3)
        "Cancer" -> listOf(R.raw.cancer1, R.raw.cancer2, R.raw.cancer3)
        "Leo" -> listOf(R.raw.leo1, R.raw.leo2, R.raw.leo3)
        "Virgo" -> listOf(R.raw.virgo1, R.raw.virgo2, R.raw.virgo3)
        "Libra" -> listOf(R.raw.libra1, R.raw.libra2, R.raw.libra3)
        "Scorpio" -> listOf(R.raw.scorpio1, R.raw.scorpio2, R.raw.scorpio3)
        "Sagittarius" -> listOf(R.raw.sagittarius1, R.raw.sagittarius2, R.raw.sagittarius3)
        "Capricorn" -> listOf(R.raw.capricorn1, R.raw.capricorn2, R.raw.capricorn3)
        "Aquarius" -> listOf(R.raw.aquarius1, R.raw.aquarius2, R.raw.aquarius3)
        "Pisces" -> listOf(R.raw.pisces1, R.raw.pisces2, R.raw.pisces3)
        else -> listOf("Horoscope not available.")
    }

    val calendar = Calendar.getInstance()
    val randomSeed = calendar.timeInMillis / 86400000 // Convert milliseconds to days
    val randomGenerator = Random(randomSeed)
    val randomIndex = randomGenerator.nextInt(horoscopeTexts.size) % 3 // 3 horoscopes per zodiac sign

    val inputStream = resources.openRawResource(horoscopeTexts[randomIndex] as Int)

    return inputStream.bufferedReader().use(BufferedReader::readText)
}