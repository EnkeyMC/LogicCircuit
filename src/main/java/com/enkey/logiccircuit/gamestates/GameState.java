package com.enkey.logiccircuit.gamestates;

import com.enkey.logiccircuit.App;
import com.enkey.logiccircuit.Camera;
import com.enkey.logiccircuit.map.InfiniteMap;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by MOmac on 05.08.2017.
 */
public class GameState extends BasicGameState {
    public static final int ID = 1;

    private InfiniteMap map;
    private Camera camera;

    @Override
    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame app) throws SlickException {
        map = new InfiniteMap();
        camera = new Camera();
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
        Rectangle rect = new Rectangle((input.getAbsoluteMouseX() / tileSize) * tileSize, (input.getAbsoluteMouseY() / tileSize) * tileSize, tileSize, tileSize);
        g.setColor(Color.black);
        g.fill(rect);
        this.camera.stopCameraRendering(g);
    }

    public void update(GameContainer gameContainer, StateBasedGame app, int i) throws SlickException {
        this.map.update(gameContainer, app, i, this);
    }

    public InfiniteMap getMap() {
        return this.map;
    }
}
