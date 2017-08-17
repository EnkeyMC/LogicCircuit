package com.enkey.logiccircuit.utils;

import java.awt.Point;
import java.util.Iterator;

public class Directional<T> implements Iterable<T> {

    public T up;
    public T right;
    public T down;
    public T left;

    public Directional (T up, T right, T down, T left) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    public Directional () {
        up = down = left = right = null;
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

    public void set(Direction direction, T obj) {
        switch (direction) {
            case UP:
                up = obj;
                break;
            case RIGHT:
                right = obj;
                break;
            case LEFT:
                left = obj;
                break;
            default:
                down = obj;
        }
    }

    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private Direction currentDir = Direction.UP;

            public boolean hasNext() {
                if (currentDir == Direction.LEFT)
                    return false;
                return true;
            }

            public T next() {
                switch (currentDir) {
                    case UP:
                        currentDir = Direction.RIGHT;
                        return up;
                    case RIGHT:
                        currentDir = Direction.DOWN;
                        return right;
                    case DOWN:
                        currentDir = Direction.LEFT;
                        return down;
                    case LEFT:
                        return left;
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
}
