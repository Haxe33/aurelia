package com.example.aurelia.logic

import com.example.aurelia.model.User

fun readUserData(): List<User>{
    return listOf(
        User("Tester1", "test"),
        User("Tester2", "test2")
    )
}