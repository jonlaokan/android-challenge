package com.m2dl.barelyhot;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private GameView gv;
    private SensorManager sm;
    private float[] accValues = {0,0};
    private Float vitesse = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv = findViewById(R.id.gameView);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accSensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onResume() {
        super.onResume();
        Sensor accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onStop() {
        sm.unregisterListener(this, sm.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER
        ));
        super.onStop();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensor = event.sensor.getType();
        if (sensor == Sensor.TYPE_ACCELEROMETER /*&& event.values[0]>0.001 && event.values[1]>0.001*/) {


            Float newX = -event.values[0]*200 + gv.getWidth()/2 ;
            Float newY = event.values[1]*200 + gv.getHeight()/2 ;
            vitesse += (newX*newX + newY*newY)*100;
            Log.i("s", "x "+accValues+"y "+accValues[1]+ vitesse + "lol");
            gv.moveImage(0, newX, newY);
            gv.invalidate();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void movePlayer(View view) {
        gv.move(view);
    }
}
