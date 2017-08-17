package com.enkey.logiccircuit.utils;

import java.awt.Point;

public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);

    private int value;

    Direction(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static Direction getDirection(Point target, Point fromDirection) {
        int deltaX = fromDirection.x - target.x;
        int deltaY = target.y - fromDirection.y;

        if (deltaY >= Math.abs(deltaX)) {
            return Direction.UP;
        } else if (deltaX > Math.abs(deltaY)) {
            return Direction.RIGHT;
        } else if (deltaY < -Math.abs(deltaX)) {
            return Direction.DOWN;
        } else {
            return Direction.LEFT;
        }
    }
}
