package com.enkey.logiccircuit.gameobjects;

import com.enkey.logiccircuit.App;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Point;

public class WireProxy extends GameObject {

    public Wireable vertical;
    public Wireable horizontal;

    public WireProxy() {
        super();
    }

    public WireProxy(Wireable vertical, Wireable horizontal) {
        super();
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException {
        if (vertical != null && vertical.isDead) {
            vertical = null;
        }
        if (horizontal != null && horizontal.isDead) {
            horizontal = null;
        }

        if (horizontal == null && vertical == null) {
            this.isDead = true;
        }
    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState, Point position, boolean ghost) throws SlickException {
        g.setColor(new Color(255,255,255,0.2f));
        g.fillRect(position.x, position.y, App.tileSize, App.tileSize);
        g.setColor(Color.black);
        if (vertical != null)
            g.fillRect(position.x, position.y, 2, 2);
        if (horizontal != null)
            g.fillRect(position.x + 3, position.y, 2, 2);
    }
}
