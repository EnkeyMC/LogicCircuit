package com.enkey.logiccircuit.gamestates;

import com.enkey.logiccircuit.App;
import com.enkey.logiccircuit.Camera;
import com.enkey.logiccircuit.actions.Action;
import com.enkey.logiccircuit.actions.PlaceWireAction;
import com.enkey.logiccircuit.utils.Utils;
import com.enkey.logiccircuit.gameobjects.WireNode;
import com.enkey.logiccircuit.map.InfiniteMap;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;

/**
 * Created by MOmac on 05.08.2017.
 */
public class GameState extends BasicGameState {
    public static final int ID = 1;

    private InfiniteMap map;
    private Camera camera;
    private Action action;

    @Override
    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame app) throws SlickException {
        map = new InfiniteMap();
        camera = new Camera();
        action = new PlaceWireAction();
        action.init();
    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g) throws SlickException {
        g.setColor(Color.gray);
        g.fillRect(0, 0, gameContainer.getWidth(), gameContainer.getHeight());

        this.camera.setWidth(gameContainer.getWidth());
        this.camera.setHeight(gameContainer.getHeight());
        this.camera.startCameraRendering(g);
        this.map.render(gameContainer, app, g, this);

        this.action.render(gameContainer, app, g, this);

        this.camera.stopCameraRendering(g);
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i) throws SlickException {
        this.action.update(gameContainer, app, i, this);
        this.map.update(gameContainer, app, i, this);
    }

    public InfiniteMap getMap() {
        return this.map;
    }
}
