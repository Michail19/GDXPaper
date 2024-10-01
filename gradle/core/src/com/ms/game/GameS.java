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
        batch = new SpriteBatch();
        ScreenManager.getInstance().init(this, batch);
        ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.MENU);
    }

    /**
     * Render
     */
    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        getScreen().render(dt);
    }

    /**
     * Destructor
     */
    @Override
    public void dispose() {
        batch.dispose();
    }
}
