package com.ms.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    /**
     * Player object
     *
     * Created by MS
     */
    public boolean isGrounded = false;
    public int hp = 5;
    public int jumpForce = 0, startM = 0;
    public int speed = 9, weight = 0;
    private Vector2 position, velocity;
    private int width;
    private int height;
    private Rectangle rectangle, rectangleL;

    /**
     * Player constructor
     * @param x - position x
     * @param y - position y
     * @param width - width
     * @param height - height
     */
    public Player(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        rectangle = new Rectangle();
        rectangleL = new Rectangle();
        rectangle.x = 265;
        rectangle.y = 45;
        rectangle.width = 335;
        rectangle.height = 530;
        rectangleL.x = 265;
        rectangleL.y = 140;
        rectangleL.width = 335;
        rectangleL.height = 5;
    }

    /**
     * Getter
     * @return - return jump force
     */
    public int getJumpForce() {
        return jumpForce;
    }

    /**
     * Getter
     * @return - return status of grounding
     */
    public boolean isGrounded() {
        return isGrounded;
    }

    /**
     * Getter
     * @return - return hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * Getter
     * @return - return weight
     */
    public float getWeight() {
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
     * @return - return rectangle collision
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Getter
     * @return - return rectangle legs collision
     */
    public Rectangle getRectangleL() {
        return rectangleL;
    }

    /**
     * Getter
     * @return - return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter
     * @return - return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter
     * @return - return position x
     */
    public float getX() {
        return position.x;
    }

    /**
     * Getter
     * @return - return position y
     */
    public float getY() {
        return position.y;
    }
}
