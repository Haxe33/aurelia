package com.example.aurelia.model

class User(private var username:String, private var password:String) {
    fun getUser(): String{
        return username
    }
    fun getPassword(): String{
        return password
    }
}