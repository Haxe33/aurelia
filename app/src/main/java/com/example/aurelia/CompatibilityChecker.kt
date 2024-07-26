package com.example.aurelia

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aurelia.logic.ZodiacSign
import com.example.aurelia.ui.theme.*

@Composable
fun CompatibilityCheckerScreen(currentZodiacSign: ZodiacSign) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .verticalScroll(scrollState)
        .padding(15.dp)) {
        Heading(currentZodiacSign.name)
        Spacer(Modifier.height(8.dp))
        //TODO add Text wie das Sternzeichen so in der Liebe ist
        Text("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.")
        Spacer(Modifier.height(8.dp))
        Heading("Choose your soulmate")
        Spacer(Modifier.height(6.dp))
        val compatibleSign=zodiacSignSwiper(modifier = Modifier.fillMaxWidth())
        Heading(compatibleSign.name)
        Spacer(Modifier.height(6.dp))



    }

}

//Quelle [L4]
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PinchReveal(imageRes: Int, modifier: Modifier = Modifier){
    Box( modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .combinedClickable(
            enabled = true,
            onLongClick = {
                //TODO change imagesource
            },
            onClick = {}
        )
    ){
      //TODO image
    }
}