package com.m2dl.barelyhot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class GameView extends View {

    private boolean hasActiveHolder=false;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float x, y;
    private boolean touching;

    private DisplayMetrics currentDisplay;

    private Bitmap playerBitmap;
    private Player player;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setFocusable(true); // Get key events
        setFocusableInTouchMode(true); // Get Touch events
        currentDisplay = context.getResources().getDisplayMetrics();
        player =  new Player(getResources(), currentDisplay);
    }

    @Override
    public void onDraw(Canvas canvas) {
        playerBitmap = player.initBitmap(playerBitmap,R.drawable.cut_the_rope, 400, 400);

        canvas.drawBitmap(playerBitmap,200,200, paint);
    }

}
