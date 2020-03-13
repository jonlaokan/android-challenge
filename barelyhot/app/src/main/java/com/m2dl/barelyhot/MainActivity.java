package com.m2dl.barelyhot;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private GameView gv;
    private SensorManager sm;
    private Float score = 0f;
    private TextView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv = findViewById(R.id.gameView);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accSensor,SensorManager.SENSOR_DELAY_UI);
        scoreView = findViewById(R.id.score);
        scoreView.setText("0");
        gv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv.freeze();
            }
        });
        gv.player.setLat(gv.getWidth()/2f);
        gv.player.setLng(gv.getHeight()/2f);
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
        if (sensor == Sensor.TYPE_ACCELEROMETER && !gv.isGameOver()) {
            Float newX = -event.values[0]*200 + gv.getWidth()/2f ;
            Float newY = event.values[1]*200 + gv.getHeight()/2f ;
            score += (-event.values[0]* -event.values[0] + event.values[1]*event.values[1])/(2<< 28);
            scoreView.setText(Float.toString(score));

            gv.player.setLat(newX);
            gv.player.setLng(newY);
            gv.player.setTime(event.timestamp);

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
