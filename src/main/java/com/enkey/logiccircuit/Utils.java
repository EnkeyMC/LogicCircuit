package com.enkey.logiccircuit;

public class Utils {
    public static int alignToGrid(int x) {
        return alignToGrid(x, App.tileSize);
    }

    public static int alignToGrid(int x, int gridSize) {
        return (x / gridSize) * gridSize;
    }
}
