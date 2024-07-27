package com.example.aurelia

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aurelia.logic.ZodiacSign
import com.example.aurelia.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun CompatibilityCheckerScreen(currentZodiacSign: ZodiacSign) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(modifier = Modifier
        .verticalScroll(scrollState)
        .padding(15.dp))
    {
        Heading(currentZodiacSign.name)
        Spacer(Modifier.height(8.dp))
        Text(getDescription(context,currentZodiacSign))
        Spacer(Modifier.height(8.dp))
        Heading("Choose your soulmate")
        Spacer(Modifier.height(6.dp))
        val compatibleSign=zodiacSignSwiper(modifier = Modifier.fillMaxWidth())
        Heading(compatibleSign.name)
        Spacer(Modifier.height(8.dp))
        Text(getDescription(context,compatibleSign))
        Spacer(Modifier.height(8.dp))
        CompatibilityReveal(modifier = Modifier.size(240.dp),currentZodiacSign,compatibleSign)
        Instructions(str = "Press long on the heart to reveal your soulmate!")
    }

}

//Quelle [L4] for gesture handling
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompatibilityReveal(modifier: Modifier = Modifier,currentZodiacSign: ZodiacSign, compatibleSign: ZodiacSign) {
    var isVisible by remember { mutableStateOf(true) }
    var progress by remember { mutableStateOf(0.0f) }
    val context = LocalContext.current
    val targetProgress= getCompatibility(context,currentZodiacSign,compatibleSign)/100.0f

    LaunchedEffect(currentZodiacSign, compatibleSign) {
        isVisible = true
        progress = 0.0f
    }

    Column(
        modifier=Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp)
                .combinedClickable(
                    enabled = true,
                    onLongClick = {
                        isVisible = !isVisible
                    },
                    onClick = {}
                ),
            contentAlignment = Alignment.Center
        ) {
            if(isVisible) {
                Image(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }else{
                LaunchedEffect(isVisible) {
                    progress=0.0f
                    while(progress<targetProgress){
                        progress += 0.01f
                        delay(10)
                    }
                    progress=targetProgress
                }
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(190.dp),
                    color = Purple80,
                    strokeWidth = 15.dp,
                )
                Text(
                    text = "${(progress * 100).toInt()}% match",
                    color = Purple80,
                    fontSize = 27.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

private fun getCompatibility(context: Context, oneSign:ZodiacSign, otherSign:ZodiacSign): Int{
    val inputStream = context.resources.openRawResource(oneSign.compatibilities)
    val compatibilities = inputStream.bufferedReader().use { it.readLines() }

    for(line in compatibilities){
        val sign=line.split(",")
        if(sign[0].equals(otherSign.name)){
            return sign[1].trim().toInt()
        }
    }
    return 0
}

private fun getDescription(context: Context,zodiacSign: ZodiacSign): String{
    val inputStream = context.resources.openRawResource(R.raw.love_descriptions)
    val descriptions = inputStream.bufferedReader().use { it.readLines() }

    for(line in descriptions){
        val sign=line.split(";")
        if(sign[0].equals(zodiacSign.name)){
            return sign[1]
        }
    }
    return ""
}

