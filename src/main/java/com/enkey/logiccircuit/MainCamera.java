package com.enkey.logiccircuit;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

/**
 * Created by MOmac on 23.09.2017.
 */
public class MainCamera extends Camera implements MouseListener {

    protected Input input;
    protected boolean dragMap = false;

    public void mouseWheelMoved(int change) {
        this.scale(change/120);
    }

    public void mouseClicked(int button, int x, int y, int clickCount) {

    }

    public void mousePressed(int button, int x, int y) {
        if (button == Input.MOUSE_MIDDLE_BUTTON)
            dragMap = true;
    }

    public void mouseReleased(int button, int x, int y) {
        if (button == Input.MOUSE_MIDDLE_BUTTON)
            dragMap = false;
    }

    public void mouseMoved(int oldx, int oldy, int newx, int newy) {

    }

    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
        if (this.dragMap) {
            this.addOffsetX((oldx - newx) / scale);
            this.addOffsetY((oldy - newy) / scale);
        }
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public boolean isAcceptingInput() {
        return true;
    }

    public void inputEnded() {

    }

    public void inputStarted() {

    }
}
