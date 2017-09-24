package com.enkey.logiccircuit.gameobjects;

import com.enkey.logiccircuit.App;
import com.enkey.logiccircuit.utils.PointInt;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

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

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState, PointInt position, boolean ghost) throws SlickException {
    }
}
