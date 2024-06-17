package com.example.aurelia.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun Typer(str: String, onTextChange: (String) -> Unit){
    OutlinedTextField(
        str,
        onTextChange,

    )
}