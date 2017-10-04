package com.enkey.logiccircuit;

import com.enkey.logiccircuit.utils.PointFloat;
import com.enkey.logiccircuit.utils.PointInt;
import com.enkey.logiccircuit.utils.Utils;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Camera {
    protected Rectangle camView;
    protected PointInt offset;
    protected float scale;

    private final float scaleStep = 1.2f;

    private static final float minScale = 0.2f;
    private static final float maxScale = 2f;

    public Camera() {
        camView = new Rectangle(0,0,0,0);
        offset = new PointInt();
        scale = 1f;
    }

    public Camera(Rectangle view, PointInt offset, float scale) {
        camView = view;
        this.offset = offset;
        this.scale = scale;
    }

    public void startCameraRendering(Graphics g) {
        g.scale(scale, scale);
        g.translate(-offset.x, -offset.y);
        g.setClip(camView);
    }

    public void stopCameraRendering(Graphics g) {
        g.translate(offset.x, offset.y);
        g.scale(1/scale, 1/scale);
        g.clearClip();
    }

    public PointFloat worldToScreen(float x, float y) {
        return new PointFloat((x - offset.x) * scale, (y - offset.y) * scale);
    }

    public PointInt worldToScreen(PointInt coords) {
        coords.x -= offset.x;
        coords.y -= offset.y;
        coords.x *= scale;
        coords.y *= scale;
        return coords;
    }

    public PointFloat screenToWorld(float x, float y) {
        return new PointFloat((x / scale) + offset.x, (y / scale) + offset.y);
    }

    public PointInt screenToWorld(PointInt coords) {
        coords.x /= scale;
        coords.y /= scale;
        coords.y += offset.y;
        coords.x += offset.x;
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
        return offset.x;
    }

    public float getOffsetY() {
        return offset.y;
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

    public void setOffset(PointInt offset) {
        this.offset = offset;
    }

    public void addOffsetX(float x) {
        offset.x += x;
    }

    public void addOffsetY(float y) {
        offset.y += y;
    }

    public void scale(int amount) {
        float oldScale = scale;

        this.scale *= Math.pow(scaleStep, amount);
        this.scale = (float) Utils.clamp(scale, Camera.minScale, Camera.maxScale);

        this.offset.x += (camView.getWidth() / 2f) * (1/oldScale - 1/scale);
        this.offset.y += (camView.getHeight() / 2f) * (1/oldScale - 1/scale);
    }

    public void resetScale() {
        this.scale = 1f;
    }
}
