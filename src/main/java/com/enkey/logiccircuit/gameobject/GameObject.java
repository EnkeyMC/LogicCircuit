package com.enkey.logiccircuit.gameobject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Point;

public abstract class GameObject {

    public abstract void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException;

    public abstract void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState, Point position) throws SlickException;
}
