package com.enkey.logiccircuit.gameobjects;

import java.awt.Point;

public abstract class Wireable extends GameObject {

    public Wireable() {
        rendered = false;
    }

    protected boolean rendered;

    public boolean wireRendered() {
        return rendered;
    }

    public void resetRendered() {
        rendered = false;
    }

    public abstract boolean isActive();
    public abstract boolean canConnectFrom(Point position);
}
