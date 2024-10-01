package com.ms.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ms.game.AbstractScreen;
import com.ms.game.ScreenManager;

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
    private final int Width = (int) ScreenManager.getInstance().getViewport().getWorldWidth();
    private final int Height = (int) ScreenManager.getInstance().getViewport().getWorldHeight();
    private double progress = 1;
    private int tWidth = 128, tHeight = 128, t1 = 0, t2 = 0;
    private boolean isDestroyed = false, c = false;
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
        texture = new Texture(Gdx.files.internal("WEEARTHON.png"));
        Texture a1 = new Texture(Gdx.files.internal("We.png"));
        Texture a2 = new Texture(Gdx.files.internal("Earth.png"));
        Texture a3 = new Texture(Gdx.files.internal("On.png"));
        Image n1 = new Image(a1);
        Image n2 = new Image(a2);
        Image n3 = new Image(a3);
        n1.setPosition((Width>>1)+100, (Height>>1)+50);
        n1.addAction(
                Actions.sequence(
                        Actions.alpha(0f),
                        Actions.parallel(
                                Actions.scaleTo(1.2f, 1.2f, 0.9f),
                                Actions.moveTo(n1.getX() - 595f, n1.getY() + 120f, 1.2f),
                                Actions.alpha(1f, 0.9f, Interpolation.circleIn))));
        n3.setPosition((Width>>1)-100, (Height>>1)-50);
        n3.addAction(
                Actions.sequence(
                        Actions.alpha(0f),
                        Actions.parallel(
                                Actions.scaleTo(1.2f, 1.2f, 0.9f),
                                Actions.moveTo(n3.getX() + 405f, n3.getY() + 220f, 1.2f),
                                Actions.alpha(1f, 0.9f, Interpolation.circleIn))));
        n2.setPosition((Width>>1)-233, (Height>>1));
        n2.addAction(
                Actions.sequence(
                        Actions.alpha(0f),
                        Actions.moveTo(n2.getX(), n2.getY(), 0.6f),
                        Actions.parallel(
                                Actions.scaleTo(1.2f, 1.2f, 0.9f),
                                Actions.moveTo(n2.getX(), n2.getY() + 170f, 0.8f),
                                Actions.alpha(1f, 0.9f, Interpolation.circleIn))));
        stage.addActor(n1);
        stage.addActor(n2);
        stage.addActor(n3);
        stage.setViewport(ScreenManager.getInstance().getViewport());
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    c = true;
                    Thread.sleep(2000);
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
        //stage.getBatch().draw(texture, (Width>>1)-100, (Height>>1)+280);
        if (c) {
            if (progress < 13) {
                textureRegion = textureAtlas.findRegion("Load" + (int) progress);
                progress += 0.2;
                stage.getBatch().draw(textureRegion, (Width >> 1) - 64, (Height >> 1) - 180);
            } else {
                textureRegion = textureAtlas.findRegion("LoadX");
                tWidth += 50;
                tHeight += 50;
                t1 += 25;
                t2 += 25;
                stage.getBatch().draw(textureRegion, (Width >> 1) - 64 - t1, (Height >> 1) - 180 - t2, tWidth, tHeight);
            }
        }
//        font24.getData().setScale(7f);
//        font24.setColor(Color.GREEN);
//        font24.draw(stage.getBatch(), "WeEARTHoN", (Width>>1)-620, (Height>>1)+260);
//        stage.getBatch().draw(texture, (Width>>1)-600, (Height>>1)+280);
        font24.getData().setScale(1f);
        font24.setColor(Color.CYAN);
        font24.draw(stage.getBatch(), " Created by: \n Kurbashkin Nikita \n Ershov Michail \n Shishakova Elizaveta \n Mayorova Maria", Width-300, 200);
        font24.setColor(Color.WHITE);
        font24.draw(stage.getBatch(), " Version 1.3.7", 10, 40);
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
