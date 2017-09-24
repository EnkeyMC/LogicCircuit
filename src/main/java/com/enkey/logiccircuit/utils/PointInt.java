package com.enkey.logiccircuit.utils;

/**
 * Created by MOmac on 23.09.2017.
 */
public class PointInt {
    public int x;
    public int y;

    public PointInt() {
        x = y = 0;
    }

    public PointInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PointInt move(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }
}
