package com.ms.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Paper extends Scrollable {
    /**
     * Paper object
     *
     * Created by MS
     */
    private Vector2 position;
    private int width;
    private int height;
    private Rectangle rectangle;
    private int weight;
    private Random r;

    /**
     * Paper constructor
     * @param x - position x
     * @param y - position y
     * @param width - width
     * @param height - height
     * @param scrollSpeed - scrolling speed
     */
    public Paper(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        this.width = width;
        this.height = height;
        position = new Vector2(getX(), getY());
        rectangle = new Rectangle();
        r = new Random();
        rectangle.width = width;
        rectangle.height = height;
        weight = 5;
    }

    /**
     * Reset function
     * @param newX - new position x
     * @param newY - new position y
     */
    @Override
    public void reset(float newX, float newY) {
        weight = 1 + r.nextInt(12);
        super.reset(newX, newY);
    }

    /**
     * Reset function
     * @param newX - new position x
     */
    @Override
    public void reset(float newX) {
        weight = 1 + r.nextInt(12);
        super.reset(newX);
    }

    /**
     * Getter
     * @return - return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Getter
     * @return - return position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Setter
     * @param position - position
     */
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /**
     * Getter
     * @return - return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Setter
     * @param width - width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Getter
     * @return - return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter
     * @param height - height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter
     * @return - return rectangle collision
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Setter
     * @param rectangle - rectangle collision
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
