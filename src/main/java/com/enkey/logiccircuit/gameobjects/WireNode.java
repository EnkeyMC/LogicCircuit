package com.enkey.logiccircuit.gameobjects;

import com.enkey.logiccircuit.App;
import com.enkey.logiccircuit.utils.Direction;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Point;

public class WireNode extends Wireable {

    public WireNode() {
        super();
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException {

    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState, Point position, boolean ghost) throws SlickException {
        Color wireColor = Color.red;
        if (ghost)
            wireColor = new Color(255,0,0, 0.5f);

        g.setColor(wireColor);

        g.fillRect(position.x + 4, position.y + 4, App.tileSize - 8, App.tileSize - 8);

        int i = 0;

        for (Wireable wire : connections) {
            if (wire != null && !(wire).wireRendered()) {
                g.setColor(wireColor);
                g.setLineWidth(10);
                g.drawLine(position.x + (App.tileSize / 2), position.y + (App.tileSize / 2), wire.position.x + (App.tileSize / 2), wire.position.y + (App.tileSize / 2));
                g.setColor(Color.black);
                g.fillRect(position.x + (i * 3), position.y, 2,2);
            }
            i++;
        }

        //this.rendered = true;
    }

    public boolean isActive() {
        for (Wireable wire : connections) {
            if (wire.isActive())
                return true;
        }
        return false;
    }

    public boolean canConnectFrom(Point position) {
        if (this.position.x == position.x || this.position.y == position.y) {
            if (this.connections.get(Direction.getDirection(this.position, position)) == null)
                return true;
        }
        return false;
    }
}
