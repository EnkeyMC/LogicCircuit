package com.enkey.logiccircuit.map;

import com.enkey.logiccircuit.gameobject.GameObject;
import com.enkey.logiccircuit.gamestates.GameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class InfiniteMap {

    private HashMap<Point, GameObject> map;
    private int minX, maxX, minY, maxY;

    public InfiniteMap() {
        this.map = new HashMap<Point, GameObject>();
        this.minX = this.maxX = this.minY = this.maxY = 0;
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException {
        for (GameObject obj : map.values()) {
            obj.update(gameContainer, app, i, gameState);
        }
    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState) throws SlickException {
        for (Map.Entry<Point, GameObject> entry : map.entrySet()) {
            entry.getValue().render(gameContainer, app, g, gameState, entry.getKey());
        }
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
