package com.enkey.logiccircuit.utils;

/**
 * Created by MOmac on 23.09.2017.
 */
public class PointFloat {
    public float x;
    public float y;

    public PointFloat() {
        x = y = 0f;
    }

    public PointFloat(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PointFloat move(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }
}
