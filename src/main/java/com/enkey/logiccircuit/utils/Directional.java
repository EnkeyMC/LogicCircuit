package com.enkey.logiccircuit.utils;

import java.util.Iterator;

public class Directional<T> implements Iterable<T> {

    private T up;
    private T right;
    private T down;
    private T left;

    private byte mask;

    public static final byte MASK_UP = 1;
    public static final byte MASK_RIGHT = 2;
    public static final byte MASK_DOWN = 4;
    public static final byte MASK_LEFT = 8;

    public Directional (byte mask) {
        this.mask = mask;
        up = down = left = right = null;
    }

    public Directional () {
        up = down = left = right = null;
        mask = 0;
    }

    public T get(Direction direction) {
        switch (direction) {
            case UP:
                return up;
            case RIGHT:
                return right;
            case LEFT:
                return left;
            default:
                return down;
        }
    }

    public boolean set(Direction direction, T obj) {
        switch (direction) {
            case UP:
                if ((mask & MASK_UP) == 0) {
                    up = obj;
                    return true;
                }
                return false;
            case RIGHT:
                if ((mask & MASK_RIGHT) == 0) {
                    right = obj;
                    return true;
                }
                return false;
            case LEFT:
                if ((mask & MASK_LEFT) == 0) {
                    left = obj;
                    return true;
                }
                return false;
            default:
                if ((mask & MASK_DOWN) == 0) {
                    down = obj;
                    return true;
                }
                return false;
        }
    }

    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private Direction currentDir = Direction.UP;
            private boolean last = false;

            public boolean hasNext() {
                if (last)
                    return false;

                switch (currentDir) {
                    case UP:
                        if ((mask & MASK_UP) == 0)
                            return true;
                    case RIGHT:
                        if ((mask & MASK_RIGHT) == 0)
                            return true;
                    case DOWN:
                        if ((mask & MASK_DOWN) == 0)
                            return true;
                    case LEFT:
                        if ((mask & MASK_LEFT) == 0)
                            return true;
                }

                return false;
            }

            public T next() {
                switch (currentDir) {
                    case UP:
                        currentDir = Direction.RIGHT;
                        if ((mask & MASK_UP) == 0)
                            return up;
                    case RIGHT:
                        currentDir = Direction.DOWN;
                        if ((mask & MASK_RIGHT) == 0)
                            return right;
                    case DOWN:
                        currentDir = Direction.LEFT;
                        if ((mask & MASK_DOWN) == 0)
                            return down;
                    case LEFT:
                        if ((mask & MASK_LEFT) == 0) {
                            last = true;
                            return left;
                        }
                    default:
                        return null;
                }
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        return it;
    }

    public byte getMask() {
        return mask;
    }

    public void setMask(byte mask) {
        this.mask = mask;
    }
}
