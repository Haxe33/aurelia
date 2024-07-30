package com.example.aurelia.model


/**
 * DAO for representing a user app internal
 * @param username Username of the current User
 * @param password Password of the current User
 * @param zodiacSign Zodiac Sign of the current User
 */
class User(private var username:String, private var password:String, private var zodiacSign: String) {
    /**
     * @return the username as a String
     */
    fun getUser(): String{
        return username
    }

    /**
     * @return the password as a String
     */
    fun getPassword(): String{
        return password
    }

    /**
     * @return the Zodiac sign as a String
     */
    fun getZodiacSign(): String{
        return zodiacSign
    }
}