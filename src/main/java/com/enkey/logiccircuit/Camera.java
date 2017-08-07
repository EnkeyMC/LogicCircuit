package com.enkey.logiccircuit;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public class Camera {
    private Rectangle camView;
    private Point offset;

    public Camera() {
        camView = new Rectangle(0,0,0,0);
        offset = new Point(0,0);
    }

    public Camera(Rectangle view, Point offset) {
        camView = view;
        this.offset = offset;
    }

    public void startCameraRendering(Graphics g) {
        g.translate(-offset.getX(), -offset.getY());
        g.setClip(camView);
    }

    public void stopCameraRendering(Graphics g) {
        g.translate(offset.getX(), offset.getY());
        g.clearClip();
    }

    public Point worldToScreen(float x, float y) {
        return new Point(x - offset.getX(), y - offset.getY());
    }

    public Point worldToScreen(Point coords) {
        coords.setX(coords.getX() - offset.getX());
        coords.setY(coords.getY() - offset.getY());
        return coords;
    }

    public Point screenToWorld(float x, float y) {
        return new Point(x + offset.getX(), y + offset.getY());
    }

    public Point screenToWorld(Point coords) {
        coords.setX(coords.getX() + offset.getX());
        coords.setY(coords.getY() + offset.getY());
        return coords;
    }

    public boolean isVisible(Rectangle rect) {
        return this.camView.intersects(rect);
    }

    public float getX() {
        return camView.getX();
    }

    public float getY() {
        return camView.getY();
    }

    public float getWidth() {
        return camView.getWidth();
    }

    public float getHeight() {
        return camView.getHeight();
    }

    public float getOffsetX() {
        return offset.getX();
    }

    public float getOffsetY() {
        return offset.getY();
    }

    public void setWidth(float width) {
        camView.setWidth(width);
    }

    public void setHeight(float height) {
        camView.setHeight(height);
    }

    public void setX(float x) {
        camView.setX(x);
    }

    public void setY(float y) {
        camView.setY(y);
    }

    public void setOffset(Point offset) {
        this.offset = offset;
    }

    public void addOffsetX(float x) {
        offset.setX(offset.getX() + x);
    }

    public void addOffsetY(float y) {
        offset.setY(offset.getY() + y);
    }
}
