package com.m2dl.barelyhot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Random;

public class GameView extends View {

    private boolean hasActiveHolder=false;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float x, y;
    private boolean touching;
    private Matrix matrix;

    private DisplayMetrics currentDisplay;

    private Bitmap playerBitmap;
    private Player player;

    Random randomGenerator = new Random();
    
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setFocusable(true); // Get key events
        setFocusableInTouchMode(true); // Get Touch events
        currentDisplay = context.getResources().getDisplayMetrics();
        player =  new Player(getResources(), currentDisplay, 400, 400);
        matrix = player.matrixTranslateAndMove(0, 400.0f, 400.0f);
    }

    @Override
    public void onDraw(Canvas canvas) {
        playerBitmap = player.initBitmap(playerBitmap,R.drawable.cut_the_rope, 400, 400);


        canvas.drawBitmap(playerBitmap, matrix, paint);
    }

    public void move(View view) {
        matrix = player.matrixTranslateAndMove(0,
                randomGenerator.nextInt(800) + 1, randomGenerator.nextInt(800) + 1);

        System.out.println("matrix changed");
        invalidate();
    }

    public void moveImage(int angle, float newX, float newY){
        matrix = player.matrixTranslateAndMove(angle,
                newX, newY);

        System.out.println("matrix changed");
        invalidate();

    }

}
