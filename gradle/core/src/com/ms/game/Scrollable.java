package com.ms.game;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {
    /**
     * Main class for scrollable objects
     *
     * Created by MS
     */
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledLeft;
    protected int l;

    /**
     * Scrollable constructor
     * @param x - position x
     * @param y - position y
     * @param width - width
     * @param height - height
     * @param scrollSpeed - scrolling speed
     */
    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    /**
     * Update for background and paper
     * @param delta - delta time
     */
    public void update(float delta) {
        position.set(new Vector2(position.x - velocity.x, position.y)); //* Changing position

        if (position.x + 1000 <= 0) {
            isScrolledLeft = true;
        } //* Checking scrolling
    }

    /**
     * Update for ground
     * @param delta - delta time
     */
    public void updateG(float delta) {
        position.set(new Vector2(position.x - velocity.x, position.y)); //* Changing position

        if (position.x + 512 * l < 0) {
            isScrolledLeft = true;
        } //* Checking scrolling
    }

    /**
     * Reset function
     * @param newX - new position x
     */
    public void reset(float newX) {
        position.x = newX;
        isScrolledLeft = false;
    }

    /**
     * Setting new position y function
     * @param newY - new position y
     */
    public void setY(float newY) {
        position.y = newY;
    }

    /**
     * Setting new length function
     * @param l - set new length
     */
    public void setL(int l) {
        this.l = l;
    }

    /**
     * Stop function
     */
    public void stop() {
        velocity.x = 0;
    }

    /**
     * Scrolling left function
     * @return - returning boolean variable
     */
    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    /**
     * Getting full platform of ground
     * @return - get full width
     */
    public float getTailX() {
        return position.x + width;
    }

    /**
     * Getting position x
     * @return - position x
     */
    public float getX() {
        return position.x;
    }

    /**
     * Getting position y
     * @return - position y
     */
    public float getY() {
        return position.y;
    }

    /**
     * Getting width
     * @return - returning width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getting height
     * @return - returning height
     */
    public int getHeight() {
        return height;
    }

}
