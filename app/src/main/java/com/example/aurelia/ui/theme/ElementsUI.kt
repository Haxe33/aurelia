package com.example.aurelia.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aurelia.R


@Composable
fun Heading(str: String){
    Text(
        str,
        fontSize = 28.sp,
        fontFamily = aTypewriter,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = Modifier.padding(vertical = 15.dp)
    )
}
@Composable
fun Label(str: String){
    Text(
        str,
        fontSize = 16.sp,
        fontFamily = aTypewriter,
        color = Color.White
    )
}

/**
 * small textfield with accent color for instructions for sensors and gestures
 */
@Composable
fun Instructions(str: String){
    Text(
        str,
        fontSize = 16.sp,
        fontFamily = aTypewriter,
        fontWeight = FontWeight.Bold,
        color = Purple80,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Typer(str: String, onTextChange: (String) -> Unit, label: String){
    OutlinedTextField(
        str,
        onTextChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun FancyButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier){
    Box(
        modifier = modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(Color.Black, shape = AbsoluteCutCornerShape(5.dp))
                    .border(1.dp, Color.Gray)
                    .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ){
        Text(
            text,
            color = Purple80,
            fontFamily = aTypewriter,
            textAlign = TextAlign.Center
        )
    }

}

/**
 * simple Button in a Box matched to the Typers
 */
@Composable
fun CalcButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(Color.Transparent, shape = AbsoluteCutCornerShape(5.dp))
            .border(1.dp, Color.Gray)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ){
        Text(
            text,
            color = Color.White,
            fontFamily = aTypewriter,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Composable for a simple Text with a customfont
 */
@Composable
fun FontText(str: String) {
    Text(
        text = str,
        fontSize = 16.sp,
        fontFamily = aTypewriter,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        textAlign = TextAlign.Left
    )
}

/**
 * aTypewriter is a fontstyle defined by a .ttf file
 */
val aTypewriter = FontFamily(
    Font(R.font.ame, FontWeight.ExtraBold)
)
