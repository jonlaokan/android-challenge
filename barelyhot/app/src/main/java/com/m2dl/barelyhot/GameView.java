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
    private Matrix barrelMatrix;
    private Matrix barrel2Matrix;

    private DisplayMetrics currentDisplay;

    private Bitmap playerBitmap;
    private Bitmap barrelBitmap;
    private Bitmap barrel2Bitmap;
    public Player player;
    private Barrel barrel;
    private Barrel barrel2;

    Random randomGenerator = new Random();
    
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setFocusable(true); // Get key events
        setFocusableInTouchMode(true); // Get Touch events
        currentDisplay = context.getResources().getDisplayMetrics();
        player =  new Player(getResources(), currentDisplay, 400, 400);
        matrix = player.matrixTranslateAndMove(0, 400.0f, 400.0f);
        playerBitmap = player.initBitmap(playerBitmap,R.drawable.supergranny, 400, 400);

        barrel = new Barrel(getResources(), currentDisplay, 200, 200, 0, 90, this);
        barrelBitmap = barrel.initBitmap(barrelBitmap, R.drawable.plane, 300, 300);

        barrel2 = new Barrel(getResources(), currentDisplay, 400, 400, 1, -90, this);
        barrel2Bitmap = barrel2.initBitmap(barrelBitmap, R.drawable.plane, 400, 400);
    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawBitmap(playerBitmap, matrix, paint);
        barrel.moveBarrel(player.getDeltaSpeed());
        canvas.drawBitmap(barrelBitmap, barrel.getMatrixPos(), paint);

        barrel2.moveBarrel(player.getDeltaSpeed());
        canvas.drawBitmap(barrel2Bitmap, barrel2.getMatrixPos(), paint);


        //canvas.drawBitmap(barrel2Bitmap, barrel2Matrix, paint);
        System.out.println(barrel.getMatrixPos().toString());
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
