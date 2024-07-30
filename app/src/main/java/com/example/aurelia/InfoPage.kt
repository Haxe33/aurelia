package com.example.aurelia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aurelia.ui.theme.AureliaTheme
import com.example.aurelia.ui.theme.Heading

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AureliaTheme {
                InfoPage()
            }
        }
    }
}

@Preview
@Composable
fun InfoPage() {
    val context = LocalContext.current
    val infoText = context.resources.openRawResource(R.raw.infotext).bufferedReader().use { it.readText() }
    val gradientColors = listOf(Color.LightGray, Color.Gray, Color.Gray)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            Heading("Inforamtions")
            Spacer(Modifier.height(20.dp))
            Text(
                text = infoText,
                style = TextStyle(
                    brush = Brush.sweepGradient(
                        colors = gradientColors
                    ),
                )
            )
        }
    }
}
