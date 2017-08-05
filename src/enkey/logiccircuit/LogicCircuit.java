package enkey.logiccircuit;

import enkey.logiccircuit.gamestates.GameState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by MOmac on 05.08.2017.
 */
public class LogicCircuit extends StateBasedGame {

    // Game state identifiers
    public static final int GAME = 1;

    // Application Properties
    public static final int WIDTH   = 640;
    public static final int HEIGHT  = 480;
    public static final int FPS     = 60;
    public static final double VERSION = 0.1;
    public static final String NAME = "LogicCircuit";

    public LogicCircuit(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new GameState());
    }

    // Main Method
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new LogicCircuit(NAME + " v" + VERSION));
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(true);
            app.start();
        } catch(SlickException e) {
            e.printStackTrace();
        }
    }
}
