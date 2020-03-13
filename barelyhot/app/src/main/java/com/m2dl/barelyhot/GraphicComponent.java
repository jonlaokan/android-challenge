package com.m2dl.barelyhot;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

abstract class GraphicComponent {

    private final Resources res;
    protected final float scale;
    private float currentX;
    private float currentY;

    private float xOffset;
    private float yOffset;

    public float getCurrentX() {
        return currentX;
    }

    public void setCurrentX(float currentX) {
        this.currentX = currentX;
    }

    public float getCurrentY() {
        return currentY;
    }

    public void setCurrentY(float currentY) {
        this.currentY = currentY;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public GraphicComponent(Resources res, DisplayMetrics metrics, float x, float y) {
        this.res = res;
        scale = Utils.scale(metrics);
        currentX = x;
        currentY = y;
    }

    protected Bitmap initBitmap(Bitmap image, int resource, float width, float height) {
        if (image == null) {
            image = BitmapFactory.decodeResource(res, resource);
            image = Bitmap.createScaledBitmap(image, (int) (width * scale), (int) (height * scale), true);
        }
        return image;

    }

    protected Matrix matrixTranslateAndMove(float angle, float pivotX, float pivotY) {
        Matrix m = new Matrix();
        m.postTranslate(currentX, currentY);
        currentX = (int) pivotX;
        currentY = (int) pivotY;
        m.postRotate(angle, pivotX, pivotY);
        return m;
    }
}