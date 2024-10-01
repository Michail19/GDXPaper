package com.ms.game.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ms.game.AbstractScreen;
import com.ms.game.save.Prefs;
import com.ms.game.ScreenManager;

public class MenuScreen extends AbstractScreen {
    /**
     * Menu screen, start screen
     *
     * Created by MS
     */
    private Stage stage;
    private Texture img1, player, top, bottom, texture1, menu1;
    private BitmapFont font24;
    private Prefs prefs;
    private boolean haveOnScreenText = true, isAdd = false;
    private String[] chosenLanguage;
    private final int Width = (int) ScreenManager.getInstance().getViewport().getWorldWidth();
    private final int Height = (int) ScreenManager.getInstance().getViewport().getWorldHeight();
    private int level = 0, n, m; //* level - for button animation, n - for coin position
    private float time = -1, t = 0;
    private double progress = 0; //* progress - for button animation
    private Music musicB, musicBG;
    private AssetManager assetManager;
    private final String pathBtn = String.valueOf(Gdx.files.internal("btn.mp3")), pathBG = String.valueOf(Gdx.files.internal("wait.mp3"));

    /**
     * Menu screen constructor
     */
    public MenuScreen(int m) {
        this.m = m;
    }
    public MenuScreen(float time) {
        this.time = time;
        m = 1;
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
        player = new Texture(Gdx.files.internal("AnimationPlayer.png"));
        texture1 = new Texture(Gdx.files.internal("Coin.png"));
        stage.setViewport(ScreenManager.getInstance().getViewport());
        setLang(prefs.getLang());
        chosenLanguage = language[getLang()];
        if (getLang() == 1) menu1 = new Texture(Gdx.files.internal("EngMenu.png"));
        else menu1 = new Texture(Gdx.files.internal("RusMenu.png"));

        assetManager = new AssetManager();
        assetManager.load(pathBtn, Music.class);
        assetManager.load(pathBG, Music.class);
        assetManager.finishLoading();

        if (prefs.getTop() == 1) top = new Texture(Gdx.files.internal("BlueTs1.png"));
        else if (prefs.getTop() == 2) top = new Texture("Vest1.png");
        else if (prefs.getTop() == 3) top = new Texture("Jamper1.png");
        else if (prefs.getTop() == 4) top = new Texture("PinkTS1.png");
        else if (prefs.getTop() == 5) top = new Texture("WBTS1.png");
        else if (prefs.getTop() == 6) top = new Texture("BJamper1.png");
        else if (prefs.getTop() == 7) top = new Texture("PJamper1.png");
        else if (prefs.getTop() == 8) top = new Texture("Final1.png");

        if (prefs.getDown() == 1) bottom = new Texture("Shorts1.png");
        else if (prefs.getDown() == 2) bottom = new Texture("Trousers1.png");
        else if (prefs.getDown() == 3) bottom = new Texture("Jeans1.png");
        else if (prefs.getDown() == 4) bottom = new Texture("Jeans1E.png");

        Texture img = new Texture(Gdx.files.internal("Play.png"));
        Skin skin = new Skin();
        skin.add("simpleButton", img);
        ImageButton.ImageButtonStyle textButtonStyle = new ImageButton.ImageButtonStyle();
        textButtonStyle.up = skin.getDrawable("simpleButton");
        haveOnScreenText = true;

        final Group group = new Group();
        final ImageButton imgBtn = new ImageButton(textButtonStyle);
        imgBtn.setTransform(true);

        final Group group3 = new Group();
        final Group group1 = new Group();
        Texture texture = new Texture(Gdx.files.internal("ButtonDesign1.png"));
        final Skin skin1 = new Skin();
        skin1.add("simple1Button", texture);
        texture = new Texture(Gdx.files.internal("Shop.png"));
        skin1.add("shopButton", texture);
        texture = new Texture(Gdx.files.internal("offVolume.png"));
        skin1.add("volumeOff", texture);
        texture = new Texture(Gdx.files.internal("onVolume.png"));
        skin1.add("volumeOn", texture);
        texture = new Texture(Gdx.files.internal("Settings.png"));
        skin1.add("settings", texture);

        ImageButton.ImageButtonStyle textButtonStyle1 = new ImageButton.ImageButtonStyle();
        textButtonStyle1.up = skin1.getDrawable("shopButton");
        final ImageButton txtBtn = new ImageButton(textButtonStyle1);
        txtBtn.setTransform(true);

        texture = new Texture(Gdx.files.internal("Computer.png"));
        skin1.add("button", texture);
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.imageUp = skin1.getDrawable("button");
        final ImageButton imageButton = new ImageButton(imageButtonStyle);
        imageButton.setTransform(true);

        texture = new Texture(Gdx.files.internal("Challenge.png"));
        skin1.add("button1", texture);
        ImageButton.ImageButtonStyle imageButtonStyle1 = new ImageButton.ImageButtonStyle();
        imageButtonStyle1.imageUp = skin1.getDrawable("button1");
        final ImageButton imageButton1 = new ImageButton(imageButtonStyle1);
        imageButton1.setTransform(true);

        texture = new Texture(Gdx.files.internal("Factory.png"));
        skin1.add("button2", texture);
        ImageButton.ImageButtonStyle imageButtonStyle2 = new ImageButton.ImageButtonStyle();
        imageButtonStyle2.imageUp = skin1.getDrawable("button2");
        final ImageButton imageButton2 = new ImageButton(imageButtonStyle2);
        imageButton2.setTransform(true);

        final ImageButton.ImageButtonStyle imageButtonStyle3 = new ImageButton.ImageButtonStyle();
        imageButtonStyle3.imageUp = skin1.getDrawable("settings");
        final ImageButton imageButton3 = new ImageButton(imageButtonStyle3);
        imageButton3.setTransform(true);

        imgBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (assetManager.isLoaded(pathBtn) && !prefs.isVolumeOff()) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                        musicBG.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                //setScreenWithFade(new MyGdxGame());
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.sequence(
                        Actions.parallel(
                                Actions.scaleTo(10000f, 10000f, 2f, Interpolation.circleIn),
                                Actions.moveTo(imgBtn.getX() - (Width*505), imgBtn.getY() - (Height*1055), 2f, Interpolation.circleIn))));
                sequenceAction.addAction(run(new Runnable() {
                    @Override
                    public void run() {
                        ScreenManager.getInstance().setScreen(new MyGdxGame());
                    }
                }));
                imgBtn.addAction(sequenceAction);
                txtBtn.setVisible(false);
                imageButton.setVisible(false);
                imageButton1.setVisible(false);
                imageButton2.setVisible(false);
                imageButton3.setVisible(false);
            }
        }); //* Event listener
        imgBtn.setPosition(-98, -48);
        group.addActor(imgBtn);
        group.setPosition(Width >> 1, (Height >> 1) - 50);
        stage.addActor(group);

        txtBtn.addListener(new ClickListener() {
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
                //setScreenWithFade(new ShopScreen());
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.sequence(
                        Actions.parallel(
                                Actions.scaleTo(10000f, 10000f, 2f, Interpolation.circleIn),
                                Actions.moveTo(txtBtn.getX() - (Width*205), txtBtn.getY() - (Height*455), 2f, Interpolation.circleIn))));
                sequenceAction.addAction(run(new Runnable() {
                    @Override
                    public void run() {
                        ScreenManager.getInstance().setScreen(new ShopScreen());
                    }
                }));
                txtBtn.addAction(sequenceAction);
                imageButton.setVisible(false);
                imageButton1.setVisible(false);
                imageButton2.setVisible(false);
                imageButton3.setVisible(false);
            }
        });
        txtBtn.setPosition(-150, -110);
        group1.addActor(txtBtn);
        group1.setPosition(Width - (Width >> 15), Height - (Height >> 5));

        imageButton.addListener(new ClickListener() {
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
                //setScreenWithFade(new InformationScreen());
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.sequence(
                        Actions.parallel(
                                Actions.scaleTo(10000f, 10000f, 2f, Interpolation.circleIn),
                                Actions.moveTo(imageButton.getX() - (Width*270), imageButton.getY() - (Height*515), 2f, Interpolation.circleIn))));
                sequenceAction.addAction(run(new Runnable() {
                    @Override
                    public void run() {
                        ScreenManager.getInstance().setScreen(new InformationScreen());
                    }
                }));
                imageButton.addAction(sequenceAction);
                imageButton1.setVisible(false);
                imageButton2.setVisible(false);
                imageButton3.setVisible(false);
            }
        });
        imageButton.setPosition(-300, -110);
        group1.addActor(imageButton);

        imageButton1.addListener(new ClickListener() {
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
                //setScreenWithFade(new ChallengeScreen());
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.sequence(
                        Actions.parallel(
                                Actions.scaleTo(10000f, 10000f, 2f, Interpolation.circleIn),
                                Actions.moveTo(imageButton1.getX() - (Width*305), imageButton1.getY() - (Height*530), 2f, Interpolation.circleIn))));
                sequenceAction.addAction(run(new Runnable() {
                    @Override
                    public void run() {
                        ScreenManager.getInstance().setScreen(new ChallengeScreen());
                    }
                }));
                imageButton1.addAction(sequenceAction);
                imageButton2.setVisible(false);
                imageButton3.setVisible(false);
            }
        });
        imageButton1.setPosition(-450, -110);
        group1.addActor(imageButton1);
        stage.addActor(group1);

        imageButton2.addListener(new ClickListener() {
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
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.sequence(
                        Actions.parallel(
                                Actions.scaleTo(10000f, 10000f, 2f, Interpolation.circleIn),
                                Actions.moveTo(imageButton2.getX() - (Width*355), imageButton2.getY() - (Height*505), 2f, Interpolation.circleIn))));
                sequenceAction.addAction(run(new Runnable() {
                    @Override
                    public void run() {
                        ScreenManager.getInstance().setScreen(new FactoryScreen());
                    }
                }));
                imageButton2.addAction(sequenceAction);
                imageButton3.setVisible(false);
                //setScreenWithFade(new FactoryScreen());
            }
        });
        imageButton2.setPosition(-600, -110);
        group1.addActor(imageButton2);
        stage.addActor(group1);

        imageButton3.addListener(new ClickListener() {
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

                group3.setVisible(true);
                group.setVisible(false);
                group1.setVisible(false);
                isAdd = true;
            }
        });
        imageButton3.setPosition(-750, -110);
        group1.addActor(imageButton3);
        stage.addActor(group1);

        if (m == 0) {
            stage.getRoot().getColor().a = 0;
            stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
        }
        else if (m == 1) {
            imageButton3.setVisible(false);
            imageButton2.setVisible(false);
            imageButton1.setVisible(false);
            imageButton.setVisible(false);
            txtBtn.setVisible(false);
            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.sequence(
                    Actions.scaleTo(10000f, 10000f),
                    Actions.moveTo(imgBtn.getX() - (Width*505), imgBtn.getY() - (Height*1055)),
                    Actions.parallel(
                            Actions.scaleTo(1f, 1f, 2f, Interpolation.circleOut),
                            Actions.moveTo(imgBtn.getX(), imgBtn.getY(), 2f, Interpolation.circleOut))));
            sequenceAction.addAction(run(new Runnable() {
                @Override
                public void run() {
                    imageButton3.setVisible(true);
                    imageButton2.setVisible(true);
                    imageButton1.setVisible(true);
                    imageButton.setVisible(true);
                    txtBtn.setVisible(true);
                }
            }));
            imgBtn.addAction(sequenceAction);
        }
        else if (m == 2) {
            imageButton3.setVisible(false);
            imageButton2.setVisible(false);
            imageButton1.setVisible(false);
            imageButton.setVisible(false);
            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.sequence(
                    Actions.scaleTo(10000f, 10000f),
                    Actions.moveTo(txtBtn.getX() - (Width*205), txtBtn.getY() - (Height*455)),
                    Actions.parallel(
                            Actions.scaleTo(1f, 1f, 2f, Interpolation.circleOut),
                            Actions.moveTo(txtBtn.getX(), txtBtn.getY(), 2f, Interpolation.circleOut))));
            sequenceAction.addAction(run(new Runnable() {
                @Override
                public void run() {
                    imageButton3.setVisible(true);
                    imageButton2.setVisible(true);
                    imageButton1.setVisible(true);
                    imageButton.setVisible(true);
                }
            }));
            txtBtn.addAction(sequenceAction);
        }
        else if (m == 3) {
            imageButton3.setVisible(false);
            imageButton2.setVisible(false);
            imageButton1.setVisible(false);
            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.sequence(
                    Actions.scaleTo(10000f, 10000f),
                    Actions.moveTo(imageButton.getX() - (Width*270), imageButton.getY() - (Height*515)),
                    Actions.parallel(
                            Actions.scaleTo(1f, 1f, 2f, Interpolation.circleOut),
                            Actions.moveTo(imageButton.getX(), imageButton.getY(), 2f, Interpolation.circleOut))));
            sequenceAction.addAction(run(new Runnable() {
                @Override
                public void run() {
                    imageButton3.setVisible(true);
                    imageButton2.setVisible(true);
                    imageButton1.setVisible(true);
                }
            }));
            imageButton.addAction(sequenceAction);
        }
        else if (m == 4) {
            imageButton3.setVisible(false);
            imageButton2.setVisible(false);
            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.sequence(
                    Actions.scaleTo(10000f, 10000f),
                    Actions.moveTo(imageButton1.getX() - (Width*305), imageButton1.getY() - (Height*530)),
                    Actions.parallel(
                            Actions.scaleTo(1f, 1f, 2f, Interpolation.circleOut),
                            Actions.moveTo(imageButton1.getX(), imageButton1.getY(), 2f, Interpolation.circleOut))));
            sequenceAction.addAction(run(new Runnable() {
                @Override
                public void run() {
                    imageButton3.setVisible(true);
                    imageButton2.setVisible(true);
                }
            }));
            imageButton1.addAction(sequenceAction);
        }
        else if (m == 5) {
            imageButton3.setVisible(false);
            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.sequence(
                    Actions.scaleTo(10000f, 10000f),
                    Actions.moveTo(imageButton2.getX() - (Width*355), imageButton2.getY() - (Height*505)),
                    Actions.parallel(
                            Actions.scaleTo(1f, 1f, 2f, Interpolation.circleOut),
                            Actions.moveTo(imageButton2.getX(), imageButton2.getY(), 2f, Interpolation.circleOut))));
            sequenceAction.addAction(run(new Runnable() {
                @Override
                public void run() {
                    imageButton3.setVisible(true);
                }
            }));
            imageButton2.addAction(sequenceAction);
        }

        final Skin skin3 = new Skin();
        Texture texture3 = new Texture(Gdx.files.internal("Eng.png"));
        skin3.add("eng", texture3);
        texture3 = new Texture(Gdx.files.internal("Rus.png"));
        skin3.add("rus", texture3);
        texture3 = new Texture(Gdx.files.internal("offVolume.png"));
        skin3.add("volumeOff", texture3);
        texture3 = new Texture(Gdx.files.internal("onVolume.png"));
        skin3.add("volumeOn", texture3);
        texture3 = new Texture(Gdx.files.internal("offMel.png"));
        skin3.add("melOff", texture3);
        texture3 = new Texture(Gdx.files.internal("onMel.png"));
        skin3.add("melOn", texture3);
        texture3 = new Texture(Gdx.files.internal("Exit.png"));
        skin3.add("exit", texture3);
        texture3 = new Texture(Gdx.files.internal("HelpEng.png"));
        skin3.add("helpE", texture3);
        texture3 = new Texture(Gdx.files.internal("HelpRus.png"));
        skin3.add("help", texture3);

        final ImageButton.ImageButtonStyle imageButtonStyle6 = new ImageButton.ImageButtonStyle();
        if (prefs.isVolumeOff()) imageButtonStyle6.imageUp = skin3.getDrawable("volumeOff");
        else imageButtonStyle6.imageUp = skin3.getDrawable("volumeOn");
        final ImageButton imageButton6 = new ImageButton(imageButtonStyle6);
        imageButton6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!prefs.isVolumeOff()) imageButtonStyle6.imageUp = skin3.getDrawable("volumeOff");
                else imageButtonStyle6.imageUp = skin3.getDrawable("volumeOn");
                prefs.setVolumeOff();
                if (assetManager.isLoaded(pathBtn) && !prefs.isVolumeOff()) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                imageButton6.setStyle(imageButtonStyle6);
            }
        });
        imageButton6.setPosition((Width>>1)+300, (Height>>1)-30);
        group3.addActor(imageButton6);

        final ImageButton.ImageButtonStyle imageButtonStyle0 = new ImageButton.ImageButtonStyle();
        if (prefs.isMelOff()) imageButtonStyle0.imageUp = skin3.getDrawable("melOff");
        else imageButtonStyle0.imageUp = skin3.getDrawable("melOn");
        final ImageButton imageButton0 = new ImageButton(imageButtonStyle0);
        imageButton0.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!prefs.isMelOff()) {
                    try {
                        if (musicBG.isPlaying()) musicBG.stop();
                    } catch (Exception ignore) {}
                }
                else {
                    if (assetManager.isLoaded(pathBG)) {
                        musicBG = assetManager.get(pathBG, Music.class);
                        musicBG.setLooping(true);
                        musicBG.setVolume(0.5f);
                        musicBG.play();
                    }
                }

                if (assetManager.isLoaded(pathBtn) && !prefs.isVolumeOff()) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                if (!prefs.isMelOff()) imageButtonStyle0.imageUp = skin3.getDrawable("melOff");
                else imageButtonStyle0.imageUp = skin3.getDrawable("melOn");
                prefs.setMelOff();
                imageButton0.setStyle(imageButtonStyle0);
            }
        });
        imageButton0.setPosition((Width>>1)+300, (Height>>1)-200);
        group3.addActor(imageButton0);

        final ImageButton.ImageButtonStyle imageButtonStyle9 = new ImageButton.ImageButtonStyle();
        final ImageButton.ImageButtonStyle textButtonStyle7 = new ImageButton.ImageButtonStyle();
        if (getLang() == 0) textButtonStyle7.up = skin3.getDrawable("rus");
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

                if (getLang() == 0) {
                    setLang(1);
                    prefs.setLang(1);
                    textButtonStyle7.up = skin3.getDrawable("eng");
                    menu1 = new Texture(Gdx.files.internal("EngMenu.png"));
                    imageButtonStyle9.up = skin3.getDrawable("helpE");
                }
                else {
                    setLang(0);
                    prefs.setLang(0);
                    textButtonStyle7.up = skin3.getDrawable("rus");
                    menu1 = new Texture(Gdx.files.internal("RusMenu.png"));
                    imageButtonStyle9.up = skin3.getDrawable("help");
                }
                chosenLanguage = language[getLang()];
                txtBtn7.setStyle(textButtonStyle7);
            }
        });
        txtBtn7.setPosition((Width>>1)+234, (Height>>1)+123);
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
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                group3.setVisible(false);
                group.setVisible(true);
                group1.setVisible(true);
                isAdd = false;
            }
        });
        txtBtn8.setPosition((Width>>1)-408, (Height>>1)+285);
        group3.addActor(txtBtn8);

        if (getLang() == 0) imageButtonStyle9.up = skin3.getDrawable("help");
        else imageButtonStyle9.up = skin3.getDrawable("helpE");
        ImageButton imageButton9 = new ImageButton(imageButtonStyle9);
        imageButton9.addListener(new ClickListener() {
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
            }
        });
        imageButton9.setPosition((Width>>1)-300, (Height>>1)-420);
        group3.addActor(imageButton9);
        group3.setVisible(false);

        group3.setPosition(0 , 0);
        stage.addActor(group3);

        Gdx.input.setInputProcessor(stage);

        n = 0;
        for (int i = 0; i < String.valueOf(prefs.getScore()).length(); i++) {
            n++;
        }

        if (assetManager.isLoaded(pathBG) && !prefs.isMelOff()) {
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
        update(delta);

        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (t>10) {
            stage.getBatch().begin();
            stage.getBatch().draw(img1, 0, -300);
            if (!isAdd) {
                if (haveOnScreenText) {
                    font24.setColor(Color.GOLDENROD);
                    font24.getData().setScale(2f);
                    stage.getBatch().draw(texture1, 5 + 35 * n, Height - 85, 80, 80);
                    font24.draw(stage.getBatch(), String.valueOf(prefs.getScore()), 20, Height - 20);
                    font24.setColor(Color.CYAN);
                    font24.getData().setScale(3f);
                    font24.draw(stage.getBatch(), chosenLanguage[2] + prefs.getRecord(), (Width >> 1) - (Width >> 3) + (Width >> 7) * 2, (Height >> 1) + 1.25f * (Height >> 2));
                }
                font24.getData().setScale(1f);
                stage.getBatch().draw(player, 170, -295);
                if (prefs.getDown() != 0) stage.getBatch().draw(bottom, 450, 180);
                if (prefs.getTop() != 0 && prefs.getTop() != 8)
                    stage.getBatch().draw(top, 445, 365);
                else if (prefs.getTop() == 8) stage.getBatch().draw(top, 87, 105);
            }
            else {
                stage.getBatch().draw(menu1, (Width >> 1) - 600, (Height >> 1) - 444);
            }
            stage.getBatch().end();
            stage.draw();
        }
        else t++;
    }

    /**
     * Fade screen function
     * @param screen - screen
     */
    public void setScreenWithFade(final Screen screen) {
        stage.getRoot().getColor().a = 1.5f;
        haveOnScreenText = false;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(fadeOut(0.5f));
        sequenceAction.addAction(run(new Runnable() {
            @Override
            public void run() {
                ScreenManager.getInstance().setScreen(screen);
            }
        }));
        stage.getRoot().addAction(sequenceAction);
    }

    /**
     * Update function
     * @param dt - delta time
     */
    public void update(float dt) {
        stage.act(dt);

        try {
            if (progress < 2 && level == 0) {
                stage.getActors().get(0).setScale((float) (1f + 0.1f * progress));
                progress += 0.05f;
            } else {
                level = 1;
            }

            if (progress > 0 && level == 1) {
                stage.getActors().get(0).setScale((float) (1f + 0.1f * progress));
                progress -= 0.05f;
            } else {
                level = 0;
            }
        }
        catch (Exception ignore) {}
    }

    /**
     * Destructor
     */
    @Override
    public void dispose() {
        try {
            font24.dispose();
            img1.dispose();
            stage.dispose();
            haveOnScreenText = true;
            musicB.dispose();
            musicBG.dispose();
            assetManager.dispose();
        }
        catch (Exception ignore) {}
    }
}
