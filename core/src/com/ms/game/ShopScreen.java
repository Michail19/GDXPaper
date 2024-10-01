package com.ms.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class ShopScreen extends AbstractScreen{
    /**
     * Shop
     *
     * Created by MS
     */
    private Stage stage;
    private BitmapFont font24;
    private Prefs prefs;
    private String[] chosenLanguage;
    private final float w = Gdx.graphics.getWidth()>>1;
    private float scrollSpeed = 0.0f;
    private Group group, group2;
    private Texture t, t1, t2, texture6, img;
    private int k = 0, n; //* k - scrolling level, n - for coin position
    private boolean btn2 = false;
    private Label label;
    private final int Width = Gdx.graphics.getWidth(), Height = Gdx.graphics.getHeight();

    /**
     * Menu screen constructor
     */
    public ShopScreen() {}

    /**
     * Initialization and creating button
     */
    @Override
    public void show() {
        stage = new Stage();
        font24 = new BitmapFont(Gdx.files.internal("font32S.fnt"));
        prefs = new Prefs();
        t = new Texture(Gdx.files.internal("BarM.png"));
        t1 = new Texture(Gdx.files.internal("Bar1.png"));
        t2 = new Texture(Gdx.files.internal("Bar2.png"));
        texture6 = new Texture(Gdx.files.internal("Coin.png"));
        if (prefs.getBg() == 0) img = new Texture(Gdx.files.internal("Landscape.png"));
        else img = new Texture(Gdx.files.internal("City.png"));
        chosenLanguage = language[prefs.getLang()];
        stage.getCamera().viewportWidth = Width;
        stage.getCamera().viewportHeight = Height;

        GestureDetector gestureDetector = new GestureDetector(new GestureDetector.GestureListener() {
            @Override
            public boolean touchDown(float x, float y, int pointer, int button) {
                return false;
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
                return false;
            }

            @Override
            public boolean longPress(float x, float y) {
                return false;
            }

            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                return false;
            }

            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                scrollSpeed = -deltaX;
                return false;
            }

            @Override
            public boolean panStop(float x, float y, int pointer, int button) {
                return false;
            }

            @Override
            public boolean zoom(float initialDistance, float distance) {
                return false;
            }

            @Override
            public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
                return false;
            }

            @Override
            public void pinchStop() {

            }
        });

        group = new Group();
        Texture texture = new Texture(Gdx.files.internal("ButtonDesign1.png"));
        final Skin skin = new Skin();
        skin.add("simple1Button", texture);
        texture = new Texture(Gdx.files.internal("ButtonDesign3U.png"));
        skin.add("simple2Button", texture);
        texture = new Texture(Gdx.files.internal("ButtonDesign4.png"));
        skin.add("simple3Button", texture);

        final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("simple1Button");
        textButtonStyle.font = font24;
        textButtonStyle.fontColor = Color.FOREST;
        TextButton imgBtn = new TextButton(chosenLanguage[5], textButtonStyle);
        imgBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.MENU);
            }
        });
        imgBtn.setPosition((Width>>1)-250, -90);
        group.addActor(imgBtn);
        group.setPosition(stage.getCamera().position.x, Height - (Height >> 5));
        stage.addActor(group);

        Group group1 = new Group();
        Texture texture1 = new Texture(Gdx.files.internal("Slot.png"));
        skin.add("slot", texture1);
        texture1 = new Texture(Gdx.files.internal("BlueTs.png"));
        skin.add("A1", texture1);
        texture1 = new Texture(Gdx.files.internal("VestT.png"));
        skin.add("A2", texture1);
        texture1 = new Texture(Gdx.files.internal("JamperT.png"));
        skin.add("A3", texture1);
        texture1 = new Texture(Gdx.files.internal("PinkTST.png"));
        skin.add("A4", texture1);
        texture1 = new Texture(Gdx.files.internal("WBTST.png"));
        skin.add("A5", texture1);
        texture1 = new Texture(Gdx.files.internal("BJamperT.png"));
        skin.add("A6", texture1);
        texture1 = new Texture(Gdx.files.internal("PJamperT.png"));
        skin.add("A7", texture1);
        texture1 = new Texture(Gdx.files.internal("ShortsT.png"));
        skin.add("A8", texture1);
        texture1 = new Texture(Gdx.files.internal("TrousersT.png"));
        skin.add("A9", texture1);
        texture1 = new Texture(Gdx.files.internal("JeansT.png"));
        skin.add("A10", texture1);
        texture1 = new Texture(Gdx.files.internal("JeansTE.png"));
        skin.add("A11", texture1);
        texture1 = new Texture(Gdx.files.internal("Text.png"));
        skin.add("A12", texture1);
        texture1 = new Texture(Gdx.files.internal("FinalT.png"));
        skin.add("A12G", texture1);
        texture1 = new Texture(Gdx.files.internal("magnet.png"));
        skin.add("A13", texture1);
        texture1 = new Texture(Gdx.files.internal("x2bonus.png"));
        skin.add("A14", texture1);
        texture1 = new Texture(Gdx.files.internal("acceleration.png"));
        skin.add("A15", texture1);
        texture1 = new Texture(Gdx.files.internal("SupportUs.png"));
        skin.add("A16", texture1);
        texture1 = new Texture(Gdx.files.internal("CityIcon.png"));
        skin.add("A17", texture1);
        texture1 = new Texture(Gdx.files.internal("LandscapeIcon.png"));
        skin.add("A18", texture1);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font24;
        labelStyle.fontColor = Color.CYAN;
        labelStyle.background = skin.getDrawable("simple2Button");
        label = new Label(chosenLanguage[14], labelStyle);
        label.setVisible(false);
        label.setAlignment(Align.center);

        ImageTextButton.ImageTextButtonStyle textButtonStyle1 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle1.up = skin.getDrawable("slot");
        textButtonStyle1.down = skin.getDrawable("slot");
        textButtonStyle1.checked = skin.getDrawable("slot");
        textButtonStyle1.imageUp = skin.getDrawable("A1");
        textButtonStyle1.imageDown = skin.getDrawable("A1");
        textButtonStyle1.imageChecked = skin.getDrawable("A1");
        textButtonStyle1.font = font24;
        textButtonStyle1.fontColor = Color.CYAN;
        final ImageTextButton a1 = new ImageTextButton("", textButtonStyle1);
        font24.getData().setScale(1.5f);
        a1.getLabel().setAlignment(Align.bottom);
        if (!prefs.isA1()) {
            a1.setText("15 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getTop() != 1) a1.setText(chosenLanguage[7]);
            else a1.setText(chosenLanguage[8]);
        }

        ImageTextButton.ImageTextButtonStyle textButtonStyle2 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle2.up = skin.getDrawable("slot");
        textButtonStyle2.down = skin.getDrawable("slot");
        textButtonStyle2.checked = skin.getDrawable("slot");
        textButtonStyle2.imageUp = skin.getDrawable("A2");
        textButtonStyle2.imageDown = skin.getDrawable("A2");
        textButtonStyle2.imageChecked = skin.getDrawable("A2");
        textButtonStyle2.font = font24;
        textButtonStyle2.fontColor = Color.CYAN;
        final ImageTextButton a2 = new ImageTextButton("", textButtonStyle2);
        a2.getLabel().setAlignment(Align.bottom);
        if (!prefs.isA2()) {
            a2.setText("20 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getTop() != 2) a2.setText(chosenLanguage[7]);
            else a2.setText(chosenLanguage[8]);
        }

        ImageTextButton.ImageTextButtonStyle textButtonStyle3 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle3.up = skin.getDrawable("slot");
        textButtonStyle3.down = skin.getDrawable("slot");
        textButtonStyle3.checked = skin.getDrawable("slot");
        textButtonStyle3.imageUp = skin.getDrawable("A3");
        textButtonStyle3.imageDown = skin.getDrawable("A3");
        textButtonStyle3.imageChecked = skin.getDrawable("A3");
        textButtonStyle3.font = font24;
        textButtonStyle3.fontColor = Color.CYAN;
        final ImageTextButton a3 = new ImageTextButton("", textButtonStyle3);
        a3.getLabel().setAlignment(Align.bottom);
        if (!prefs.isA3()) {
            a3.setText("45 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getTop() != 3) a3.setText(chosenLanguage[7]);
            else a3.setText(chosenLanguage[8]);
        }

        ImageTextButton.ImageTextButtonStyle textButtonStyle4 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle4.up = skin.getDrawable("slot");
        textButtonStyle4.down = skin.getDrawable("slot");
        textButtonStyle4.checked = skin.getDrawable("slot");
        textButtonStyle4.imageUp = skin.getDrawable("A4");
        textButtonStyle4.imageDown = skin.getDrawable("A4");
        textButtonStyle4.imageChecked = skin.getDrawable("A4");
        textButtonStyle4.font = font24;
        textButtonStyle4.fontColor = Color.CYAN;
        final ImageTextButton a4 = new ImageTextButton("", textButtonStyle4);
        a4.getLabel().setAlignment(Align.bottom);
        if (!prefs.isA4()) {
            a4.setText("25 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getTop() != 4) a4.setText(chosenLanguage[7]);
            else a4.setText(chosenLanguage[8]);
        }

        ImageTextButton.ImageTextButtonStyle textButtonStyle5 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle5.up = skin.getDrawable("slot");
        textButtonStyle5.down = skin.getDrawable("slot");
        textButtonStyle5.checked = skin.getDrawable("slot");
        textButtonStyle5.imageUp = skin.getDrawable("A5");
        textButtonStyle5.imageDown = skin.getDrawable("A5");
        textButtonStyle5.imageChecked = skin.getDrawable("A5");
        textButtonStyle5.font = font24;
        textButtonStyle5.fontColor = Color.CYAN;
        final ImageTextButton a5 = new ImageTextButton("", textButtonStyle5);
        a5.getLabel().setAlignment(Align.bottom);
        if (!prefs.isA5()) {
            a5.setText("35 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getTop() != 5) a5.setText(chosenLanguage[7]);
            else a5.setText(chosenLanguage[8]);
        }

        ImageTextButton.ImageTextButtonStyle textButtonStyle6 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle6.up = skin.getDrawable("slot");
        textButtonStyle6.down = skin.getDrawable("slot");
        textButtonStyle6.checked = skin.getDrawable("slot");
        textButtonStyle6.imageUp = skin.getDrawable("A6");
        textButtonStyle6.imageDown = skin.getDrawable("A6");
        textButtonStyle6.imageChecked = skin.getDrawable("A6");
        textButtonStyle6.font = font24;
        textButtonStyle6.fontColor = Color.CYAN;
        final ImageTextButton a6 = new ImageTextButton("", textButtonStyle6);
        a6.getLabel().setAlignment(Align.bottom);
        if (!prefs.isA6()) {
            a6.setText("40 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getTop() != 6) a6.setText(chosenLanguage[7]);
            else a6.setText(chosenLanguage[8]);
        }

        ImageTextButton.ImageTextButtonStyle textButtonStyle7 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle7.up = skin.getDrawable("slot");
        textButtonStyle7.down = skin.getDrawable("slot");
        textButtonStyle7.checked = skin.getDrawable("slot");
        textButtonStyle7.imageUp = skin.getDrawable("A7");
        textButtonStyle7.imageDown = skin.getDrawable("A7");
        textButtonStyle7.imageChecked = skin.getDrawable("A7");
        textButtonStyle7.font = font24;
        textButtonStyle7.fontColor = Color.CYAN;
        final ImageTextButton a7 = new ImageTextButton("", textButtonStyle7);
        a7.getLabel().setAlignment(Align.bottom);
        if (!prefs.isA7()) {
            a7.setText("50 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getTop() != 7) a7.setText(chosenLanguage[7]);
            else a7.setText(chosenLanguage[8]);
        }

        ImageTextButton.ImageTextButtonStyle textButtonStyle12 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle12.up = skin.getDrawable("slot");
        textButtonStyle12.down = skin.getDrawable("slot");
        textButtonStyle12.checked = skin.getDrawable("slot");
        if (prefs.getA12() < 3) {
            textButtonStyle12.imageUp = skin.getDrawable("A12");
            textButtonStyle12.imageDown = skin.getDrawable("A12");
            textButtonStyle12.imageChecked = skin.getDrawable("A12");
        }
        else {
            textButtonStyle12.imageUp = skin.getDrawable("A12G");
            textButtonStyle12.imageDown = skin.getDrawable("A12G");
            textButtonStyle12.imageChecked = skin.getDrawable("A12G");
        }
        textButtonStyle12.font = font24;
        textButtonStyle12.fontColor = Color.CYAN;
        final ImageTextButton a12 = new ImageTextButton("", textButtonStyle12);
        if (prefs.getA12()==0) {
            a12.setText("10 M \n" + chosenLanguage[9]);
        }
        else if (prefs.getA12()==1) {
            a12.setText("20 M \n" + chosenLanguage[9]);
        }
        else if (prefs.getA12()==2) {
            a12.setText("40 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getTop() != 8) a12.setText(chosenLanguage[7]);
            else a12.setText(chosenLanguage[8]);
        }

        a1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA1()) {
                    prefs.setTop(1);
                    a1.setText(chosenLanguage[8]);
                    if (prefs.isA2()) a2.setText(chosenLanguage[7]);
                    if (prefs.isA3()) a3.setText(chosenLanguage[7]);
                    if (prefs.isA4()) a4.setText(chosenLanguage[7]);
                    if (prefs.isA5()) a5.setText(chosenLanguage[7]);
                    if (prefs.isA6()) a6.setText(chosenLanguage[7]);
                    if (prefs.isA7()) a7.setText(chosenLanguage[7]);
                    if (prefs.getA12() == 3) a12.setText(chosenLanguage[7]);
                }
                else {
                    if (prefs.getScore() >= 15) {
                        prefs.setScore(-15);
                        prefs.setA1();
                        a1.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a1.setPosition(20, -450);

        a2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA2()) {
                    prefs.setTop(2);
                    if (prefs.isA1()) a1.setText(chosenLanguage[7]);
                    a2.setText(chosenLanguage[8]);
                    if (prefs.isA3()) a3.setText(chosenLanguage[7]);
                    if (prefs.isA4()) a4.setText(chosenLanguage[7]);
                    if (prefs.isA5()) a5.setText(chosenLanguage[7]);
                    if (prefs.isA6()) a6.setText(chosenLanguage[7]);
                    if (prefs.isA7()) a7.setText(chosenLanguage[7]);
                    if (prefs.getA12() == 3) a12.setText(chosenLanguage[7]);
                }
                else {
                    if (prefs.getScore() >= 20) {
                        prefs.setScore(-20);
                        prefs.setA2();
                        a2.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a2.setPosition(640, -450);

        a3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA3()) {
                    prefs.setTop(3);
                    if (prefs.isA1()) a1.setText(chosenLanguage[7]);
                    if (prefs.isA2()) a2.setText(chosenLanguage[7]);
                    a3.setText(chosenLanguage[8]);
                    if (prefs.isA4()) a4.setText(chosenLanguage[7]);
                    if (prefs.isA5()) a5.setText(chosenLanguage[7]);
                    if (prefs.isA6()) a6.setText(chosenLanguage[7]);
                    if (prefs.isA7()) a7.setText(chosenLanguage[7]);
                    if (prefs.getA12() == 3) a12.setText(chosenLanguage[7]);
                }
                else {
                    if (prefs.getScore() >= 45) {
                        prefs.setScore(-45);
                        prefs.setA3();
                        a3.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a3.setPosition(1260, -450);

        a4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA4()) {
                    prefs.setTop(4);
                    if (prefs.isA1()) a1.setText(chosenLanguage[7]);
                    if (prefs.isA2()) a2.setText(chosenLanguage[7]);
                    if (prefs.isA3()) a3.setText(chosenLanguage[7]);
                    a4.setText(chosenLanguage[8]);
                    if (prefs.isA5()) a5.setText(chosenLanguage[7]);
                    if (prefs.isA6()) a6.setText(chosenLanguage[7]);
                    if (prefs.isA7()) a7.setText(chosenLanguage[7]);
                    if (prefs.getA12() == 3) a12.setText(chosenLanguage[7]);
                }
                else {
                    if (prefs.getScore() >= 25) {
                        prefs.setScore(-25);
                        prefs.setA4();
                        a4.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a4.setPosition(1880, -450);

        a5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA5()) {
                    prefs.setTop(5);
                    if (prefs.isA1()) a1.setText(chosenLanguage[7]);
                    if (prefs.isA2()) a2.setText(chosenLanguage[7]);
                    if (prefs.isA3()) a3.setText(chosenLanguage[7]);
                    if (prefs.isA4()) a4.setText(chosenLanguage[7]);
                    a5.setText(chosenLanguage[8]);
                    if (prefs.isA6()) a6.setText(chosenLanguage[7]);
                    if (prefs.isA7()) a7.setText(chosenLanguage[7]);
                    if (prefs.getA12() == 3) a12.setText(chosenLanguage[7]);
                }
                else {
                    if (prefs.getScore() >= 35) {
                        prefs.setScore(-35);
                        prefs.setA5();
                        a5.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a5.setPosition(2500, -450);

        a6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA6()) {
                    prefs.setTop(6);
                    if (prefs.isA1()) a1.setText(chosenLanguage[7]);
                    if (prefs.isA2()) a2.setText(chosenLanguage[7]);
                    if (prefs.isA3()) a3.setText(chosenLanguage[7]);
                    if (prefs.isA4()) a4.setText(chosenLanguage[7]);
                    if (prefs.isA5()) a5.setText(chosenLanguage[7]);
                    a6.setText(chosenLanguage[8]);
                    if (prefs.isA7()) a7.setText(chosenLanguage[7]);
                    if (prefs.getA12() == 3) a12.setText(chosenLanguage[7]);
                }
                else {
                    if (prefs.getScore() >= 40) {
                        prefs.setScore(-40);
                        prefs.setA6();
                        a6.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a6.setPosition(3120, -450);

        a7.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA7()) {
                    prefs.setTop(7);
                    if (prefs.isA1()) a1.setText(chosenLanguage[7]);
                    if (prefs.isA2()) a2.setText(chosenLanguage[7]);
                    if (prefs.isA3()) a3.setText(chosenLanguage[7]);
                    if (prefs.isA4()) a4.setText(chosenLanguage[7]);
                    if (prefs.isA5()) a5.setText(chosenLanguage[7]);
                    if (prefs.isA6()) a6.setText(chosenLanguage[7]);
                    a7.setText(chosenLanguage[8]);
                    if (prefs.getA12() == 3) a12.setText(chosenLanguage[7]);
                }
                else {
                    if (prefs.getScore() >= 50) {
                        prefs.setScore(-50);
                        prefs.setA7();
                        a7.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a7.setPosition(3740, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle8 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle8.up = skin.getDrawable("slot");
        textButtonStyle8.down = skin.getDrawable("slot");
        textButtonStyle8.checked = skin.getDrawable("slot");
        textButtonStyle8.imageUp = skin.getDrawable("A8");
        textButtonStyle8.imageDown = skin.getDrawable("A8");
        textButtonStyle8.imageChecked = skin.getDrawable("A8");
        textButtonStyle8.font = font24;
        textButtonStyle8.fontColor = Color.CYAN;
        final ImageTextButton a8 = new ImageTextButton("", textButtonStyle8);
        a8.getLabel().setAlignment(Align.bottom);
        if (!prefs.isA8()) {
            a8.setText("20 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getDown() != 1) a8.setText(chosenLanguage[7]);
            else a8.setText(chosenLanguage[8]);
        }

        ImageTextButton.ImageTextButtonStyle textButtonStyle9 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle9.up = skin.getDrawable("slot");
        textButtonStyle9.down = skin.getDrawable("slot");
        textButtonStyle9.checked = skin.getDrawable("slot");
        textButtonStyle9.imageUp = skin.getDrawable("A9");
        textButtonStyle9.imageDown = skin.getDrawable("A9");
        textButtonStyle9.imageChecked = skin.getDrawable("A9");
        textButtonStyle9.font = font24;
        textButtonStyle9.fontColor = Color.CYAN;
        final ImageTextButton a9 = new ImageTextButton("", textButtonStyle9);
        a9.getLabel().setAlignment(Align.bottom);
        if (!prefs.isA9()) {
            a9.setText("30 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getDown() != 2) a9.setText(chosenLanguage[7]);
            else a9.setText(chosenLanguage[8]);
        }

        ImageTextButton.ImageTextButtonStyle textButtonStyle10 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle10.up = skin.getDrawable("slot");
        textButtonStyle10.down = skin.getDrawable("slot");
        textButtonStyle10.checked = skin.getDrawable("slot");
        textButtonStyle10.imageUp = skin.getDrawable("A10");
        textButtonStyle10.imageDown = skin.getDrawable("A10");
        textButtonStyle10.imageChecked = skin.getDrawable("A10");
        textButtonStyle10.font = font24;
        textButtonStyle10.fontColor = Color.CYAN;
        final ImageTextButton a10 = new ImageTextButton("", textButtonStyle10);
        a10.getLabel().setAlignment(Align.bottom);
        if (!prefs.isA10()) {
            a10.setText("40 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getDown() != 3) a10.setText(chosenLanguage[7]);
            else a10.setText(chosenLanguage[8]);
        }

        ImageTextButton.ImageTextButtonStyle textButtonStyle11 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle11.up = skin.getDrawable("slot");
        textButtonStyle11.down = skin.getDrawable("slot");
        textButtonStyle11.checked = skin.getDrawable("slot");
        textButtonStyle11.imageUp = skin.getDrawable("A11");
        textButtonStyle11.imageDown = skin.getDrawable("A11");
        textButtonStyle11.imageChecked = skin.getDrawable("A11");
        textButtonStyle11.font = font24;
        textButtonStyle11.fontColor = Color.CYAN;
        final ImageTextButton a11 = new ImageTextButton("", textButtonStyle11);
        a11.getLabel().setAlignment(Align.bottom);
        if (!prefs.isA11()) {
            a11.setText("25 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getDown() != 4) a11.setText(chosenLanguage[7]);
            else a11.setText(chosenLanguage[8]);
        }

        a8.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA8()) {
                    prefs.setDown(1);
                    a8.setText(chosenLanguage[8]);
                    if (prefs.isA9()) a9.setText(chosenLanguage[7]);
                    if (prefs.isA10()) a10.setText(chosenLanguage[7]);
                    if (prefs.isA11()) a11.setText(chosenLanguage[7]);
                }
                else {
                    if (prefs.getScore() >= 20) {
                        prefs.setScore(-20);
                        prefs.setA8();
                        a8.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a8.setPosition(4360, -450);

        a9.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA9()) {
                    prefs.setDown(2);
                    if (prefs.isA8()) a8.setText(chosenLanguage[7]);
                    a9.setText(chosenLanguage[8]);
                    if (prefs.isA10()) a10.setText(chosenLanguage[7]);
                    if (prefs.isA11()) a11.setText(chosenLanguage[7]);
                }
                else {
                    if (prefs.getScore() >= 30) {
                        prefs.setScore(-30);
                        prefs.setA9();
                        a9.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a9.setPosition(4980, -450);

        a10.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA10()) {
                    prefs.setDown(3);
                    if (prefs.isA8()) a8.setText(chosenLanguage[7]);
                    if (prefs.isA9()) a9.setText(chosenLanguage[7]);
                    a10.setText(chosenLanguage[8]);
                    if (prefs.isA11()) a11.setText(chosenLanguage[7]);
                }
                else {
                    if (prefs.getScore() >= 40) {
                        prefs.setScore(-40);
                        prefs.setA10();
                        a10.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a10.setPosition(5600, -450);

        a11.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA11()) {
                    prefs.setDown(4);
                    if (prefs.isA8()) a8.setText(chosenLanguage[7]);
                    if (prefs.isA9()) a9.setText(chosenLanguage[7]);
                    if (prefs.isA10()) a10.setText(chosenLanguage[7]);
                    a11.setText(chosenLanguage[8]);
                }
                else {
                    if (prefs.getScore() >= 25) {
                        prefs.setScore(-25);
                        prefs.setA11();
                        a11.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a11.setPosition(6220, -450);

        a12.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.getA12() == 0 && prefs.getScore() >= 10) {
                    prefs.setA12(1);
                    a12.setText("20M \n" + chosenLanguage[9]);
                    prefs.setScore(-10);
                    label.setText(chosenLanguage[14]);
                    label.setVisible(true);
                    setTimer();
                }
                else if (prefs.getA12() == 1 && prefs.getScore() >= 20) {
                    prefs.setA12(2);
                    a12.setText("40M \n" + chosenLanguage[9]);
                    prefs.setScore(-20);
                    label.setText(chosenLanguage[14]);
                    label.setVisible(true);
                    setTimer();
                }
                else if (prefs.getA12() == 2 && prefs.getScore() >= 40) {
                    prefs.setA12(3);
                    a12.setText(chosenLanguage[7]);
                    prefs.setScore(-40);
                    label.setText(chosenLanguage[14]);
                    label.setVisible(true);
                    a12.getStyle().imageUp =  skin.getDrawable("A12G");
                    a12.getStyle().imageDown = skin.getDrawable("A12G");
                    a12.getStyle().imageChecked = skin.getDrawable("A12G");
                    setTimer();
                }
                else if (prefs.getA12() == 3) {
                    prefs.setTop(8);
                    if (prefs.isA1()) a1.setText(chosenLanguage[7]);
                    if (prefs.isA2()) a2.setText(chosenLanguage[7]);
                    if (prefs.isA3()) a3.setText(chosenLanguage[7]);
                    if (prefs.isA4()) a4.setText(chosenLanguage[7]);
                    if (prefs.isA5()) a5.setText(chosenLanguage[7]);
                    if (prefs.isA6()) a6.setText(chosenLanguage[7]);
                    if (prefs.isA7()) a7.setText(chosenLanguage[7]);
                    a12.setText(chosenLanguage[8]);
                }
                else {
                    label.setText(chosenLanguage[15]);
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        a12.setPosition(6840, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle17 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle17.up = skin.getDrawable("slot");
        textButtonStyle17.down = skin.getDrawable("slot");
        textButtonStyle17.checked = skin.getDrawable("slot");
        textButtonStyle17.imageUp = skin.getDrawable("A17");
        textButtonStyle17.imageDown = skin.getDrawable("A17");
        textButtonStyle17.imageChecked = skin.getDrawable("A17");
        textButtonStyle17.font = font24;
        textButtonStyle17.fontColor = Color.CYAN;
        final ImageTextButton a17 = new ImageTextButton(chosenLanguage[9], textButtonStyle17);
        if (!prefs.isA17()) {
            a17.setText("60 M \n" + chosenLanguage[9]);
        }
        else {
            if (prefs.getBg() != 1) a17.setText(chosenLanguage[7]);
            else a17.setText(chosenLanguage[8]);
        }
        ImageTextButton.ImageTextButtonStyle textButtonStyle18 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle18.up = skin.getDrawable("slot");
        textButtonStyle18.down = skin.getDrawable("slot");
        textButtonStyle18.checked = skin.getDrawable("slot");
        textButtonStyle18.imageUp = skin.getDrawable("A18");
        textButtonStyle18.imageDown = skin.getDrawable("A18");
        textButtonStyle18.imageChecked = skin.getDrawable("A18");
        textButtonStyle18.font = font24;
        textButtonStyle18.fontColor = Color.CYAN;
        final ImageTextButton a18 = new ImageTextButton(chosenLanguage[8], textButtonStyle18);
        if (prefs.getBg() != 0) a18.setText(chosenLanguage[7]);
        else a18.setText(chosenLanguage[8]);

        a17.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!prefs.isA17()) {
                    if (prefs.getScore() >= 60) {
                        prefs.setScore(-60);
                        prefs.setA17();
                        a17.setText(chosenLanguage[7]);
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
                else {
                    prefs.setBg(1);
                    img = new Texture(Gdx.files.internal("City.png"));
                    a18.setText(chosenLanguage[7]);
                    a17.setText(chosenLanguage[8]);
                }
            }
        });
        a17.setPosition(7460, -450);

        a18.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.setBg(0);
                img = new Texture(Gdx.files.internal("Landscape.png"));
                if (prefs.isA17()) a17.setText(chosenLanguage[7]);
                a18.setText(chosenLanguage[8]);
            }
        });
        a18.setPosition(8080, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle13 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle13.up = skin.getDrawable("slot");
        textButtonStyle13.down = skin.getDrawable("slot");
        textButtonStyle13.checked = skin.getDrawable("slot");
        textButtonStyle13.imageUp = skin.getDrawable("A13");
        textButtonStyle13.imageDown = skin.getDrawable("A13");
        textButtonStyle13.imageChecked = skin.getDrawable("A13");
        textButtonStyle13.font = font24;
        textButtonStyle13.fontColor = Color.CYAN;
        ImageTextButton a13 = new ImageTextButton(chosenLanguage[9] + "\n  x1  \n  10  ", textButtonStyle13);
        a13.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.getScore() >= 10) {
                    prefs.setScore(-10);
                    prefs.setAmountMagnetBooster(1);
                    label.setText(chosenLanguage[14]);
                }
                else {
                    label.setText(chosenLanguage[15]);
                }
                label.setVisible(true);
                setTimer();
            }
        });
        a13.setPosition(8700, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle14 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle14.up = skin.getDrawable("slot");
        textButtonStyle14.down = skin.getDrawable("slot");
        textButtonStyle14.checked = skin.getDrawable("slot");
        textButtonStyle14.imageUp = skin.getDrawable("A14");
        textButtonStyle14.imageDown = skin.getDrawable("A14");
        textButtonStyle14.imageChecked = skin.getDrawable("A14");
        textButtonStyle14.font = font24;
        textButtonStyle14.fontColor = Color.CYAN;
        ImageTextButton a14 = new ImageTextButton(chosenLanguage[9] + "\n  x1  \n  12  ", textButtonStyle14);
        a14.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.getScore() >= 12) {
                    prefs.setScore(-12);
                    prefs.setAmountIncreaseWeightBooster(1);
                    label.setText(chosenLanguage[14]);
                }
                else {
                    label.setText(chosenLanguage[15]);
                }
                label.setVisible(true);
                setTimer();
            }
        });
        a14.setPosition(9320, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle15 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle15.up = skin.getDrawable("slot");
        textButtonStyle15.down = skin.getDrawable("slot");
        textButtonStyle15.checked = skin.getDrawable("slot");
        textButtonStyle15.imageUp = skin.getDrawable("A15");
        textButtonStyle15.imageDown = skin.getDrawable("A15");
        textButtonStyle15.imageChecked = skin.getDrawable("A15");
        textButtonStyle15.font = font24;
        textButtonStyle15.fontColor = Color.CYAN;
        ImageTextButton a15 = new ImageTextButton(chosenLanguage[9] + "\n  x1  \n  7  ", textButtonStyle15);
        a15.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.getScore() >= 7) {
                    prefs.setScore(-7);
                    prefs.setAmountSpeedBooster(1);
                    label.setText(chosenLanguage[14]);
                }
                else {
                    label.setText(chosenLanguage[15]);
                }
                label.setVisible(true);
                setTimer();
            }
        });
        a15.setPosition(9940, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle16 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle16.up = skin.getDrawable("slot");
        textButtonStyle16.down = skin.getDrawable("slot");
        textButtonStyle16.checked = skin.getDrawable("slot");
        textButtonStyle16.imageUp = skin.getDrawable("A16");
        textButtonStyle16.imageDown = skin.getDrawable("A16");
        textButtonStyle16.imageChecked = skin.getDrawable("A16");
        textButtonStyle16.font = font24;
        textButtonStyle16.fontColor = Color.CYAN;
        ImageTextButton a16 = new ImageTextButton("", textButtonStyle16);
        a16.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.setScore(100);
                prefs.setForRecycled(100);
                label.setText(chosenLanguage[16]);
                label.setVisible(true);
                setTimer();
                n = 0;
                for (int i = 0; i < String.valueOf(prefs.getScore()).length(); i++) {
                    n++;
                }
            }
        });
        a16.setPosition(10560, -450);

        group1.addActor(a1);
        group1.addActor(a2);
        group1.addActor(a3);
        group1.addActor(a4);
        group1.addActor(a5);
        group1.addActor(a6);
        group1.addActor(a7);
        group1.addActor(a8);
        group1.addActor(a9);
        group1.addActor(a10);
        group1.addActor(a11);
        group1.addActor(a12);
        group1.addActor(a13);
        group1.addActor(a14);
        group1.addActor(a15);
        group1.addActor(a16);
        group1.addActor(a17);
        group1.addActor(a18);
        group1.setPosition(0, (Height >> 1)+25);
        stage.addActor(group1);

        group2 = new Group();
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font24;
        buttonStyle.fontColor = Color.WHITE;
        buttonStyle.up = skin.getDrawable("simple3Button");
        TextButton textButton1 = new TextButton(chosenLanguage[10], buttonStyle);
        TextButton textButton2 = new TextButton(chosenLanguage[11], buttonStyle);
        TextButton textButton3 = new TextButton(chosenLanguage[12], buttonStyle);
        textButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (stage.getCamera().position.x != 0) scrollSpeed = -500f;
            }
        });
        textButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                scrollSpeed = count(stage.getCamera().position.x);
                btn2 = true;
            }
        });
        textButton3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (stage.getCamera().position.x != 8700) scrollSpeed = 500f;
            }
        });
        textButton1.setPosition(-360, 140);
        textButton2.setPosition(-150, 140);
        textButton3.setPosition(65, 140);
        group2.addActor(textButton1);
        group2.addActor(textButton2);
        group2.addActor(textButton3);
        group2.setPosition((Width>>1), Height-(Height>>2));
        stage.addActor(group2);
        stage.addActor(label);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(gestureDetector);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);

        n = 0;
        for (int i = 0; i < String.valueOf(prefs.getScore()).length(); i++) {
            n++;
        }
    }

    private void setTimer() {
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

    /**
     * Function for calculation distance from start point to start point of boosters
     *
     * @param pos - position
     * @return - returns speed
     */
    private float count(float pos) {
        float ans = 0;
        if (pos < 9720) {
            while (pos < 9720) {
                pos += ans;
                ans += 10;
                if (pos == 9720) return ans;
            }
            return ans-19;
        }
        else if (pos > 9720){
            while (pos > 9720) {
                pos += ans;
                ans -= 10;
                if (pos == 9720) return ans;
            }
            return ans+19;
        }
        else {
            return 0;
        }
    }

    /**
     * Rendering all objects
     * @param delta - delta time
     */
    @Override
    public void render(float delta) {
        update(delta);

        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);

        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(img, stage.getCamera().position.x - (Width >> 1), -300);
        stage.getBatch().end();

        stage.draw();

        stage.getBatch().begin();
        stage.getBatch().draw(t, stage.getCamera().position.x-306, 0);
        if (scrollSpeed > 0) {
            stage.getBatch().draw(t1, stage.getCamera().position.x + (stage.getCamera().position.x/16) - 420, 0);
            k = 0;
        }
        else if (scrollSpeed < 0) {
            stage.getBatch().draw(t2, stage.getCamera().position.x + (stage.getCamera().position.x/16) - 420, 0);
            k = 1;
        }
        else {
            if (k==0) stage.getBatch().draw(t1, stage.getCamera().position.x + (stage.getCamera().position.x/16) - 420, 0);
            else stage.getBatch().draw(t2, stage.getCamera().position.x + (stage.getCamera().position.x/16) - 420, 0);
        }
        font24.setColor(Color.GOLDENROD);
        font24.getData().setScale(2f);
        //font24.draw(stage.getBatch(), chosenLanguage[1] + prefs.getScore(), stage.getCamera().position.x - (Width >> 1) + 20, Height - 25);
        stage.getBatch().draw(texture6, stage.getCamera().position.x - (Width >> 1) + 5 + 35 * n, Height - 90, 80, 80);
        font24.draw(stage.getBatch(), String.valueOf(prefs.getScore()), stage.getCamera().position.x - (Width >> 1) + 20, Height - 20);
        font24.getData().setScale(1.5f);

        int y = Height - (Height >> 1) - (Height >> 5) - (Height >> 8) * 3 + 20;
        if (!prefs.isA1()) stage.getBatch().draw(texture6, 440, y, 60, 60);
        if (!prefs.isA2()) stage.getBatch().draw(texture6, 1060, y, 60, 60);
        if (!prefs.isA3()) stage.getBatch().draw(texture6, 1715, y, 60, 60);
        if (!prefs.isA4()) stage.getBatch().draw(texture6, 2305, y, 60, 60);
        if (!prefs.isA5()) stage.getBatch().draw(texture6, 2925, y, 60, 60);
        if (!prefs.isA6()) stage.getBatch().draw(texture6, 3580, y, 60, 60);
        if (!prefs.isA7()) stage.getBatch().draw(texture6, 4195, y, 60, 60);
        if (!prefs.isA8()) stage.getBatch().draw(texture6, 4805, y, 60, 60);
        if (!prefs.isA9()) stage.getBatch().draw(texture6, 5405, y, 60, 60);
        if (!prefs.isA10()) stage.getBatch().draw(texture6, 6030, y, 60, 60);
        if (!prefs.isA11()) stage.getBatch().draw(texture6, 6650, y, 60, 60);
        if (prefs.getA12() != 3) stage.getBatch().draw(texture6, 7260, y, 60, 60);
        if (!prefs.isA17()) stage.getBatch().draw(texture6, 7885, y, 60, 60);
        y = Height - (Height >> 1) - (Height >> 5) * 4 - (Height >> 8) + 25;
        stage.getBatch().draw(texture6, 9095, y, 60, 60);
        stage.getBatch().draw(texture6, 9715, y, 60, 60);
        stage.getBatch().draw(texture6, 10325, y, 60, 60);
        stage.getBatch().end();
    }

    /**
     * Update function
     * @param dt - delta time
     */
    public void update(float dt) {
        stage.act(dt);

        float newPosX = (stage.getCamera().position.x + scrollSpeed), gp = group.getX() + scrollSpeed;
        float MAX_SCROLL = 11180.0f;
        float MIN_SCROLL = 0.0f;
        if (newPosX + (w - scrollSpeed) - 10 > MAX_SCROLL) {
            newPosX = MAX_SCROLL - (w - scrollSpeed) + 10;
            gp = MAX_SCROLL - (w - scrollSpeed) + 10;
        }
        else if (newPosX < MIN_SCROLL - 10 + (w - scrollSpeed)) {
            newPosX = -10 + (w - scrollSpeed);
            gp = -10 + (w - scrollSpeed);
        }
        group.setPosition(gp, group.getY());
        group2.setPosition(gp, group2.getY());
        stage.getCamera().position.set(newPosX, stage.getCamera().position.y, 0);
        stage.getCamera().update();
        label.setPosition(gp, Height>>1);

        if (scrollSpeed > 0) {
            scrollSpeed -= 10;
            if (scrollSpeed - 10 < 0) scrollSpeed = 0;
        }
        else if (scrollSpeed < 0) {
            scrollSpeed += 10;
            if (scrollSpeed + 10 > 0) scrollSpeed = 0;
        }

        if (btn2 && scrollSpeed==0) {
            group.setPosition(9650, group.getY());
            group2.setPosition(9650, group2.getY());
            stage.getCamera().position.set(9650, stage.getCamera().position.y, 0);
            stage.getCamera().update();
            btn2 = false;
        }
    }

    /**
     * Destructor
     */
    @Override
    public void dispose() {
        try {
            font24.dispose();
            stage.dispose();
            t.dispose();
            t1.dispose();
            t2.dispose();
        }
        catch (Exception ignore) {}
    }
}
