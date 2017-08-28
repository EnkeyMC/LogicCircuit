package com.enkey.logiccircuit.actions;

import com.enkey.logiccircuit.App;
import com.enkey.logiccircuit.gameobjects.WireNode;
import com.enkey.logiccircuit.gamestates.GameState;
import com.enkey.logiccircuit.utils.Utils;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Point;

/**
 * Created by MOmac on 28.08.2017.
 */
public class PlaceWireAction extends Action {

    private WireNode lastWire;

    @Override
    public void init() {
        super.init();

        lastWire = null;
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isMousePressed(0)) {
            Point position = new Point(Utils.alignToGrid(input.getMouseX()), Utils.alignToGrid(input.getMouseY()));

            WireNode wire = new WireNode();
            gameState.getMap().setObject(position, wire);

            if (lastWire != null) {
                if (lastWire.canConnectFrom(position)) {
                    wire.connect(lastWire);
                    lastWire.connect(wire);
                }
            }

            lastWire = wire;
        }
    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState) throws SlickException {
        Input input = gameContainer.getInput();
        int tileSize = App.tileSize;
        Point position = new Point(
                Utils.alignToGrid(input.getAbsoluteMouseX(), tileSize),
                Utils.alignToGrid(input.getAbsoluteMouseY(), tileSize)
        );

        WireNode wire = new WireNode();
        wire.position = position;

        if (lastWire != null) {
            if (lastWire.canConnectFrom(position)) {
                wire.connect(lastWire);
            }
        }

        wire.render(gameContainer, app, g, gameState, position);
    }
}