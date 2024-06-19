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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Heading(str: String){
    Text(
        str,
        fontSize = 28.sp,
        fontFamily = FontFamily.Monospace,
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
        fontFamily = FontFamily.Monospace,
        color = Color.White
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
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center
        )
    }

}