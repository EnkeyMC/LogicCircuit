package com.enkey.logiccircuit.gameobject;

import com.enkey.logiccircuit.App;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;

public class LogicalNOT extends GameObject {
    public void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException {

    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState, Point position) throws SlickException {
        g.setColor(Color.white);
        g.fillRect(position.x, position.y, App.tileSize, App.tileSize);
    }
}
