package com.enkey.logiccircuit.gamestates;

import com.enkey.logiccircuit.App;
import com.enkey.logiccircuit.Camera;
import com.enkey.logiccircuit.gameobject.Wireable;
import com.enkey.logiccircuit.utils.Utils;
import com.enkey.logiccircuit.gameobject.GameObject;
import com.enkey.logiccircuit.gameobject.LogicalNOT;
import com.enkey.logiccircuit.gameobject.WireNode;
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

    private WireNode lastWire;

    @Override
    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame app) throws SlickException {
        map = new InfiniteMap();
        camera = new Camera();
        lastWire = null;
    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g) throws SlickException {
        g.setColor(Color.gray);
        g.fillRect(0, 0, gameContainer.getWidth(), gameContainer.getHeight());

        this.camera.setWidth(gameContainer.getWidth());
        this.camera.setHeight(gameContainer.getHeight());
        this.camera.startCameraRendering(g);
        this.map.render(gameContainer, app, g, this);

        Input input = gameContainer.getInput();
        int tileSize = App.tileSize;
        Rectangle rect = new Rectangle(
                Utils.alignToGrid(input.getAbsoluteMouseX(), tileSize),
                Utils.alignToGrid(input.getAbsoluteMouseY(), tileSize),
                tileSize,
                tileSize
        );

        g.setColor(Color.black);
        g.fill(rect);
        this.camera.stopCameraRendering(g);
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isMousePressed(0)) {
            Point position = new Point(Utils.alignToGrid(input.getMouseX()), Utils.alignToGrid(input.getMouseY()));

            WireNode wire = new WireNode();
            map.setObject(position, wire);

            if (lastWire != null) {
                if (lastWire.canConnectFrom(position)) {
                    wire.connect(lastWire);
                    lastWire.connect(wire);
                }
            }

            lastWire = wire;
        }

        this.map.update(gameContainer, app, i, this);
    }

    public InfiniteMap getMap() {
        return this.map;
    }
}
