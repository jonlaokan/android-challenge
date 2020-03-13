package com.m2dl.barelyhot;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

public class Player extends GraphicComponent {
    private float previous_lat = 0f;
    private float previous_long = 0f;
    private long previous_time =0;


    private float lat = 0f;


    private float lng = 0f;

    private long time =0;


    Player(Resources res, DisplayMetrics displayMetrics, float x, float y) {
        super(res, displayMetrics, x, y);
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        previous_long = this.lng;
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.previous_lat = this.lat;
        this.lat = lat;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.previous_time = this.time;
        this.time = time;
    }

    public float getDeltaSpeed(){
       // Log.i("speed=", ( (Math.abs(previous_lat-lat)+Math.abs(previous_long-lng))/ Math.abs(previous_time-time) )+"");
        return (Math.abs(previous_lat-lat)+Math.abs(previous_long-lng))/ Math.abs(previous_time-time);
    }

}
