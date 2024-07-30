package com.example.aurelia

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
import androidx.compose.ui.unit.dp
import com.example.aurelia.logic.ShakeDetector
import com.example.aurelia.logic.ZodiacSign
import com.example.aurelia.ui.theme.*

@Composable
fun AscendantScreen(currentZodiacSign: ZodiacSign) {
    val scrollState = rememberScrollState()
    var isAscendantVisible by remember{ mutableStateOf(false) }
    var isAscendantCalculated by remember { mutableStateOf(false) }
    var ascendant by remember{ mutableStateOf(zodiacSigns[0]) }

    val context = LocalContext.current

    val shakeDetector = remember {
        ShakeDetector(context) {
            isAscendantVisible = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            shakeDetector.unregister()
        }
    }

    var dateOfBirth by remember { mutableStateOf("") }
    val onDateOfBirthChange = {text: String ->
        dateOfBirth = text
    }
    var timeOfBirth by remember{ mutableStateOf("") }
    val onTimeOfBirthChange = {
            text: String -> timeOfBirth = text
    }
    var placeOfBirth by remember{ mutableStateOf("") }
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
            //TODO wenn Eingaben gültig => @Paul
            isAscendantCalculated = true
            ascendant= zodiacSigns[6] //TODO retourniere ZodiacSign
        })
        if(ascendantReveal(Modifier.height(240.dp), isAscendantVisible, isAscendantCalculated, ascendant)){
            isAscendantVisible=false
        }
        Instructions(str = "Shake your phone to reveal your ascendant after calculating")
    }
}

@Composable
fun ascendantReveal(modifier: Modifier = Modifier, isAscendantVisible: Boolean, isAscendantCalculated: Boolean, ascendant: ZodiacSign): Boolean {
    var imageResource by rememberSaveable { mutableIntStateOf(R.drawable.ascendant) }

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


