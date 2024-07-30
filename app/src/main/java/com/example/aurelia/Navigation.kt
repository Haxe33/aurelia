package com.example.aurelia

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = "login"){
        composable("login"){ Greeting(controller) }
        composable("registration"){Registration()}
        composable("description"){}
        composable("info"){ InfoPage()}
    }
}