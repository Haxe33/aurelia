package com.example.aurelia.logic

import android.content.Context
import android.util.Log


/**
 * Calls the checkPassword() method
 * @param pwd Password
 * @param user Username
 * @param context Application / Activity context
 * @return True if and only if username-password pair is valid
 */
fun handleLogin(pwd:String, user: String, context: Context): Boolean{
    return checkPassword(pwd, user, context)
}

/**
 * Writes the data passed upon registration to the user.txt
 * @param pwd Password chosen upon registration
 * @param user Username chosen upon registration
 * @param zodiac Zodiac sign chosen upon registration
 * @param context Application / Activity context
 */
fun handleRegistration(pwd: String, user: String, zodiac: String, context: Context){
    val str = "$user,$pwd,$zodiac\n"
    writeUserData(context, str)
}


/**
 * Checks if the Username already exists
 * @param context Application / Activity context
 * @param user Username
 * @return False if the username already exists
 */
fun checkUsername(context: Context, user: String): Boolean{
    val users = readUserData(context)
    users.forEach {
        if(it.getUser() == user){
            return false
        }
    }
    return true
}


/**
 * This method is used to read out the Zodiac Sign a user chose upon Registration
 * @param context Application / Activity context
 * @param user Username
 * @return The Zodiac Sign of the current User or an empty string
 */
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


/**
 * Checks if the password is valid for the given username
 * @param pwd Password
 * @param username Username
 * @param context Application / Activity context
 * @return True if and only if the password is valid for the username
 */
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