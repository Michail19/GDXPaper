package com.ms.game.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ms.game.AbstractScreen;
import com.ms.game.save.Prefs;
import com.ms.game.ScreenManager;

public class InformationScreen extends AbstractScreen {
    private Stage stage;
    private String[] chosenLanguage;
    private Prefs prefs;
    private Texture texture, texture1;
    private BitmapFont font24;
    private final int Width = (int) ScreenManager.getInstance().getViewport().getWorldWidth();
    private final int Height = (int) ScreenManager.getInstance().getViewport().getWorldHeight();
    private Music musicB, musicBG;
    private AssetManager assetManager;
    private final String pathBtn = String.valueOf(Gdx.files.internal("btn.mp3")), pathBG = String.valueOf(Gdx.files.internal("text.mp3"));

    /**
     * Information screen constructor
     */
    public InformationScreen() {}

    /**
     * Initialization and creating button
     */
    @Override
    public void show() {
        stage = new Stage();
        prefs = new Prefs();
        font24 = new BitmapFont(Gdx.files.internal("font32S.fnt"));
        texture = new Texture(Gdx.files.internal("Slot.png"));
        if (prefs.getBg() == 0) texture1 = new Texture(Gdx.files.internal("Landscape.png"));
        else texture1 = new Texture(Gdx.files.internal("City.png"));
        setLang(prefs.getLang());
        chosenLanguage = language[getLang()];
        TextureAtlas atlas;
        if (getLang() == 1) atlas = new TextureAtlas("InfEng.pack");
        else atlas = new TextureAtlas("InfRus.pack");

        assetManager = new AssetManager();
        if (!prefs.isVolumeOff()) {
            assetManager.load(pathBtn, Music.class);
        }
        if (!prefs.isMelOff()) {
            assetManager.load(pathBG, Music.class);
        }
        assetManager.finishLoading();

        stage.setViewport(ScreenManager.getInstance().getViewport());
        stage.getRoot().getColor().a = 0;
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));

        Group group = new Group();
        Skin skin = new Skin();
        skin.add("slot", texture);
        texture = new Texture(Gdx.files.internal("ExitS.png"));
        skin.add("button1", texture);
        TextureRegion region1 = atlas.findRegion("S1"), region2 = atlas.findRegion("S2"), region3 = atlas.findRegion("S3");
        Image label1 = new Image(region1);
        Image label2 = new Image(region2);
        Image label3 = new Image(region3);
        if (prefs.getA12() < 1) label1.setVisible(false);
        if (prefs.getA12() < 2) label2.setVisible(false);
        if (prefs.getA12() < 3) label3.setVisible(false);
        label1.setScale(1.5f);
        label2.setScale(1.5f);
        label3.setScale(1.5f);
        label1.setPosition((Width>>1)-430, (Height>>1)+130);
        label2.setPosition((Width>>1)-440, (Height>>1)-160);
        label3.setPosition((Width>>1)-450, (Height>>1)-430);
        group.addActor(label1);
        group.addActor(label2);
        group.addActor(label3);

        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.up = skin.getDrawable("button1");
        ImageButton textButton = new ImageButton(buttonStyle);
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (assetManager.isLoaded(pathBtn)) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                        musicBG.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                //ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.MENU);
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.sequence(
                        Actions.alpha(0.1f, 0.2f)));
                sequenceAction.addAction(run(new Runnable() {
                    @Override
                    public void run() {
                        ScreenManager.getInstance().setScreen(new MenuScreen(3));
                    }
                }));
                stage.getRoot().addAction(sequenceAction);
            }
        });
        textButton.setPosition(10, Height-120);
        group.addActor(textButton);

        stage.addActor(group);
        Gdx.input.setInputProcessor(stage);

        if (assetManager.isLoaded(pathBG)) {
            musicBG = assetManager.get(pathBG, Music.class);
            musicBG.setVolume(1.0f);
            musicBG.play();
        }
    }

    /**
     * Rendering and updating all objects
     * @param delta - delta time
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(texture1, 0, 0);
        if (prefs.getA12()==0) {
            font24.getData().setScale(2f);
            font24.draw(stage.getBatch(), chosenLanguage[13], (Width>>1)-600, Height>>1);
            font24.getData().setScale(1f);
        }
        stage.getBatch().end();
        stage.draw();
        stage.act(delta);
    }

    /**
     * Destructor
     */
    @Override
    public void dispose() {
        try {
            musicBG.stop();
        }
        catch (Exception ignore) {}

        try {
            stage.dispose();
            font24.dispose();
            texture.dispose();
            musicB.dispose();
            musicBG.dispose();
            assetManager.dispose();
        }
        catch (Exception ignore) {}
    }
}
