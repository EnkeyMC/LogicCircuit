package com.enkey.logiccircuit.actions;

import com.enkey.logiccircuit.gamestates.GameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by MOmac on 28.08.2017.
 */
public abstract class Action {

    public Action() {

    }

    public void init() {

    }

    public abstract void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException;

    public abstract void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState) throws SlickException;
}
