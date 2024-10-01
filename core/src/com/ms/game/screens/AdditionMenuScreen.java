package com.ms.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ms.game.AbstractScreen;
import com.ms.game.ScreenManager;
import com.ms.game.save.Prefs;

public class AdditionMenuScreen extends AbstractScreen {
    /**
     * Addition menu screen
     *
     * Created by MS
     */
    private Stage stage;
    private Texture img1, menu;
    private BitmapFont font24;
    private Prefs prefs;
    private final int Width = (int) ScreenManager.getInstance().getViewport().getWorldWidth();
    private final int Height = (int) ScreenManager.getInstance().getViewport().getWorldHeight();
    private Music musicB, musicBG;
    private AssetManager assetManager;
    private final float time;
    private final String pathBtn = String.valueOf(Gdx.files.internal("btn.mp3")), pathBG = String.valueOf(Gdx.files.internal("wait.mp3"));


    /**
     * Addition menu screen constructor
     */
    public AdditionMenuScreen(float time) {
        this.time = time;
    }

    /**
     * Initialization and creating button
     */
    @Override
    public void show() {
        stage = new Stage();
        font24 = new BitmapFont(Gdx.files.internal("font32S.fnt"));
        prefs = new Prefs();
        if (prefs.getBg() == 0) img1 = new Texture(Gdx.files.internal("StartScreen.png"));
        else img1 = new Texture(Gdx.files.internal("StartScreen1.png"));
        stage.setViewport(ScreenManager.getInstance().getViewport());

        assetManager = new AssetManager();
        assetManager.load(pathBtn, Music.class);
        assetManager.load(pathBG, Music.class);
        assetManager.finishLoading();

        setLang(prefs.getLang());

        if (getLang() == 0) {
            menu = new Texture(Gdx.files.internal("EngMenu.png"));
        }
        else {
            menu = new Texture(Gdx.files.internal("RusMenu.png"));
        }

        Group group3 = new Group();
        final Skin skin3 = new Skin();
        Texture texture3 = new Texture(Gdx.files.internal("Eng.png"));
        skin3.add("eng", texture3);
        texture3 = new Texture(Gdx.files.internal("Rus.png"));
        skin3.add("rus", texture3);
        texture3 = new Texture(Gdx.files.internal("offVolume.png"));
        skin3.add("volumeOff", texture3);
        texture3 = new Texture(Gdx.files.internal("onVolume.png"));
        skin3.add("volumeOn", texture3);
        texture3 = new Texture(Gdx.files.internal("Exit.png"));
        skin3.add("exit", texture3);

        final ImageButton.ImageButtonStyle imageButtonStyle6 = new ImageButton.ImageButtonStyle();
        if (prefs.isVolumeOff()) imageButtonStyle6.imageUp = skin3.getDrawable("volumeOff");
        else imageButtonStyle6.imageUp = skin3.getDrawable("volumeOn");
        final ImageButton imageButton6 = new ImageButton(imageButtonStyle6);
        imageButton6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    if (musicBG.isPlaying() && !prefs.isVolumeOff()) musicBG.stop();
                    else {
                        if (assetManager.isLoaded(pathBG)) {
                            musicBG = assetManager.get(pathBG, Music.class);
                            musicBG.setLooping(true);
                            musicBG.setVolume(0.5f);
                            musicBG.play();
                        }
                    }
                } catch (Exception ignore) {}

                if (assetManager.isLoaded(pathBtn) && !prefs.isVolumeOff()) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                if (!prefs.isVolumeOff()) imageButtonStyle6.imageUp = skin3.getDrawable("volumeOff");
                else imageButtonStyle6.imageUp = skin3.getDrawable("volumeOn");
                prefs.setVolumeOff();
                imageButton6.setStyle(imageButtonStyle6);
            }
        });
        imageButton6.setPosition((Width>>1)+250, (Height>>1)-100);
        group3.addActor(imageButton6);

        final ImageButton.ImageButtonStyle textButtonStyle7 = new ImageButton.ImageButtonStyle();
        if (getLang() == 1) textButtonStyle7.up = skin3.getDrawable("rus");
        else textButtonStyle7.up = skin3.getDrawable("eng");
        final ImageButton txtBtn7 = new ImageButton(textButtonStyle7);
        txtBtn7.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (assetManager.isLoaded(pathBtn) && !prefs.isVolumeOff()) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }

                if (getLang() == 1) {
                    setLang(0);
                    prefs.setLang(0);
                    textButtonStyle7.up = skin3.getDrawable("eng");
                    menu = new Texture(Gdx.files.internal("EngMenu.png"));
                }
                else {
                    setLang(1);
                    prefs.setLang(1);
                    textButtonStyle7.up = skin3.getDrawable("rus");
                    menu = new Texture(Gdx.files.internal("RusMenu.png"));
                }
                txtBtn7.setStyle(textButtonStyle7);
            }
        });
        txtBtn7.setPosition((Width>>1)+200, (Height>>1)+85);
        group3.addActor(txtBtn7);

        final ImageButton.ImageButtonStyle textButtonStyle8 = new ImageButton.ImageButtonStyle();
        textButtonStyle8.up = skin3.getDrawable("exit");
        final ImageButton txtBtn8 = new ImageButton(textButtonStyle8);
        txtBtn8.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (assetManager.isLoaded(pathBtn) && !prefs.isVolumeOff()) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                        musicBG.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                ScreenManager.getInstance().setScreen(new MenuScreen(0));
            }
        });
        txtBtn8.setPosition((Width>>1)-200, (Height>>1)+285);
        group3.addActor(txtBtn8);

        group3.setPosition(0 , 0);
        stage.addActor(group3);
        Gdx.input.setInputProcessor(stage);

        if (assetManager.isLoaded(pathBG) && !prefs.isVolumeOff()) {
            musicBG = assetManager.get(pathBG, Music.class);
            musicBG.setLooping(true);
            musicBG.setVolume(0.5f);
            musicBG.setPosition(time+1);
            musicBG.play();
        }
    }

    /**
     * Rendering all objects
     * @param delta - delta time
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);

        stage.getBatch().begin();
        stage.getBatch().draw(img1, 0, -300);
        stage.getBatch().draw(menu, (Width>>1)-500, (Height>>1)-370);
        stage.getBatch().end();

        stage.draw();
    }

    /**
     * Update function
     */
    @Override
    public void dispose() {
        try {
            font24.dispose();
            img1.dispose();
            stage.dispose();
            musicB.dispose();
            musicBG.dispose();
            assetManager.dispose();
        }
        catch (Exception ignore) {}
    }
}
