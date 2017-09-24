package com.enkey.logiccircuit.gameobjects;

import com.enkey.logiccircuit.utils.PointInt;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Proxy extends GameObject {

    public GameObject obj;

    public Proxy(GameObject obj) {
        super();
        this.obj = obj;
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException {
        if (this.obj.isDead)
            this.isDead = true;
    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState, PointInt position, boolean ghost) throws SlickException {}
}
