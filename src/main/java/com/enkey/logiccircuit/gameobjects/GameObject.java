package com.enkey.logiccircuit.gameobjects;

import com.enkey.logiccircuit.utils.PointInt;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class GameObject {

    public boolean isDead = false;
    public PointInt position;

    public abstract void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException;

    public abstract void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState, PointInt position, boolean ghost) throws SlickException;
}
