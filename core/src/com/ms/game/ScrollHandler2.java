package com.ms.game;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class ScrollHandler2 {
    /**
     * Scroller main class
     *
     * Created by MS
     */
    private Background background;
    private Paper paper;
    private Ground ground, ground1;
    public int SCROLL_SPEED = 9;
    public Random r;

    /**
     * Scroll handler constructor
     */
    public ScrollHandler2() {
        background = new Background(0, 0, 4167, 4167, SCROLL_SPEED);
        paper = new Paper(2000, 200, 128, 128, SCROLL_SPEED);
        ground = new Ground(0, 100, 512, 128, SCROLL_SPEED, 5);
        ground1 = new Ground(ground.getX()+150, 100, 512, 128, SCROLL_SPEED, 5);
        ground.setL(5);
        ground1.setL(5);
        r = new Random();
    }

    /**
     * Updating method
     * @param delta - delta time
     */
    public void update(float delta) {
        background.update(delta);
        paper.update(delta);
        ground.updateG(delta);
        ground1.updateG(delta); //* Update all objects

        if (background.isScrolledLeft()) {
            background.reset(background.getTailX(), background.getY());
            background.setRight(!background.isRight());
        } //* Reset background

        if (ground.isScrolledLeft) {
            ground.setL(r.nextInt(5) + 1);
            ground.reset(r.nextInt(100) + 900, r.nextInt(220) - 100 + ground1.getY());
        }
        if (ground1.isScrolledLeft || ground1.getY() <= 0 || ground1.getY() >= 3000) {
            ground1.setL(r.nextInt(5) + 1);
            ground1.reset(r.nextInt(100) + 900, r.nextInt(220) - 100 + ground.getY());
        } //* Reset ground

        if (ground.getRectangle().overlaps(ground1.getRectangle())) {
            ground1.setL(r.nextInt(5) + 1);
            ground1.reset(r.nextInt(100) + ground.getTailX(), r.nextInt(220) - 100 + ground.getY());
        }

        if (ground.getY() <= 0 || ground.getY() >= 3000) {
            ground.reset(ground.getX(), r.nextInt(220) - 100 + ground1.getY());
        }
        if (ground1.getY() <= 0 || ground1.getY() >= 3000) {
            ground1.reset(ground1.getX(), r.nextInt(220) - 100 + ground.getY());
        }

        if (paper.isScrolledLeft() || paper.getY() < 0
                || ((paper.getY() - 130 >= ground.getY() && paper.getX() - 66 >= ground.getX() && paper.getY() - 124 <= ground.getY() && paper.getX() - 62 <= ground.getX())
                && (paper.getY() - 130 >= ground1.getY() && paper.getX() - 66 >= ground1.getX() && paper.getY() - 124 <= ground1.getY() && paper.getX() - 62 <= ground1.getX()))) {
            resetPaper();
        } //* Reset paper
    }

    /**
     * Function for resetting paper
     */
    public void resetPaper() {
        int k = r.nextInt(900) + 1020;
        float n;
        if (ground.getRectangle().contains(new Vector2(k, ground.getY()))) n = ground.getY() + 130;
        else if (ground1.getRectangle().contains(new Vector2(k, ground1.getY()))) n = ground1.getY() + 130;
        else n = -500;
        paper.reset(k, n);
    }

    /**
     * Ground
     * @return - returning ground
     */
    public Ground getGround() {
        return ground;
    }

    /**
     * Background
     * @return - returning background
     */
    public Background getBackground() {
        return background;
    }

    /**
     * Paper
     * @return - returning paper
     */
    public Paper getPaper() {
        return paper;
    }

    /**
     * Scrolling speed
     */
    public void setScrollSpeed(int scrollSpeed) {
        background.setVelocity(new Vector2(scrollSpeed, 0));
        ground.setVelocity(new Vector2(scrollSpeed, 0));
        ground1.setVelocity(new Vector2(scrollSpeed, 0));
        paper.setVelocity(new Vector2(scrollSpeed, 0));
    }

    /**
     * Function for stopping scrolling
     */
    public void stop() {
        background.stop();
        paper.stop();
        ground.stop();
        ground1.stop();
    }
}
