package com.enkey.logiccircuit.map;

import com.enkey.logiccircuit.gameobjects.GameObject;
import com.enkey.logiccircuit.gameobjects.Wireable;
import com.enkey.logiccircuit.gamestates.GameState;
import com.enkey.logiccircuit.utils.PointInt;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfiniteMap {

    private HashMap<PointInt, GameObject> map;
    private int minX, maxX, minY, maxY;
    private ArrayList<PointInt> toRemove;
    private ArrayList<Wireable> wires;

    public InfiniteMap() {
        this.map = new HashMap<PointInt, GameObject>();
        this.minX = this.maxX = this.minY = this.maxY = 0;
        this.toRemove = new ArrayList<PointInt>();
        this.wires = new ArrayList<Wireable>();
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException {
        for (PointInt p : toRemove) {
            map.remove(p);
        }

        toRemove.clear();

        for (Map.Entry<PointInt, GameObject> kv : map.entrySet()) {
            kv.getValue().update(gameContainer, app, i, gameState);
            if (kv.getValue().isDead)
                toRemove.add(kv.getKey());
        }
    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState) throws SlickException {
        for (Map.Entry<PointInt, GameObject> entry : map.entrySet()) {
            entry.getValue().render(gameContainer, app, g, gameState, entry.getKey(), false);
        }

        /*for (Wireable wire : wires) {
            wire.resetRendered();
        }*/
    }

    public void setObject(PointInt coord, GameObject object) {
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
        this.setObject(new PointInt(x, y), object);
    }

    public GameObject at(PointInt coord) {
        return this.map.get(coord);
    }

    public GameObject at(int x, int y) {
        return this.at(new PointInt(x,y));
    }
}
