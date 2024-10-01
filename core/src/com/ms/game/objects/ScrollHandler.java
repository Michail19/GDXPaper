package com.ms.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.ms.game.objects.Background;
import com.ms.game.objects.Ground;
import com.ms.game.objects.Paper;

import java.util.Random;

public class ScrollHandler {
    /**
     * Scroller main class
     *
     * Created by MS
     */
    private Background background;
    private Paper paper;
    private Ground ground, ground1, ground2, ground3;
    public int SCROLL_SPEED = 9;
    public Random r;

    /**
     * Scroll handler constructor
     */
    public ScrollHandler() {
        background = new Background(0, 0, 4167, 4167, SCROLL_SPEED);
        paper = new Paper(2000, 200, 128, 128, SCROLL_SPEED);
        ground = new Ground(0, 100, 512, 128, SCROLL_SPEED, 5);
        ground1 = new Ground(ground.getX()+150, 100, 512, 128, SCROLL_SPEED, 5);
        ground2 = new Ground(ground1.getX()+150, 100, 512, 128, SCROLL_SPEED, 5);
        ground3 = new Ground(ground2.getX()+150, 100, 512, 128, SCROLL_SPEED, 5);
        ground.setL(5);
        ground1.setL(5);
        ground2.setL(5);
        ground3.setL(5);
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
        ground1.updateG(delta);
        ground2.updateG(delta);
        ground3.updateG(delta); //* Update all objects

        if (background.isScrolledLeft()) {
            background.reset(background.getTailX());
            background.setRight(!background.isRight());
        } //* Reset background

        if (ground.isScrolledLeft) {
            ground.setY(r.nextInt(10) + ground3.getY());
            ground.setL(r.nextInt(5) + 1);
            ground.reset(r.nextInt(100) + 1080);
        }
        if (ground1.isScrolledLeft) {
            ground1.setY(r.nextInt(10) + ground.getY());
            ground1.setL(r.nextInt(5) + 1);
            ground1.reset(r.nextInt(100) + 1080);
        }
        if (ground2.isScrolledLeft) {
            ground2.setY(r.nextInt(10) + ground1.getY());
            ground2.setL(r.nextInt(5) + 1);
            ground2.reset(r.nextInt(100) + 1080);
        }
        if (ground3.isScrolledLeft) {
            ground3.setY(r.nextInt(10) + ground2.getY());
            ground3.setL(r.nextInt(5) + 1);
            ground3.reset(r.nextInt(100) + 1080);
        } //* Reset ground

        if (ground.getRectangle().overlaps(ground1.getRectangle())) {
            ground1.setY(r.nextInt(10) + ground.getY());
            ground1.setL(r.nextInt(5) + 1);
            ground1.reset(r.nextInt(100) + ground.getTailX());
        }
        if (ground.getRectangle().overlaps(ground2.getRectangle())) {
            ground2.setY(r.nextInt(10) + ground.getY());
            ground2.setL(r.nextInt(5) + 1);
            ground2.reset(r.nextInt(100) + ground.getTailX());
        }
        if (ground.getRectangle().overlaps(ground3.getRectangle())) {
            ground3.setY(r.nextInt(10) + ground.getY());
            ground3.setL(r.nextInt(5) + 1);
            ground3.reset(r.nextInt(100) + ground.getTailX());
        }
        if (ground1.getRectangle().overlaps(ground2.getRectangle())) {
            ground2.setY(r.nextInt(10) + ground1.getY());
            ground2.setL(r.nextInt(5) + 1);
            ground2.reset(r.nextInt(100) + ground1.getTailX());
        }
        if (ground1.getRectangle().overlaps(ground3.getRectangle())) {
            ground3.setY(r.nextInt(10) + ground1.getY());
            ground3.setL(r.nextInt(5) + 1);
            ground3.reset(r.nextInt(100) + ground1.getTailX());
        }
        if (ground2.getRectangle().overlaps(ground3.getRectangle())) {
            ground3.setY(r.nextInt(10) + ground2.getY());
            ground3.setL(r.nextInt(5) + 1);
            ground3.reset(r.nextInt(100) + ground2.getTailX());
        }

        //System.out.println(ground.getX() + " " + ground1.getX() + " " +  ground2.getX() + " " + ground3.getX());
        //System.out.println(ground.getY() + " " + ground1.getY() + " " +  ground2.getY() + " " + ground3.getY() + "-");
        //System.out.println(ground.l + " " + ground1.l + " " + ground2.l + " " + ground3.l);

        if (paper.isScrolledLeft()) {
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
        else if (ground1.getRectangle().contains(new Vector2(k, ground.getY()))) n = ground.getY() + 130;
        else if (ground2.getRectangle().contains(new Vector2(k, ground.getY()))) n = ground.getY() + 130;
        else if (ground3.getRectangle().contains(new Vector2(k, ground.getY()))) n = ground.getY() + 130;
        else n = -500;
        paper.setY(n);
        paper.reset(k);
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
        ground2.setVelocity(new Vector2(scrollSpeed, 0));
        ground3.setVelocity(new Vector2(scrollSpeed, 0));
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
        ground2.stop();
        ground3.stop();
    }
}
