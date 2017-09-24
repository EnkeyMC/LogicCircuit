package com.enkey.logiccircuit.utils;

import com.enkey.logiccircuit.App;

public class Utils {
    public static int alignToGrid(int x) {
        return alignToGrid(x, App.tileSize);
    }

    public static int alignToGrid(int x, int gridSize) {
        if (x < 0)
            x -= gridSize;
        return (x / gridSize) * gridSize;
    }

    public static double clamp(double x, double min, double max) {
        if (x < min)
            return min;
        if (x > max)
            return max;
        return x;
    }
}
