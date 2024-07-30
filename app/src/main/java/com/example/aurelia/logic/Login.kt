package com.example.aurelia.logic

import android.content.Context
import android.util.Log

fun handleLogin(pwd:String, user: String, context: Context): Boolean{
    return checkPassword(pwd, user, context)
}

fun handleRegistration(pwd: String, user: String, zodiac: String, context: Context){
    val str = "$user,$pwd,$zodiac\n"
    writeUserData(context, str)
}

fun checkUsername(context: Context, user: String): Boolean{
    val users = readUserData(context)
    users.forEach {
        if(it.getUser() == user){
            return false
        }
    }
    return true
}

fun getZodiac(context: Context, user: String): String{
    val users = readUserData(context)
    users.forEach {
        if(it.getUser() == user){
            Log.d("Zodiac", it.getZodiacSign())
            return it.getZodiacSign()
        }
    }
    return ""
}

fun checkPassword(pwd: String, username: String, context: Context): Boolean{
    val users = readUserData(context)
    users.forEach{
        if(it.getUser() == username){
            if(it.getPassword() == pwd){
                return true
            }
        }
    }
    return false
}