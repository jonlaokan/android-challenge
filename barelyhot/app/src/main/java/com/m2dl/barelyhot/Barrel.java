package com.m2dl.barelyhot;

import android.content.res.Resources;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

public class Barrel extends GraphicComponent {

    private final int DIAG_RIGHT_DOWN = 0;
    private final int DIAG_RIGHT_TOP = 1;
    //private final int DIAG_RIGHT_DOWN = ;
    //private final int DIAG_RIGHT_DOWN = 0;
    private float offset;
    private int direction;
    private GameView gv;


    private Matrix matrixPos;


    Barrel(Resources res, DisplayMetrics displayMetrics, float x, float y, int direction, Matrix matrixPos, GameView gv) {
        super(res, displayMetrics, x, y);
        this.direction = direction;
        this.gv = gv;
        this.matrixPos = matrixPos;
    }

    public Matrix move() {
        switch(direction) {
            case DIAG_RIGHT_DOWN:
                offset = 5;
                break;
            case DIAG_RIGHT_TOP:
                offset = -5;
                break;
            default:
                // nothing
                break;
        }
        return update(offset);
    }

    public Matrix getMatrixPos() {
        return matrixPos;
    }

    public void setMatrixPos(Matrix matrixPos) {
        this.matrixPos = matrixPos;
    }

    protected Matrix update(float offset) {
        return matrixTranslateAndMove(0, getCurrentX() + offset, getCurrentY() + offset);
    }

    private void setGameView(GameView gv) {
        this.gv = gv;
    }

    protected  void startThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        sleep(50);
                        matrixPos = move();
                        gv.invalidate();
                        if (currentX > gv.getWidth() || currentY > gv.getHeight()) {
                            matrixPos.reset();
                            currentX = 0;
                            currentY = 0;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }



}