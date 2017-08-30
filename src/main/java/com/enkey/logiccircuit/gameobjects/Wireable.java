package com.enkey.logiccircuit.gameobjects;

import com.enkey.logiccircuit.utils.Direction;
import com.enkey.logiccircuit.utils.Directional;

import java.awt.Point;

public abstract class Wireable extends GameObject {

    protected Directional<Wireable> connections;

    protected boolean rendered;  // Not used currently


    public Wireable() {
        connections = new Directional<Wireable>();
        rendered = false;
    }

    public boolean wireRendered() {
        return rendered;
    }
    public void resetRendered() {
        rendered = false;
    }
    public Directional<Wireable> getConnections() {
        return connections;
    }

    public boolean connect(Wireable wire) {
        return connect(wire, null);
    }

    public boolean connect(Wireable wire, Point from) {
        if (from == null) {
            from = wire.position;
        }

        if (canConnectFrom(from)) {
            connections.set(Direction.getDirection(this.position, from), wire);
            return true;
        }
        return false;
    }

    public abstract boolean isActive();
    public abstract boolean canConnectFrom(Point position);
}
