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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ms.game.AbstractScreen;
import com.ms.game.save.Prefs;
import com.ms.game.ScreenManager;

public class FactoryScreen extends AbstractScreen {
    /**
     * Factory screen
     *
     * Created by MS
     */
    private Stage stage;
    private BitmapFont font24;
    private Prefs prefs;
    private TextureAtlas atlas, tAtlas;
    private TextureRegion region, tRegion;
    private String[] chosenLanguage;
    private Texture bg, st, p1, p2, texture1, texture2;
    private boolean isS = true, isR = false, isPlay = false, after = false, isNext = false, isE = false;
                //* isS - zero scene, isR - have on scene unprocessed paper, isPlay - animation recycling, after - after animation, isNext - for button continue, isE - end animation
    private final int Width = (int) ScreenManager.getInstance().getViewport().getWorldWidth();
    private final int Height = (int) ScreenManager.getInstance().getViewport().getWorldHeight();
    private Label label;
    private int stPlay = 0, anim = 0, n = 0, n2 = 0, n1 = 1, fst, addScr = 0;
    private ImageButton textButton;
    private TextButton textButton1;
    private Music music, music1;
    private final String pathBtn = String.valueOf(Gdx.files.internal("btn.mp3")), path1 = String.valueOf(Gdx.files.internal("1.mp3")),
            path2 = String.valueOf(Gdx.files.internal("2.mp3")), path3 = String.valueOf(Gdx.files.internal("3.mp3")),
            coin = String.valueOf(Gdx.files.internal("getCoin.mp3")), field = String.valueOf(Gdx.files.internal("field.mp3")),
            build = String.valueOf(Gdx.files.internal("build.mp3")), empty = String.valueOf(Gdx.files.internal("factoryEmpty.mp3"));
    private AssetManager assetManager;

    /**
     * Menu screen constructor
     */
    public FactoryScreen() {}

