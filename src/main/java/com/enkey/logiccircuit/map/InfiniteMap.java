package com.enkey.logiccircuit.map;

import com.enkey.logiccircuit.gameobject.GameObject;

import java.awt.Point;
import java.util.HashMap;

public class InfiniteMap {

    private HashMap<Point, GameObject> map;
    private int minX, maxX, minY, maxY;

    public InfiniteMap() {
        this.map = new HashMap<Point, GameObject>();
        this.minX = this.maxX = this.minY = this.maxY = 0;
    }

    public void setObject(Point coord, GameObject object) {
        if (coord.x < minX)
            minX = coord.x;
        if (coord.x > maxX)
            maxX = coord.x;
        if (coord.y < minY)
            minY = coord.y;
        if (coord.y > maxY)
            maxY = coord.y;

        this.map.put(coord, object);
    }

    public void setObject(int x, int y, GameObject object) {
        this.setObject(new Point(x, y), object);
    }

    public GameObject at(Point coord) {
        return this.map.get(coord);
    }

    public GameObject at(int x, int y) {
        return this.at(new Point(x,y));
    }
}
