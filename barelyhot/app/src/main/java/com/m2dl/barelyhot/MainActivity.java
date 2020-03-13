package com.m2dl.barelyhot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private GameView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv = findViewById(R.id.gameView);

    }

    public void movePlayer(View view) {
        gv.move(view);
    }
}
