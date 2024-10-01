package com.ms.game.objects;

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
    private Fence fence;
    public int SCROLL_SPEED = 9;
    public Random r;
    private boolean isFenceStabled = false;

    /**
     * Scroll handler constructor
     */
    public ScrollHandler2() {
        background = new Background(0, 0, 4167, 4167, SCROLL_SPEED);
        paper = new Paper(2000, 200, 128, 128, SCROLL_SPEED);
        ground = new Ground(0, 100, 512, 128, SCROLL_SPEED, 5);
        ground1 = new Ground(ground.getX()+150, 100, 512, 128, SCROLL_SPEED, 5);
        fence = new Fence(-400, -400, 0, 0, SCROLL_SPEED, 0);
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
        fence.update(delta);
        ground.updateG(delta);
        ground1.updateG(delta); //* Update all objects

        if (background.isScrolledLeft()) {
            background.reset(background.getTailX(), background.getY());
            background.setRight(!background.isRight());
        } //* Reset background

        if (ground.isScrolledLeft) {
            ground.setL(r.nextInt(10) + 2);
            ground.reset(r.nextInt(100) + 900, r.nextInt(220) - 100 + ground1.getY());
        }
        if (ground1.isScrolledLeft || ground1.getY() <= 0 || ground1.getY() >= 3000) {
            ground1.setL(r.nextInt(10) + 2);
            ground1.reset(r.nextInt(100) + 900, r.nextInt(220) - 100 + ground.getY());
        }

        if (ground.getRectangle().contains(ground1.getRectangle())) {
            ground1.setL(r.nextInt(10) + 2);
            ground1.reset(r.nextInt(100) + ground.getTailX(), r.nextInt(220) - 100 + ground.getY());
        }

        if (ground.getY() <= 0 || ground.getY() >= 3000) {
            ground.reset(ground.getX(), r.nextInt(220) - 100 + ground1.getY());
        }
        if (ground1.getY() <= 0 || ground1.getY() >= 3000) {
            ground1.reset(ground1.getX(), r.nextInt(220) - 100 + ground.getY());
        } //* Reset ground

        if (!isFenceStabled) {
            if (ground.l > 6) {
                fence.setType(r.nextInt(3));
                float n = r.nextInt(512 * (ground.l-6)) + 1536 + ground.getX();
                if (n - 200 > 255) {
                    fence.reset(n, ground.getY() + 90);
                    isFenceStabled = true;
                }
            }
            else if (ground1.l > 6) {
                fence.setType(r.nextInt(3));
                float n = r.nextInt(512 * (ground1.l-6)) + 1536 + ground1.getX();
                if (n - 200 > 255) {
                    fence.reset(n, ground1.getY() + 90);
                    isFenceStabled = true;
                }
            }
        }

        if (ground.getY() + 90 != fence.getY() && ground1.getY() + 90 != fence.getY()) {
            fence.reset(-500, -500);
            isFenceStabled = false;
        }

        if (fence.isScrolledLeft && isFenceStabled) {
            isFenceStabled = false;
        } //* Reset fence

        if (paper.isScrolledLeft() || paper.getY() < 0) {
            resetPaper();
        }

        if (ground.getY() + 130 != paper.getY() && ground1.getY() + 130 != paper.getY()
                && (ground.getX() < paper.getX() || ground.getX() + 512 * ground.l > paper.getX())
                && (ground1.getX() < paper.getX() || ground1.getX() + 512 * ground1.l > paper.getX())
                || fence.getRectangle().contains(paper.getRectangle())) {
            paper.reset(-500, -500);
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
     * Function for resetting fence
     */
    public void resetFence() {
        fence.reset(0, -500);
    }

    /**
     * Ground
     * @return - returning ground
     */
    public Ground getGround() {
        return ground;
    }

    /**
     * Fence
     * @return - returning ground
     */
    public Fence getFence() {
        return fence;
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
        fence.setVelocity(new Vector2(scrollSpeed, 0));
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
