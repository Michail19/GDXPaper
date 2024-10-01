package com.ms.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LoadingScreen extends AbstractScreen {
    /**
     * Loading screen
     *
     * Created by MS
     */
    private Stage stage;
    private BitmapFont font24;
    private Texture texture;
    private TextureAtlas textureAtlas;
    private final int Width = Gdx.graphics.getWidth(), Height = Gdx.graphics.getHeight();
    private double progress = 1;
    private int tWidth = 128, tHeight = 128, t1 = 0, t2 = 0;
    private boolean isDestroyed = false;
    private TextureRegion textureRegion;

    /**
     * Loading screen constructor
     */
    public LoadingScreen() {}

    /**
     * Initialization
     */
    @Override
    public void show() {
        stage = new Stage();
        font24 = new BitmapFont(Gdx.files.internal("font32S.fnt"));
        textureAtlas = new TextureAtlas("Loading.pack");
        texture = new Texture(Gdx.files.internal("splash.png"));
        stage.getCamera().viewportWidth = Width;
        stage.getCamera().viewportHeight = Height;
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {}
                isDestroyed = true;
            }
        };
        thread.start();
    }

    /**
     * Rendering and updating all objects
     * @param delta - delta time
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.getBatch().begin();
        stage.getBatch().draw(texture, (Width>>1)-100, (Height>>1)+280);
        if (progress<13) {
            textureRegion = textureAtlas.findRegion("Load" + (int) progress);
            progress+=0.1;
            stage.getBatch().draw(textureRegion, (Width>>1)-64, (Height>>1)-180);
        }
        else {
            textureRegion = textureAtlas.findRegion("LoadX");
            tWidth+=50;
            tHeight+=50;
            t1+=25;
            t2+=25;
            stage.getBatch().draw(textureRegion, (Width>>1)-64-t1, (Height>>1)-180-t2, tWidth, tHeight);
        }
        font24.getData().setScale(7f);
        font24.setColor(Color.GREEN);
        font24.draw(stage.getBatch(), "Paper project", (Width>>1)-620, (Height>>1)+260);
        font24.getData().setScale(1f);
        font24.setColor(Color.CYAN);
        font24.draw(stage.getBatch(), " Created by: \n Kurbashkin Nikita \n Ershov Michail \n Shishakova Elizaveta \n Mayorova Maria", Width-300, 200);
        font24.setColor(Color.WHITE);
        font24.draw(stage.getBatch(), " Version 1.3.2", 10, 40);
        stage.getBatch().end();
        stage.draw();
        if (isDestroyed) ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.MENU);
    }

    /**
     * Destructor
     */
    @Override
    public void dispose() {
        try {
            stage.dispose();
            font24.dispose();
            texture.dispose();
        }
        catch (Exception ignore) {}
    }
}
