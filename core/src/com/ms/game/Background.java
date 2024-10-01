package com.ms.game;

public class Background extends Scrollable {
    /**
     * Background object
     *
     * Created by MS
     */
    public boolean isRight = false;

    /**
     * Side for texture
     * @return - return side
     */
    public boolean isRight() {
        return isRight;
    }

    /**
     * Checking for reverse texture
     * @param right - last side
     */
    public void setRight(boolean right) {
        isRight = right;
    }

    /**
     * Background constructor
     * @param x - position x
     * @param y - position y
     * @param width - width
     * @param height - height
     * @param scrollSpeed - scrolling speed
     */
    public Background(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }
}
