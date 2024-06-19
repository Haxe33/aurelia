package com.example.aurelia.logic

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.aurelia.MainActivity



fun handleLogin(pwd:String, user: String): Boolean{
    return checkPassword(pwd, user)
}

fun handleRegistration(pwd: String, user: String){

}
fun checkPassword(pwd: String, username: String): Boolean{
    val users = readUserData()
    for(user in users){
        if(user.getUser() == username){
            if(user.getPassword() == pwd){
                return true
            }
        }
    }
    return false
}