package com.example.aurelia

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.sharp.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aurelia.logic.checkUsername
import com.example.aurelia.logic.handleRegistration
import com.example.aurelia.ui.theme.AureliaTheme
import com.example.aurelia.ui.theme.FancyButton
import com.example.aurelia.ui.theme.Heading
import com.example.aurelia.ui.theme.Label
import com.example.aurelia.ui.theme.Typer

@Composable
fun Registration(){
    val context = LocalContext.current
    val backgroundImage: Painter = painterResource(R.drawable.backg)
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image( //ChatGPT als Hilfe
            painter = backgroundImage,
            contentDescription = null, // Set a suitable description
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column {
            var username by remember { mutableStateOf("") }
            val onUsernameChange = { text: String ->
                username = text
            }
            var password by remember{ mutableStateOf("") }
            val onPasswordChange = { text: String ->
                password = text
            }
            var zodiac by remember { mutableStateOf("Select an option:") }
            var expanded by remember {
                mutableStateOf(false)
            }
            Heading("Welcome to Aurelia!")
            Spacer(Modifier.height(50.dp))
            Label("Choose username:")
            Typer(
                username,
                onUsernameChange,
                "Enter username",
            )
            Spacer(Modifier.height(50.dp))
            Label("Choose password:")
            Typer(
                password,
                onPasswordChange,
                "Enter password"
            )
            Spacer(Modifier.height(50.dp))
            Label("Choose Zodiac Sign:")
            val zodiacs = listOf(
                "Capricorn",
                "Aquarius",
                "Pisces",
                "Aries",
                "Taurus",
                "Gemini",
                "Cancer",
                "Leo",
                "Virgo",
                "Libra",
                "Scorpio",
                "Sagittarius"
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)) {
                OutlinedTextField(
                    value = zodiac,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Zodiac Sign") },
                    trailingIcon = {
                        Icon(Icons.Sharp.ArrowDropDown, "contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    zodiacs.forEach{
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                zodiac = it
                                expanded = false
                            },
                            leadingIcon = { Icon(Icons.Default.PlayArrow, "text")}
                        )
                    }
                }
            }
            Spacer(Modifier.height(150.dp))
            FancyButton(
                "Registration",
                onClick = {
                    if(!checkUsername(context, username)){
                        Toast.makeText(context, "Username already in use!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else{
                        handleRegistration(password, username, zodiac, context)
                        val intent = Intent(context, Description::class.java)
                        intent.putExtra("Zodiac", zodiac)
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun RegistrationPreview(){
    AureliaTheme {
        Registration()
    }
}