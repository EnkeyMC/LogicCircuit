package com.enkey.logiccircuit.actions;

import com.enkey.logiccircuit.App;
import com.enkey.logiccircuit.gameobjects.GameObject;
import com.enkey.logiccircuit.gameobjects.WireNode;
import com.enkey.logiccircuit.gameobjects.WireProxy;
import com.enkey.logiccircuit.gameobjects.Wireable;
import com.enkey.logiccircuit.gamestates.GameState;
import com.enkey.logiccircuit.map.InfiniteMap;
import com.enkey.logiccircuit.utils.Direction;
import com.enkey.logiccircuit.utils.PointInt;
import com.enkey.logiccircuit.utils.Utils;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * Created by MOmac on 28.08.2017.
 */
public class PlaceWireAction extends Action {

    private Wireable lastWire;

    @Override
    public void init() {
        super.init();

        lastWire = null;
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i, GameState gameState) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isMousePressed(0)) {
            PointInt position = new PointInt(input.getMouseX(), input.getMouseY());
            gameState.getCamera().screenToWorld(position);
            position.move(Utils.alignToGrid(position.x), Utils.alignToGrid(position.y));

            InfiniteMap map = gameState.getMap();
            Wireable wire;

            GameObject obj = map.at(position);

            if (obj == null) {
                wire = new WireNode();
                map.setObject(position, wire);
            } else {
                if (obj instanceof Wireable) {
                    wire = (Wireable) obj;
                } else if (obj instanceof  WireProxy) {
                    WireProxy proxy = (WireProxy) obj;

                    wire = new WireNode();

                    ArrayList<Wireable> wires = new ArrayList<Wireable>();
                    if (proxy.vertical != null)
                        wires.add(proxy.vertical);
                    if (proxy.horizontal != null)
                        wires.add(proxy.horizontal);

                    Wireable opposite;

                    map.setObject(position, wire);
                    for (Wireable proxyWire : wires) {
                        Direction dir = Direction.getDirection(proxyWire.position, position);
                        opposite = proxyWire.getConnections().get(dir);

                        proxyWire.getConnections().set(dir, null);
                        proxyWire.connect(wire, position);
                        wire.connect(proxyWire);

                        opposite.getConnections().set(dir.opposite(), null);
                        opposite.connect(wire, position);
                        wire.connect(opposite);
                        placeProxies(wire, position, opposite.position, map);
                    }

                    proxy.isDead = true;
                } else {
                    return;
                }
            }

            if (lastWire != null) {
                if (lastWire.canConnectFrom(position)) {
                    wire.connect(lastWire);
                    lastWire.connect(wire);
                    placeProxies(wire, position, lastWire.position, map);
                }
            }

            lastWire = wire;
        }
    }

    public void render(GameContainer gameContainer, StateBasedGame app, Graphics g, GameState gameState) throws SlickException {
        Input input = gameContainer.getInput();

        PointInt position = new PointInt(input.getMouseX(), input.getMouseY());
        gameState.getCamera().screenToWorld(position);
        position.move(Utils.alignToGrid(position.x), Utils.alignToGrid(position.y));

        WireNode wire = new WireNode();
        wire.position = position;

        if (lastWire != null) {
            if (lastWire.canConnectFrom(position)) {
                wire.connect(lastWire);
            }
        }

        wire.render(gameContainer, app, g, gameState, position, true);
    }

    protected void placeProxies(Wireable wire, PointInt pos1, PointInt pos2, InfiniteMap map) {
        int tileSize = App.tileSize;

        if (pos1.x == pos2.x) {
            if (pos1.y > pos2.y) {
                PointInt tmp = pos1;
                pos1 = pos2;
                pos2 = tmp;
            }

            WireProxy tmp;

            for (int y = pos1.y + tileSize; y < pos2.y; y += tileSize) {
                GameObject obj = map.at(pos1.x, y);
                if (obj == null) {
                    tmp = new WireProxy();
                    map.setObject(pos1.x, y, tmp);
                    tmp.vertical = wire;
                } else if (obj instanceof WireProxy) {
                    ((WireProxy) obj).vertical = wire;
                }
            }
        } else if (pos1.y == pos2.y) {
            if (pos1.x > pos2.x) {
                PointInt tmp = pos1;
                pos1 = pos2;
                pos2 = tmp;
            }

            WireProxy tmp;

            for (int x = pos1.x + tileSize; x < pos2.x; x += tileSize) {
                GameObject obj = map.at(x, pos1.y);
                if (obj == null) {
                    tmp = new WireProxy();
                    map.setObject(x, pos1.y, tmp);
                    tmp.horizontal = wire;
                } else if (obj instanceof WireProxy) {
                    ((WireProxy) obj).horizontal = wire;
                }
            }
        }
    }
}
