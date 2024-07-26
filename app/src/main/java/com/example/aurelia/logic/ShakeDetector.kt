package com.example.aurelia.logic

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.*

//Quelle [L5] for one time triggered sensor

class ShakeDetector(context: Context, private val onShake: () -> Unit): SensorEventListener {
    private val sensorManager: SensorManager=context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscope: Sensor? =sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val NS2S= 1.0f/ 1000000000.0f
    private val deltaRotationVector = FloatArray(4) { 0f }
    private var timestamp: Float = 0f
    private val SHAKE_THRESHOLD = 1.0f

    init{
        sensorManager.registerListener(this,gyroscope,SensorManager.SENSOR_DELAY_UI)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(timestamp != 0f && event!= null){
            val dT = (event.timestamp-timestamp)*NS2S
            var xAxis: Float= event.values[0]
            var yAxis: Float= event.values[1]
            var zAxis: Float= event.values[2]

            val omegaMagnitude: Float= sqrt(xAxis*xAxis + yAxis*yAxis + zAxis*zAxis)

            if(omegaMagnitude > SHAKE_THRESHOLD){
                onShake()
            }

            if(omegaMagnitude > 0.01f){ //normalizing rotation
                xAxis /= omegaMagnitude
                yAxis /= omegaMagnitude
                zAxis /= omegaMagnitude
            }

            val thetaOverTwo: Float = omegaMagnitude * dT / 2.0f
            val sinThetaOverTwo: Float = sin(thetaOverTwo)
            val cosThetaOverTwo: Float = cos(thetaOverTwo)
            deltaRotationVector[0] = sinThetaOverTwo * xAxis
            deltaRotationVector[1] = sinThetaOverTwo * yAxis
            deltaRotationVector[2] = sinThetaOverTwo * zAxis
            deltaRotationVector[3] = cosThetaOverTwo
        }
        timestamp = event?.timestamp?.toFloat() ?: 0f
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // does not have to implemented
    }

    fun unregister(){
        sensorManager.unregisterListener(this)
    }

}