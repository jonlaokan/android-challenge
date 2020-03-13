package com.m2dl.barelyhot;

public class GameData {

    private int barrelImpact;
    private float speed;

    public int getBarrelImpact() {
        return barrelImpact;
    }

    public void setBarrelImpact(int barrelImpact) {
        this.barrelImpact = barrelImpact;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isImpacts(Player player, Barrel barrel) {
        boolean impact = false;
        float diffX=Math.abs(player.currentX-barrel.currentX);
        float diffY=Math.abs(player.currentY-barrel.currentY);
        if (diffX<199 && diffY<180* player.scale){
            impact = true;
        }
        else impact = false;
        return impact;
    }
}
