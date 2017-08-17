package com.enkey.logiccircuit.map;

import com.enkey.logiccircuit.gameobject.GameObject;
import com.enkey.logiccircuit.gameobject.Wireable;
import com.enkey.logiccircuit.gamestates.GameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfiniteMap {

    private HashMap<Point, GameObject> map;
    private int minX, maxX, minY, maxY;
    private ArrayList<Point> toRemove;
    private ArrayList<Wireable> wires;

    public InfiniteMap() {
        this.map = new HashMap<Point, GameObject>();
        this.minX = this.maxX = this.minY = this.maxY = 0;
        this.toRemove = new ArrayList<Point>();
        this.wires = new ArrayList<Wireable>();
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException {
        for (Point p : toRemove) {
            map.remove(p);
        }

        toRemove.clear();

        for (Map.Entry<Point, GameObject> kv : map.entrySet()) {
            kv.getValue().update(gameContainer, app, i, gameState);
            if (kv.getValue().isDead)
                toRemove.add(kv.getKey());
        }
    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState) throws SlickException {
        for (Map.Entry<Point, GameObject> entry : map.entrySet()) {
            entry.getValue().render(gameContainer, app, g, gameState, entry.getKey());
        }

        /*for (Wireable wire : wires) {
            wire.resetRendered();
        }*/
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

        object.position = coord;

        if (object instanceof Wireable)
            this.wires.add((Wireable) object);

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
