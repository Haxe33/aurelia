package com.example.aurelia.logic

import android.content.Context
import android.util.Log
import com.example.aurelia.model.User
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.io.OutputStreamWriter

fun readUserData(context:Context): List<User>{
    val f = File(context.filesDir, "users.txt")
    val reader = BufferedReader(FileReader(f))
    val users = mutableListOf<User>()
    while(reader.ready()){
        var l: String = reader.readLine()
        l = l.trim()
        Log.d("FileManagement", "Read: $l")
        val liste = l.split(",")
        users.add(User(liste[0], liste[1], liste[2]))
    }
    reader.close()
    return users
}

fun writeUserData(context: Context, str: String){
    val writer = OutputStreamWriter(context.openFileOutput("users.txt", Context.MODE_APPEND))
    writer.write(str)
    Log.d("FileManagement", "Wrote: $str")
    writer.close()
}

fun createFileIfNotExistant(context:Context){
    val f = File(context.filesDir, "users.txt")
    if(!f.exists()){
        try{
            f.createNewFile()
        }
        catch(e: IOException){
            Log.e("FileManagement", "Error creating file!")
        }

    }
}

