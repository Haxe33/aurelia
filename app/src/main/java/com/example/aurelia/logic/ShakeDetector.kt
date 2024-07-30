package com.example.aurelia.logic

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.*

/**
 * sensor with gyroscope to detect a shake
 */

//Quelle [L5] for one time triggered sensor
class ShakeDetector(context: Context, private val onShake: () -> Unit): SensorEventListener {
    private val sensorManager: SensorManager=context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscope: Sensor? =sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private var timestamp: Float = 0f
    private val SHAKE_THRESHOLD = 1.0f

    init{
        sensorManager.registerListener(this,gyroscope,SensorManager.SENSOR_DELAY_UI)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(timestamp != 0f && event!= null){
            //rotation speed
            val xAxis: Float= event.values[0]
            val yAxis: Float= event.values[1]
            val zAxis: Float= event.values[2]

            //rotation angle
            val omegaMagnitude: Float= sqrt(xAxis*xAxis + yAxis*yAxis + zAxis*zAxis)

            //trigger onShake if magnitude is stronger than a certain sensitivity
            if(omegaMagnitude > SHAKE_THRESHOLD){
                onShake()
            }

        }
        timestamp = event?.timestamp?.toFloat() ?: 0f //update timestamp
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // does not have to implemented
    }

    fun unregister(){
        sensorManager.unregisterListener(this)
    }

}