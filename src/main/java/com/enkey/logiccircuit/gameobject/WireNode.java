package com.enkey.logiccircuit.gameobject;

import com.enkey.logiccircuit.App;
import com.enkey.logiccircuit.utils.Direction;
import com.enkey.logiccircuit.utils.Directional;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Point;

public class WireNode extends Wireable {

    protected Directional<Wireable> connections;

    public WireNode() {
        super();
        connections = new Directional<Wireable>();
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException {

    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState, Point position) throws SlickException {
        g.setColor(Color.red);
        g.fillRect(position.x, position.y, App.tileSize, App.tileSize);

        for (Wireable wire : connections) {
            if (wire != null && !(wire).wireRendered()) {
                g.setLineWidth(14);
                g.drawLine(position.x + (App.tileSize / 2), position.y + (App.tileSize / 2), wire.position.x + (App.tileSize / 2), wire.position.y + (App.tileSize / 2));
            }
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

    public Directional<Wireable> getConnections() {
        return connections;
    }

    public boolean connect(Wireable wire) {
        return connect(wire, null);
    }

    public boolean connect(Wireable wire, Point from) {
        if (from == null) {
            from = wire.position;
        }

        if (canConnectFrom(from)) {
            connections.set(Direction.getDirection(this.position, from), wire);
            return true;
        }
        return false;
    }
}
