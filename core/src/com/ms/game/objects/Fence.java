package com.ms.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Fence extends Scrollable {
    /**
     * Fence object
     *
     * Created by MS
     */
    private Vector2 position;
    private int width;
    private int height;
    private int type;
    private Rectangle rectangle;
    private Random r;

    /**
     * Paper constructor
     * @param x - position x
     * @param y - position y
     * @param width - width
     * @param height - height
     * @param scrollSpeed - scrolling speed
     * @param type - type of fence
     */
    public Fence(float x, float y, int width, int height, float scrollSpeed, int type) {
        super(x, y, width, height, scrollSpeed);
        this.width = width;
        this.height = height;
        this.type = type;
        position = new Vector2(getX(), getY());
        rectangle = new Rectangle();
        r = new Random();
        rectangle.width = width;
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
        if (type==0) setRectangle(new Rectangle(newX, newY, 235, 185));
        else if (type==1) setRectangle(new Rectangle(newX, newY, 66, 183));
        else if (type==2) setRectangle(new Rectangle(newX, newY, 239, 198));
    }

    /**
     * Reset function
     * @param newX - new position x
     */
    @Override
    public void reset(float newX) {
        super.reset(newX);
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
     * @return - return type
     */
    public int getType() {
        return type;
    }

    /**
     * Setter
     * @param type - type
     */
    public void setType(int type) {
        this.type = type;
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