    /**
     * Initialization
     */
    @Override
    public void show() {
        stage = new Stage();
        font24 = new BitmapFont(Gdx.files.internal("font32S.fnt"));
        prefs = new Prefs();
        setLang(prefs.getLang());
        chosenLanguage = language[getLang()];
        atlas = new TextureAtlas("AnimationFactory.pack");
        if (getLang() == 1) tAtlas = new TextureAtlas("TableFactoryE.pack");
        else tAtlas = new TextureAtlas("TableFactory.pack");
        region = atlas.findRegion("St11");
        tRegion = tAtlas.findRegion("Title");
        texture1 = new Texture(Gdx.files.internal("Coin.png"));
        texture2 = new Texture(Gdx.files.internal("CrumplesPaperMin.png"));
        bg = new Texture(Gdx.files.internal("dirt.png"));
        p1 = new Texture(Gdx.files.internal("UnprocessedPaper.png"));
        p2 = new Texture(Gdx.files.internal("ProcessedPaper.png"));
        fst = prefs.getStageFactory();
        assetManager = new AssetManager();
        if (!prefs.isVolumeOff()) {
            assetManager.load(path1, Music.class);
            assetManager.load(path2, Music.class);
            assetManager.load(path3, Music.class);
            assetManager.load(coin, Music.class);
            assetManager.load(pathBtn, Music.class);
        }
        if (!prefs.isMelOff()) {
            assetManager.load(empty, Music.class);
            assetManager.load(field, Music.class);
            assetManager.load(build, Music.class);
        }
        assetManager.finishLoading();

        stage.setViewport(ScreenManager.getInstance().getViewport());
        stage.getRoot().getColor().a = 0;
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));

        final Group group = new Group();
        final Skin skin = new Skin();
        Texture texture = new Texture(Gdx.files.internal("ButtonDesign1.png"));
        skin.add("button1", texture);
        texture = new Texture(Gdx.files.internal("ExitS.png"));
        skin.add("exit", texture);
        texture = new Texture(Gdx.files.internal("ButtonDesign3U.png"));
        skin.add("simple2Button", texture);
        texture = new Texture(Gdx.files.internal("nextbtn.png"));
        skin.add("nextbtn", texture);
        final TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.down = skin.getDrawable("button1");
        buttonStyle.up = skin.getDrawable("button1");
        buttonStyle.font = font24;
        buttonStyle.fontColor = Color.FOREST;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font24;
        labelStyle.fontColor = Color.CYAN;
        labelStyle.background = skin.getDrawable("simple2Button");
        label = new Label(chosenLanguage[14], labelStyle);
        label.setVisible(false);
        label.setAlignment(Align.center);
        label.setPosition(stage.getCamera().position.x, Height>>1);

        ImageButton.ImageButtonStyle buttonStyle1 = new ImageButton.ImageButtonStyle();
        buttonStyle1.up = skin.getDrawable("exit");
        textButton = new ImageButton(buttonStyle1);
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (assetManager.isLoaded(pathBtn)) {
                    try {
                        if (music1.isPlaying()) music1.stop();
                        music.stop();
                    } catch (Exception ignore) {}
                    music1 = assetManager.get(pathBtn, Music.class);
                    music1.setVolume(1.0f);
                    music1.play();
                }
                if (!isPlay) {
                    //ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.MENU);
                    SequenceAction sequenceAction = new SequenceAction();
                    sequenceAction.addAction(Actions.sequence(
                            Actions.alpha(0.1f, 0.2f)));
                    sequenceAction.addAction(run(new Runnable() {
                        @Override
                        public void run() {
                            ScreenManager.getInstance().setScreen(new MenuScreen(5));
                        }
                    }));
                    stage.getRoot().addAction(sequenceAction);
                }
            }
        });
        textButton.setPosition(10, Height-110);
        group.addActor(textButton);

        if (fst == 0) {
            if (assetManager.isLoaded(field)) {
                music = assetManager.get(field, Music.class);
                music.setLooping(true);
                music.setVolume(0.8f);
                music.play();
            }
            isS = false;
        }
        else if (fst == 1) {
            if (assetManager.isLoaded(build)) {
                music = assetManager.get(build, Music.class);
                music.setLooping(true);
                music.setVolume(0.8f);
                music.play();
            }
            st = new Texture(Gdx.files.internal("FScene.png"));
        }
        else if (fst == 2) {
            if (assetManager.isLoaded(build)) {
                music = assetManager.get(build, Music.class);
                music.setLooping(true);
                music.setVolume(0.8f);
                music.play();
            }
            st = new Texture(Gdx.files.internal("SScene.png"));
        }
        else if (fst == 3) {
                if (assetManager.isLoaded(build)) {
                    music = assetManager.get(build, Music.class);
                    music.setLooping(true);
                    music.setVolume(0.8f);
                    music.play();
                }
            st = new Texture(Gdx.files.internal("TScene.png"));
            }
        else if (fst == 4) {
            if (assetManager.isLoaded(empty)) {
                music = assetManager.get(empty, Music.class);
                music.setLooping(true);
                music.setVolume(1.0f);
                music.play();
            }
            st = new Texture(Gdx.files.internal("FinalFactory.png"));
            if (prefs.getForRecycled() > 0) isR = true;
        }

        if (fst == 4) {
            textButton1 = new TextButton(chosenLanguage[21], buttonStyle);
            textButton1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!isPlay) {
                        if (assetManager.isLoaded(pathBtn)) {
                            try {
                                if (music1.isPlaying()) music1.stop();
                            } catch (Exception ignore) {}
                            music1 = assetManager.get(pathBtn, Music.class);
                            music1.setVolume(1.0f);
                            music1.play();
                        }
                        isPlay = true;
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
                                    imageButtonStyle.imageUp = skin.getDrawable("nextbtn");
                                    final ImageButton imageButton = new ImageButton(imageButtonStyle);
                                    imageButton.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            if (isNext) {
                                                if (assetManager.isLoaded(pathBtn)) {
                                                    try {
                                                        if (music1.isPlaying()) music1.stop();
                                                    } catch (Exception ignore) {}
                                                    music1 = assetManager.get(pathBtn, Music.class);
                                                    music1.setVolume(1.0f);
                                                    music1.play();
                                                }
                                                stPlay++;
                                            }
                                            if (stPlay==2) {
                                                if (assetManager.isLoaded(path2)) {
                                                    music.stop();
                                                    music = assetManager.get(path2, Music.class);
                                                    music.setLooping(true);
                                                    music.setVolume(0.5f);
                                                    music.play();
                                                }
                                            }
                                            else if (stPlay==3) {
                                                if (assetManager.isLoaded(path3)) {
                                                    music.stop();
                                                    music = assetManager.get(path3, Music.class);
                                                    music.setLooping(true);
                                                    music.setVolume(0.5f);
                                                    music.play();
                                                }
                                            }
                                            else if (stPlay==4) {
                                                if (assetManager.isLoaded(empty)) {
                                                    music.stop();
                                                    music = assetManager.get(empty, Music.class);
                                                    music.setLooping(true);
                                                    music.setVolume(1.0f);
                                                    music.play();
                                                }
                                            }

                                            imageButton.setVisible(false);
                                            Thread thread1 = new Thread() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        if (stPlay==5) {
                                                            isE = true;
                                                            return;
                                                        }
                                                        Thread.sleep(5000);
                                                        isNext = true;
                                                        imageButton.setVisible(true);
                                                    }
                                                    catch (InterruptedException ignore) {}
                                                }
                                            };
                                            if (isNext) thread1.start();
                                            isNext = false;
                                        }
                                    });
                                    imageButton.setPosition(Width - 300, 8);
                                    group.addActor(imageButton);
                                    textButton.setVisible(false);
                                    textButton1.setVisible(false);
                                    imageButton.setVisible(false);
                                    Thread.sleep(3000);
                                    if (assetManager.isLoaded(path1)) {
                                        music.stop();
                                        music = assetManager.get(path1, Music.class);
                                        music.setLooping(true);
                                        music.setVolume(0.5f);
                                        music.play();
                                    }
                                    stPlay = 1;
                                    isNext = true;
                                    Thread.sleep(5000);
                                    imageButton.setVisible(true);
                                    while (!isE) {
                                        Thread.sleep(1);
                                    }

