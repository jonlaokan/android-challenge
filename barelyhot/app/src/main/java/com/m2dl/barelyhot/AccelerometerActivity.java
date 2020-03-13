package com.m2dl.barelyhot;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sm;
    private float[] accValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor gyrSensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sm.registerListener(this, accSensor,SensorManager.SENSOR_DELAY_UI);
        sm.registerListener(this, gyrSensor,SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onResume() {
        super.onResume();
        Sensor accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magnSensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
        Sensor gyrSensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sm.registerListener(this, gyrSensor,SensorManager.SENSOR_DELAY_UI);
        sm.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_UI);
        sm.registerListener(this, magnSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onStop() {
        sm.unregisterListener(this, sm.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER
        ));
        sm.unregisterListener(this, sm.getDefaultSensor(
                Sensor.TYPE_GYROSCOPE
        ));
        super.onStop();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensor = event.sensor.getType();
        Log.i("f", "ok1///////");
        if (sensor == Sensor.TYPE_ACCELEROMETER) {
            Log.i("f", "ok///////");
            accValues = event.values;
            ImageView imageView = findViewById(R.id.person);
            imageView.setX(-accValues[0]*accValues[0]*accValues[0]*100);
            imageView.setY(accValues[1]*accValues[1]* accValues[1]*100);
            imageView.invalidate();
        }
        /*if(sensor == Sensor.TYPE_GYROSCOPE){
            accValues[0] = event.values[0];
            accValues[1] = event.values[1];
            accValues[2] = event.values[2];
            ImageView imageView = findViewById(R.id.person);
            imageView.setX(accValues[0]);
            imageView.setX(accValues[1]);
            //imageView.invalidate();
        }*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
