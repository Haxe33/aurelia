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

fun createFileIfNotExistent(context:Context){
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

/**
 * read the compatibilities from a zodiac sign with all the other from a txt file
 * @return compatibility as Int for later percentages
 */
fun getCompatibility(context: Context, oneSign:ZodiacSign, otherSign:ZodiacSign): Int{
    val inputStream = context.resources.openRawResource(oneSign.compatibilities)
    val compatibilities = inputStream.bufferedReader().use { it.readLines() }

    for(line in compatibilities){
        val sign=line.split(",")
        if(sign[0].equals(otherSign.name)){
            return sign[1].trim().toInt()
        }
    }
    return 0
}

/**
 * this methods reads a description string from a file where the name of the zodiac sign functions as a key
 * additionally the resource can be passed to reuse the method for different descriptions with the same scheme
 * @return description as string
 */
fun getDescription(context: Context,zodiacSign: ZodiacSign, resource: Int): String{
    val inputStream = context.resources.openRawResource(resource)
    val descriptions = inputStream.bufferedReader().use { it.readLines() }

    for(line in descriptions){
        val sign=line.split(";")
        if(sign[0].equals(zodiacSign.name)){
            return sign[1]
        }
    }
    return ""
}

