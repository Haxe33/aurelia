package com.example.aurelia

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
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
            val onUsernameChange = { text: String ->
                username = text
            }
            var password by remember{ mutableStateOf("") }
            val onPasswordChange = { text: String ->
                password = text
            }
            var zodiac by remember { mutableStateOf("") }
            val onZodiacChange = { text: String ->
                zodiac = text
            }
            Heading("Welcome to Aurelia!")
            Spacer(Modifier.height(50.dp))
            Label("Username auswählen:")
            Typer(
                username,
                onUsernameChange,
                "Username eingeben",
            )
            Spacer(Modifier.height(50.dp))
            Label("Passwort auswählen:")
            Typer(
                password,
                onPasswordChange,
                "Passwort eingeben"
            )
            Spacer(Modifier.height(50.dp))
            Label("Choose Zodiac Sign:")
            ZodiacSelector()
            Spacer(Modifier.height(150.dp))
            FancyButton(
                "Registration",
                onClick = {
                    if(!checkUsername(context, username)){
                        Toast.makeText(context, "Username schon vergeben!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else{
                        handleRegistration(password, username, zodiac, context)
                        val intent = Intent(context, Description::class.java)
                        intent.putExtra("ZodiacSign", zodiac)
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun ZodiacSelector(){
    var exp by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf("Select an option:")
    }
    val zodiacs = listOf(
        Pair("Steinbock", R.drawable.steinbock),
        Pair("Wassermann", R.drawable.wassermann),
        Pair("Fische", R.drawable.fisch),
        Pair("Widder", R.drawable.widder),
        Pair("Stier", R.drawable.stier),
        Pair("Zwillinge", R.drawable.zwilling),
        Pair("Krebs", R.drawable.krebs),
        Pair("Löwe", R.drawable.loewe),
        Pair("Jungfrau", R.drawable.jungfrau),
        Pair("Waage", R.drawable.waage),
        Pair("Skorpion", R.drawable.skorpion),
        Pair("Schütze", R.drawable.schuetze)
    )
    Column {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { exp = !exp },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "arrowdown"
                )
            }
        )
        DropdownMenu(
            expanded = exp,
            onDismissRequest = { exp = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            zodiacs.forEach{(text, icon) ->
                DropdownMenuItem(
                    onClick = {
                        exp = false
                        selected = text
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = text,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(5.dp))
                        Label(text)
                    }
                }
            }

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