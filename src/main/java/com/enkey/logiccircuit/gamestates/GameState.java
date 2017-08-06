package com.enkey.logiccircuit.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by MOmac on 05.08.2017.
 */
public class GameState extends BasicGameState {
    public static final int ID = 1;

    @Override
    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame app) throws SlickException {

    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g) throws SlickException {
        g.setColor(Color.gray);
        g.fillRect(0, 0, gameContainer.getWidth(), gameContainer.getHeight());
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i) throws SlickException {

    }
}
