package com.example.aurelia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aurelia.logic.createFileIfNotExistant
import com.example.aurelia.logic.getZodiac
import com.example.aurelia.logic.handleLogin
import com.example.aurelia.ui.theme.AureliaTheme
import com.example.aurelia.ui.theme.FancyButton
import com.example.aurelia.ui.theme.Heading
import com.example.aurelia.ui.theme.Label
import com.example.aurelia.ui.theme.Typer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createFileIfNotExistant(this)
        enableEdgeToEdge()
        setContent {
            AureliaTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Greeting(controller: NavController) {
    val context = LocalContext.current
    val backgroundImage: Painter = painterResource(R.drawable.background)
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = backgroundImage,
            contentDescription = null, // Set a suitable description
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()

        )
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
                onClick = {
                    if(handleLogin(password, username, context)){
                        val zodiac = getZodiac(context, username)
                        val intent = Intent(context, Description::class.java)
                        if(zodiac != ""){
                            intent.putExtra("Zodiac", zodiac)
                        }
                        context.startActivity(intent)
                    }
                        else{
                            Toast.makeText(
                                context,
                                "Username / Passwort war falsch!",
                                Toast.LENGTH_SHORT
                            ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(50.dp))
            FancyButton(
                "Registration",
                onClick = {
                    controller.navigate("registration")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    AureliaTheme {
        Greeting(rememberNavController())
    }
}