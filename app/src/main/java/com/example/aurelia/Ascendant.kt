package com.example.aurelia

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aurelia.logic.ShakeDetector
import com.example.aurelia.logic.ZodiacSign
import com.example.aurelia.logic.getAscendent
import com.example.aurelia.ui.theme.*

/**
 * the AscendantScreen contains fields for entering the date, time and place of birth to calculate the ascendant which is later revealed
 */
@Composable
fun AscendantScreen(currentZodiacSign: ZodiacSign) {
    val scrollState = rememberScrollState() //all screens remember scrollState so that the tabs can be switched without scrolling back up
    var isAscendantVisible by remember{ mutableStateOf(false) }
    var isAscendantCalculated by remember { mutableStateOf(false) }
    var ascendant by remember{ mutableStateOf(zodiacSigns[0]) }

    val context = LocalContext.current

    //"register" shakeDetector
    val shakeDetector = remember {
        ShakeDetector(context) {
            isAscendantVisible = true
        }
    }
    //"unregister" shakeDetector
    DisposableEffect(Unit) {
        onDispose {
            shakeDetector.unregister()
        }
    }

    var dateOfBirth by remember { mutableStateOf("dd.mm.yyyy") }
    val onDateOfBirthChange = {text: String ->
        dateOfBirth = text
    }
    var timeOfBirth by remember{ mutableStateOf("hh:mm") }
    val onTimeOfBirthChange = {
            text: String -> timeOfBirth = text
    }
    var placeOfBirth by remember{ mutableStateOf("City,Country") }
    val onPlaceOfBirthChange = {
            text: String -> placeOfBirth = text
    }

    Column(modifier = Modifier
        .verticalScroll(scrollState)
        .padding(15.dp)) {
        Heading(currentZodiacSign.name)
        Spacer(Modifier.height(1.dp))
        FontText("An ascendent is the intersection of the eastern horizon with the ecliptic and indicates the degree of the zodiac rising on the eastern horizon at a given time and geographical location.")
        Spacer(Modifier.height(35.dp))
        Instructions(str = "Please enter your birth data to find your ascendant")
        Spacer(Modifier.height(8.dp))
        Typer(
            dateOfBirth,
            onDateOfBirthChange,
        "date of birth",
        )
        Typer(
            timeOfBirth,
            onTimeOfBirthChange,
            "time of birth",
        )
        Typer(
            placeOfBirth,
            onPlaceOfBirthChange,
            "place of birth",
        )
        Spacer(Modifier.height(10.dp))
        CalcButton(text = "calculate", onClick = {
            isAscendantVisible = false
            var ascen = 0
            if(checkInput(dateOfBirth, timeOfBirth, placeOfBirth)){
                ascen = getAscendent(dateOfBirth, timeOfBirth)
                Log.d("Ascendent", "Ascendent Index: $ascen")
                isAscendantCalculated = true
            }

            ascendant = zodiacSigns[ascen]
        })
        if(ascendantReveal(Modifier.height(240.dp), isAscendantVisible, isAscendantCalculated, ascendant)){
            isAscendantVisible=false
        }
        Instructions(str = "Shake your phone to reveal your ascendant after calculating")
    }
}

/**
 * This composable contains a method to switch between images after a shake. This is used to reveal the ascendant.
 */
@Composable
fun ascendantReveal(modifier: Modifier = Modifier, isAscendantVisible: Boolean, isAscendantCalculated: Boolean, ascendant: ZodiacSign): Boolean {
    var imageResource by rememberSaveable { mutableIntStateOf(R.drawable.ascendant) }

    //only show ascendant if it is calculated and phone is shaken
    if(isAscendantVisible && isAscendantCalculated){
        imageResource= ascendant.drawableRes
    }

    Column(
        modifier=Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .size(240.dp)
                .border(BorderStroke(4.dp, Color.White), CircleShape)
                .clip(CircleShape)
                .background(Color.Black)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "AscendantRevealer",
                modifier = modifier.fillMaxSize().padding(8.dp)
            )
        }
    }

    return isAscendantVisible
}


fun checkInput(date: String, time: String, place: String): Boolean{
    // Regex created with [P5]
    val dateRegex = Regex("([0-9]{2}\\.){2}[0-9]{4}")
    val timeRegex = Regex("[1-2][0-9]:[0-6][0-9]")
    val placeRegex = Regex("[A-Za-z\\s]+,[A-Za-z\\s]+")

    return dateRegex.matches(date) &&
           timeRegex.matches(time) &&
           placeRegex.matches(place)
}

