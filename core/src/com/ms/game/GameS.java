package com.ms.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameS extends Game {
    /**
     * Local game engine
     *
     * Created by MS
     */
    private SpriteBatch batch;

    /**
     * Constructor
     */
    @Override
    public void create() {
        try {
            batch = new SpriteBatch();
            ScreenManager.getInstance().init(this);
            ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.LOAD);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Render
     */
    @Override
    public void render() {
        try {
            float dt = Gdx.graphics.getDeltaTime();
            getScreen().render(dt);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Destructor
     */
    @Override
    public void dispose() {
        batch.dispose();
    }
}
