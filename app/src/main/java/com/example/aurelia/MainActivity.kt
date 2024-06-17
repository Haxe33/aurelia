package com.example.aurelia

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aurelia.logic.checkPassword
import com.example.aurelia.logic.handleLogin
import com.example.aurelia.logic.handleRegistration
import com.example.aurelia.ui.theme.AureliaTheme
import com.example.aurelia.ui.theme.FancyButton
import com.example.aurelia.ui.theme.Heading
import com.example.aurelia.ui.theme.Label
import com.example.aurelia.ui.theme.Typer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AureliaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier) {
    Column {
        var username by remember { mutableStateOf("") }
        val onUsernameChange = {text: String ->
            username = text
        }
        var password by remember{ mutableStateOf("") }
        val onPasswordChange = {
            text: String -> password = text
        }
        Heading("Welcome to Aurelia!")
        Spacer(Modifier.height(50.dp))
        Label("Username:")
        Typer(
            username,
            onUsernameChange,
            "Username eingeben",
        )
        Spacer(Modifier.height(50.dp))
        Label("Passwort:")
        Typer(
            password,
            onPasswordChange,
            "Passwort eingeben"
        )
        Spacer(Modifier.height(50.dp))
        FancyButton(
            "Login",
            onClick = { handleLogin(password, username) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(50.dp))
        FancyButton(
            "Registration",
            onClick = { handleRegistration(password, username) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    AureliaTheme {
        Greeting(Modifier.padding(vertical = 15.dp))
    }
}