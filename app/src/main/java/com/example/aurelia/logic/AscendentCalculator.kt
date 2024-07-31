package com.example.aurelia.logic

import android.util.Log
import kotlin.math.*

// [P2], [P3], [P4] in "res/used resources.txt"

// The calculation for the ascendent is not completely correct
// It is merely an easier, for the purpose of this project better suited version, which probably
// gives completely different results than completely calculating it through
// For example the geographic longitude east of Greenwich is always 9 (Austria is on this longitude)
// Also the Calculation of the GMST and furthermore the LMST is only a approximation, since gmst is
// calculated with the time unit seconds and i am not quite sure if there needs to be another
// unit transformation in order to get it definitely right
// Furthermore the lookup of the Ascendent is only done via a easy modulo 12 calculation instead of
// looking it up in a table


/**
 * This method calculates a simulated ascendent
 * @param date Date of birth
 * @param time Time of birth
 * @return an Integer between 1 and 12 corresponding to one of the 12 zodiac signs
 */
fun getAscendent(date: String, time: String): Int{
    Log.d("Ascendent", "getAscendent")
    val epsilon = 23.4392583
    val st = getStartime(date, time)
    Log.d("Ascendent", "Startime calculated")
    var result = (-cos(st))/(sin(st) * cos(epsilon) + tan(9f) * sin(epsilon))
//    result %= 180
    if(result < 0){
        result = -result
    }
    Log.d("Ascendent", "result: " + result.toInt()%12)
    return result.toInt() % 12
}

/**
 * This method is ought to get the Startime of the birth
 * @param date Date of Birth
 * @param time Time of Birth
 * @return The Startime of a birthday
 */
fun getStartime(date: String, time: String): Double{
    Log.d("Ascendent", "getStartime")
    return gmst(
        getJulianicDate(
            date.split(".")[2].toInt(),
            date.split(".")[1].toInt(),
            date.split(".")[0].toInt(),
            time.split(":")[0].toInt(),
            time.split(":")[1].toInt()
        )
    ) + 9/15
}


/**
 * This method calculates the Julianic Date in respective to the birth date and time of the user
 * @param year birthyear
 * @param month birthmonth
 * @param day birthday
 * @param hour hour of birthtime
 * @param minute minute of birthtime
 * @return the Julianic date
 */
fun getJulianicDate(year: Int, month: Int, day: Int, hour: Int, minute: Int): Double{
    Log.d("Ascendent", "getJD")
    val y = if(month > 2){
        year.toFloat()
    }
    else{
        (year-1).toFloat()
    }
    val m = if(month > 2){
        month.toFloat()
    }
    else{
        (month + 12).toFloat()
    }
    val d = (day + (hour.toFloat()/24) + minute.toFloat()/1440)
    val b = 2 - (y/100) + (y/400)
    return (365.25 * (y+4176)) + (30.6001 * (m+1)) + b + d - 1524.5
}

/**
 * Calculates the Greenwich Mean Sidereal Time of a given Julianic date
 * @param jd Julianic Date for which gmst should be calculated
 * @return the GMST of a given JD
 */
fun gmst(jd: Double): Double{
    val t = (floor(jd)+0.5 - 2451545.0) / 36525
    return 24110.54841 + 8640184.812866 * t + 0.093104 * t * t - 0.0000062 * t * t * t
}

