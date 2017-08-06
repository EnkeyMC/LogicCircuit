package com.enkey.logiccircuit;

import com.enkey.logiccircuit.gamestates.GameState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A game using Slick2d
 */
public class App extends StateBasedGame {

    // Game state identifiers
    public static final int GAME = 1;

    // Application Properties
    public static final int WIDTH   = 640;
    public static final int HEIGHT  = 480;
    public static final int FPS     = 60;
    public static final double VERSION = 0.1;
    public static final String NAME = "LogicCircuit";

    public App() {
        super(NAME + " v" + VERSION);
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setForceExit(false);
        app.start();
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new GameState());
    }
}
