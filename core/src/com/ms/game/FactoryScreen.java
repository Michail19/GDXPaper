package com.ms.game;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class FactoryScreen extends AbstractScreen{
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
    private boolean isS = true, isR = false, isPlay = false, after = false; //* isS - zero scene, isR - have on scene unprocessed paper, isPlay - animation recycling, after - after animation
    private final int Width = Gdx.graphics.getWidth();
    private final int Height = Gdx.graphics.getHeight();
    private Label label;
    private int stPlay = 0, anim = 0, n = 0, n2 = 0, n1 = 1, fst, addScr = 0;
    private TextButton textButton, textButton1;

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
        if (getLang() == 0) tAtlas = new TextureAtlas("TableFactoryE.pack");
        else tAtlas = new TextureAtlas("TableFactory.pack");
        region = atlas.findRegion("St11");
        tRegion = tAtlas.findRegion("Title");
        texture1 = new Texture(Gdx.files.internal("Coin.png"));
        texture2 = new Texture(Gdx.files.internal("CrumplesPaperMin.png"));
        bg = new Texture(Gdx.files.internal("Landscape.png"));
        p1 = new Texture(Gdx.files.internal("UnprocessedPaper.png"));
        p2 = new Texture(Gdx.files.internal("ProcessedPaper.png"));
        fst =  prefs.getStageFactory();

        stage.getCamera().viewportWidth = Width;
        stage.getCamera().viewportHeight = Height;
        stage.getRoot().getColor().a = 0;
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));

        final Group group = new Group();
        Skin skin = new Skin();
        Texture texture = new Texture(Gdx.files.internal("ButtonDesign1.png"));
        skin.add("button1", texture);
        texture = new Texture(Gdx.files.internal("ButtonDesign3U.png"));
        skin.add("simple2Button", texture);
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

        textButton = new TextButton(chosenLanguage[5], buttonStyle);
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (!isPlay) ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.MENU);
            }
        });
        textButton.setPosition(10, Height-110);
        group.addActor(textButton);

        if (fst == 0) isS = false;
        else if (fst == 1) st = new Texture(Gdx.files.internal("FScene.png"));
        else if (fst == 2) st = new Texture(Gdx.files.internal("SScene.png"));
        else if (fst == 3) st = new Texture(Gdx.files.internal("TScene.png"));
        else if (fst == 4) {
            st = new Texture(Gdx.files.internal("FinalFactory.png"));
            if (prefs.getForRecycled() > 0) isR = true;
        }

        if (fst == 4) {
            textButton1 = new TextButton(chosenLanguage[21], buttonStyle);
            textButton1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!isPlay) {
                        isPlay = true;
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    textButton.setVisible(false);
                                    textButton1.setVisible(false);
                                    Thread.sleep(3000);
                                    stPlay = 1;
                                    Thread.sleep(15000);
                                    stPlay = 2;
                                    Thread.sleep(15000);
                                    stPlay = 3;
                                    Thread.sleep(15000);
                                    stPlay = 4;
                                    Thread.sleep(15000);
                                    stPlay = 0;
                                    isPlay = false;
                                    after = true;
                                    isR = false;
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
                    if (prefs.getScore() >= 20 && fst==0) {
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
                                    isPlay = true;
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            try {
                                                textButton.setVisible(false);
                                                textButton1.setVisible(false);
                                                Thread.sleep(3000);
                                                stPlay = 1;
                                                Thread.sleep(15000);
                                                stPlay = 2;
                                                Thread.sleep(15000);
                                                stPlay = 3;
                                                Thread.sleep(15000);
                                                stPlay = 4;
                                                Thread.sleep(15000);
                                                stPlay = 0;
                                                isPlay = false;
                                                after = true;
                                                isR = false;
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
        }
        catch (Exception ignore) {}
    }
}
