package com.ms.game;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Ground extends Scrollable {
    /**
     * Ground object
     *
     * Created by MS
     */
    private Random r;
    private int length;
    private Rectangle rectangle;

    /**
     * Ground constructor
     * @param x - position x
     * @param y - position y
     * @param width - width
     * @param height - height
     * @param scrollSpeed - speed
     * @param length - length
     */
    public Ground(float x, float y, int width, int height, float scrollSpeed, int length) {
        super(x, y, width, height, scrollSpeed);
        r = new Random();
        rectangle = new Rectangle();
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = width * length;
        rectangle.height = height;
    }

    /**
     * Reset function
     * @param newX - new position x
     * @param newY - new position y
     */
    @Override
    public void reset(float newX, float newY) {
        super.reset(newX, newY);
        length = l;
        rectangle.setPosition(newX, newY);
        rectangle.width = 512 * length;
    }

    /**
     * Reset function
     * @param newX - new position x
     */
    @Override
    public void reset(float newX) {
        super.reset(newX);
        length = l;
        rectangle.setPosition(newX, getY());
        rectangle.width = 512 * length;
    }

    /**
     * Rectangle
     * @return - returning rectangle
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Rectangle
     * @param rectangle - new rectangle
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Length
     * @return - returning length
     */
    public int getLength() {
        return length;
    }

    /**
     * Length
     * @param length - length
     */
    public void setLength(int length) {
        this.length = length;
    }
}