//                                    Thread.sleep(3000);
//                                    stPlay = 1;
//                                    Thread.sleep(15000);
//                                    stPlay = 2;
//                                    Thread.sleep(15000);
//                                    stPlay = 3;
//                                    Thread.sleep(15000);
//                                    stPlay = 4;
//                                    Thread.sleep(15000);

                                    if (assetManager.isLoaded(coin)) {
                                        try {
                                            if (music1.isPlaying()) music1.stop();
                                        } catch (Exception ignore) {}
                                        music1 = assetManager.get(coin, Music.class);
                                        music1.setVolume(0.8f);
                                        music1.play();
                                    }
                                    imageButton.setVisible(false);
                                    stPlay = 0;
                                    isPlay = false;
                                    after = true;
                                    isR = false;
                                    isNext = false;
                                    isE = false;
                                    textButton.setVisible(true);
                                    prefs.setRecycledPaper(prefs.getForRecycled());
                                    addScr = (int) Math.ceil(prefs.getForRecycled() * 0.5);
                                    prefs.setScore(addScr);
                                    prefs.setForRecycledToZero();
                                    for (int i = 0; i < String.valueOf(addScr).length(); i++) {
                                        n1++;
                                    }
                                    n2 = 1;
                                    n = 0;
                                    for (int i = 0; i < String.valueOf(prefs.getScore()).length(); i++) {
                                        n++;
                                    }
                                }
                                catch (InterruptedException ignored) {}
                            }
                        };
                        thread.start();
                    }
                }
            });
            textButton1.setPosition((Width >> 1) - 80, Height - 110);
            if (isR) group.addActor(textButton1);
        }
        else {
            textButton1 = new TextButton("chosenLanguage[20]      6", buttonStyle);
            if (fst == 0) textButton1.setText(chosenLanguage[20] + 20 + "   ");
            if (fst == 1) textButton1.setText(chosenLanguage[20] + 50 + "   ");
            if (fst == 2) textButton1.setText(chosenLanguage[20] + 80 + "   ");
            if (fst == 3) textButton1.setText(chosenLanguage[20] + 100 + "   ");
            textButton1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (music1.isPlaying()) music1.stop();
                        } catch (Exception ignore) {}
                        music1 = assetManager.get(pathBtn, Music.class);
                        music1.setVolume(1.0f);
                        music1.play();
                    }
                    if (prefs.getScore() >= 20 && fst==0) {
                        if (assetManager.isLoaded(build)) {
                            music = assetManager.get(build, Music.class);
                            music.setLooping(true);
                            music.setVolume(0.8f);
                            music.play();
                        }
                        prefs.setScore(-20);
                        prefs.setStageFactory(1);
                        label.setText(chosenLanguage[14]);
                        textButton1.setText(chosenLanguage[20] + 50 + "   ");
                        isS = true;
                        fst = 1;
                        st = new Texture(Gdx.files.internal("FScene.png"));
                    }
                    else if (prefs.getScore() >= 50 && fst==1) {
                        prefs.setScore(-50);
                        prefs.setStageFactory(2);
                        label.setText(chosenLanguage[14]);
                        textButton1.setText(chosenLanguage[20] + 80 + "   ");
                        fst = 2;
                        st = new Texture(Gdx.files.internal("SScene.png"));
                    }
                    else if (prefs.getScore() >= 80 && fst==2) {
                        prefs.setScore(-80);
                        prefs.setStageFactory(3);
                        label.setText(chosenLanguage[14]);
                        textButton1.setText(chosenLanguage[20] + 100 + "   ");
                        fst = 3;
                        st = new Texture(Gdx.files.internal("TScene.png"));
                    }
                    else if (prefs.getScore() >= 100 && fst==3) {
                        if (assetManager.isLoaded(empty)) {
                            music = assetManager.get(empty, Music.class);
                            music.setLooping(true);
                            music.setVolume(1.0f);
                            music.play();
                        }
                        prefs.setScore(-100);
                        prefs.setStageFactory(4);
                        label.setText(chosenLanguage[14]);
                        st = new Texture(Gdx.files.internal("FinalFactory.png"));
                        fst = 4;
                        if (prefs.getForRecycled() > 0) isR = true;
                        textButton1.setText(chosenLanguage[21]);
                        textButton1.clearListeners();
                        textButton1.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                if (!isPlay) {
                                    if (assetManager.isLoaded(pathBtn)) {
                                        try {
                                            if (music1.isPlaying()) music1.stop();
                                        } catch (Exception ignore) {}
                                        music1 = assetManager.get(pathBtn, Music.class);
                                        music1.setVolume(1.0f);
                                        music1.play();
                                    }
                                    isPlay = true;
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            try {
                                                ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
                                                imageButtonStyle.imageUp = skin.getDrawable("nextbtn");
                                                final ImageButton imageButton = new ImageButton(imageButtonStyle);
                                                imageButton.addListener(new ClickListener() {
                                                    @Override
                                                    public void clicked(InputEvent event, float x, float y) {
                                                        if (isNext) {
                                                            if (assetManager.isLoaded(pathBtn)) {
                                                                try {
                                                                    if (music1.isPlaying()) music1.stop();
                                                                } catch (Exception ignore) {}
                                                                music1 = assetManager.get(pathBtn, Music.class);
                                                                music1.setVolume(1.0f);
                                                                music1.play();
                                                            }
                                                            stPlay++;
                                                        }
                                                        if (stPlay==2) {
                                                            if (assetManager.isLoaded(path2)) {
                                                                music.stop();
                                                                music = assetManager.get(path2, Music.class);
                                                                music.setLooping(true);
                                                                music.setVolume(0.5f);
                                                                music.play();
                                                            }
                                                        }
                                                        else if (stPlay==3) {
                                                            if (assetManager.isLoaded(path3)) {
                                                                music.stop();
                                                                music = assetManager.get(path3, Music.class);
                                                                music.setLooping(true);
                                                                music.setVolume(0.5f);
                                                                music.play();
                                                            }
                                                        }
                                                        else if (stPlay==4) {
                                                            if (assetManager.isLoaded(empty)) {
                                                                music.stop();
                                                                music = assetManager.get(empty, Music.class);
                                                                music.setLooping(true);
                                                                music.setVolume(1.0f);
                                                                music.play();
                                                            }
                                                        }

                                                        imageButton.setVisible(false);
                                                        Thread thread1 = new Thread() {
                                                            @Override
                                                            public void run() {
                                                                try {
                                                                    if (stPlay==5) {
                                                                        isE = true;
                                                                        return;
                                                                    }
                                                                    Thread.sleep(5000);
                                                                    isNext = true;
                                                                    imageButton.setVisible(true);
                                                                }
                                                                catch (InterruptedException ignore) {}
                                                            }
                                                        };
                                                        if (isNext) thread1.start();
                                                        isNext = false;
                                                    }
                                                });
                                                imageButton.setPosition(Width - 300, 8);
                                                group.addActor(imageButton);
                                                textButton.setVisible(false);
                                                textButton1.setVisible(false);
                                                imageButton.setVisible(false);
                                                Thread.sleep(3000);
                                                if (assetManager.isLoaded(path1)) {
                                                    music.stop();
                                                    music = assetManager.get(path1, Music.class);
                                                    music.setLooping(true);
                                                    music.setVolume(0.5f);
                                                    music.play();
                                                }
                                                stPlay = 1;
                                                isNext = true;
                                                Thread.sleep(5000);
                                                imageButton.setVisible(true);
                                                while (!isE) {
                                                    Thread.sleep(1);
                                                }

//                                              Thread.sleep(3000);
//                                              stPlay = 1;
//                                              Thread.sleep(15000);
//                                              stPlay = 2;
//                                              Thread.sleep(15000);
//                                              stPlay = 3;
//                                              Thread.sleep(15000);
//                                              stPlay = 4;
//                                              Thread.sleep(15000);

                                                if (assetManager.isLoaded(coin)) {
                                                    try {
                                                        if (music1.isPlaying()) music1.stop();
                                                    } catch (Exception ignore) {}
                                                    music1 = assetManager.get(coin, Music.class);
                                                    music1.setVolume(0.8f);
                                                    music1.play();
                                                }
                                                imageButton.setVisible(false);
                                                stPlay = 0;
                                                isPlay = false;
                                                after = true;
                                                isR = false;
                                                isNext = false;
                                                isE = false;
                                                textButton.setVisible(true);
                                                prefs.setRecycledPaper(prefs.getForRecycled());
                                                addScr = (int) Math.ceil(prefs.getForRecycled() * 0.5);
                                                prefs.setScore(addScr);
                                                prefs.setForRecycledToZero();
                                                for (int i = 0; i < String.valueOf(addScr).length(); i++) {
                                                    n1++;
                                                }
                                                n2 = 1;
                                                n = 0;
                                                for (int i = 0; i < String.valueOf(prefs.getScore()).length(); i++) {
                                                    n++;
                                                }
                                            } catch (InterruptedException ignored) {}
                                        }
                                    };
                                    thread.start();
                                }
                            }
                        });
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    n = 0;
                    for (int i = 0; i < String.valueOf(prefs.getScore()).length(); i++) {
                        n++;
                    }
                    label.setVisible(true);
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1500);
                                label.setVisible(false);
                            } catch (InterruptedException ignored) {}
                        }
                    };
                    thread.start();
                }
            });
            textButton1.setPosition((Width >> 1) - 80, Height - 110);
            group.addActor(textButton1);
        }

        group.addActor(label);
        stage.addActor(group);
        Gdx.input.setInputProcessor(stage);

        for (int i = 0; i < String.valueOf(prefs.getScore()).length(); i++) {
            n++;
        }
        for (int i = 0; i < String.valueOf(prefs.getForRecycled()).length(); i++) {
            n2++;
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

        stage.getBatch().begin();
        if (!isPlay) {
            stage.getBatch().draw(bg, -200, -200);
            if (isS) stage.getBatch().draw(st, 0, 0, Width, Height);
            if (isR) stage.getBatch().draw(p1, (Width >> 1) + 150, (Height >> 1) - 400);
            else if (fst == 4) {
                font24.setColor(Color.RED);
                font24.getData().setScale(2f);
                font24.draw(stage.getBatch(), chosenLanguage[22], (Width >> 1) - 210, Height - 50);
                font24.getData().setScale(1f);
            }
            font24.getData().setScale(2f);
            font24.setColor(Color.CYAN);
            font24.draw(stage.getBatch(), String.valueOf(prefs.getForRecycled()), 10, Height - 140);
            stage.getBatch().draw(texture2, 5 + 35 * n2, Height - 200, 90, 90);
            font24.setColor(Color.GOLDENROD);
            font24.draw(stage.getBatch(), String.valueOf(prefs.getScore()), 10, Height - 220);
            stage.getBatch().draw(texture1, 5 + 35 * n, Height - 290, 90, 90);
            font24.getData().setScale(1f);
            if (after) {
                font24.getData().setScale(2f);
                font24.draw(stage.getBatch(), "+" + addScr, 10, Height - 290);
                stage.getBatch().draw(texture1, 5 + 35 * n1, Height - 360, 90, 90);
                stage.getBatch().draw(p2, (Width >> 1) + 150, (Height >> 1) - 300);
                font24.getData().setScale(1f);
            }
        }
        else {
            if (stPlay == 1) {
                if (anim >= 0 && anim < 3) {
                    anim++;
                    region = atlas.findRegion("St11");
                }
                else if (anim >= 3 && anim < 6) {
                    anim++;
                    region = atlas.findRegion("St12");
                }
                else if (anim >= 6 && anim < 9) {
                    anim++;
                    region = atlas.findRegion("St13");
                }
                else {
                    anim = 0;
                }
            }
            else if (stPlay == 2) {
                if (anim >= 0 && anim < 4) {
                    anim++;
                    region = atlas.findRegion("St21");
                }
                else if (anim >= 4 && anim < 8) {
                    anim++;
                    region = atlas.findRegion("St22");
                }
                else {
                    anim = 0;
                }
            }
            else if (stPlay == 3) {
                if (anim >= 0 && anim < 2) {
                    anim++;
                    region = atlas.findRegion("St31");
                }
                else if (anim >= 2 && anim < 4) {
                    anim++;
                    region = atlas.findRegion("St32");
                }
                else {
                    anim = 0;
                }
            }
            stage.getBatch().draw(region, 0, 0, Width, Height);
            if (stPlay == 0) stage.getBatch().draw(tRegion, (Width>>1)-281, (Height>>1)-35, 562, 140);
            else if (stPlay == 1) {
                tRegion = tAtlas.findRegion("N1");
                stage.getBatch().draw(tRegion, 20, Height-650, 562, 632);
            }
            else if (stPlay == 2) {
                tRegion = tAtlas.findRegion("N2");
                stage.getBatch().draw(tRegion, Width-570, Height-650, 562, 632);
            }
            else if (stPlay == 3) {
                tRegion = tAtlas.findRegion("N3");
                stage.getBatch().draw(tRegion, 20, Height-650, 562, 632);
            }
            else if (stPlay == 4) {
                tRegion = tAtlas.findRegion("N4");
                stage.getBatch().draw(tRegion, (Width>>1)-342, (Height>>1)-316, 684, 638);
            }
        }

        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
        stage.getBatch().begin();
        if (fst < 3) stage.getBatch().draw(texture1, (Width >> 1)+170, Height - 88, 50, 50);
        else if (fst == 3) stage.getBatch().draw(texture1, (Width >> 1)+190, Height - 88, 50, 50);
        stage.getBatch().end();
    }

    /**
     * Destructor
     */
    @Override
    public void dispose() {
        try {
            music.stop();
        }
        catch (Exception ignore) {}

        try {
            font24.dispose();
            stage.dispose();
            st.dispose();
            texture1.dispose();
            texture2.dispose();
            bg.dispose();
            atlas.dispose();
            tAtlas.dispose();
            p1.dispose();
            p2.dispose();
            music.dispose();
            music1.dispose();
            assetManager.dispose();
        }
        catch (Exception ignore) {}
    }
}
