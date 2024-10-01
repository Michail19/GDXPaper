package com.ms.game.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ms.game.AbstractScreen;
import com.ms.game.save.Prefs;
import com.ms.game.ScreenManager;

public class ShopScreen extends AbstractScreen {
    /**
     * Shop
     *
     * Created by MS
     */
    private Stage stage;
    private BitmapFont font24;
    private Prefs prefs;
    private String[] chosenLanguage;
    private final float w = ((int) ScreenManager.getInstance().getViewport().getWorldWidth())>>1;
    private float scrollSpeed = 0.0f;
    private Group group, group2;
    private Texture t, t1, t2, texture6, img;
    private int k = 0, n, et = 0; //* k - scrolling level, n - for coin position, et - for game
    private boolean btn2 = false, isDay = false, isET = false;
    private Label label;
    private final int Width = (int) ScreenManager.getInstance().getViewport().getWorldWidth();
    private final int Height = (int) ScreenManager.getInstance().getViewport().getWorldHeight();
    private Music musicB, musicBG;
    private AssetManager assetManager;
    private final String pathBtn = String.valueOf(Gdx.files.internal("btn.mp3")), pathCoin = String.valueOf(Gdx.files.internal("getCoin.mp3")),
            pathErr = String.valueOf(Gdx.files.internal("getErr.mp3")), pathBG = String.valueOf(Gdx.files.internal("text.mp3"));
    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\\\/?-+=()*&.;,{}\\\"´`'<>";


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
        stage.setViewport(ScreenManager.getInstance().getViewport());

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("comic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARACTERS;
        parameter.size = 48; // font size
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose(); // avoid memory leaks, important

        assetManager = new AssetManager();
        if (!prefs.isVolumeOff()) {
            assetManager.load(pathBtn, Music.class);
            assetManager.load(pathCoin, Music.class);
            assetManager.load(pathErr, Music.class);
        }
        if (!prefs.isMelOff()) {
            assetManager.load(pathBG, Music.class);
        }
        assetManager.finishLoading();

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

        Group group1 = new Group();
        Texture texture1 = new Texture(Gdx.files.internal("Slot.png"));
        skin.add("slot", texture1);
        texture1 = new Texture(Gdx.files.internal("Cancel.png"));
        skin.add("A0", texture1);
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
        texture1 = new Texture(Gdx.files.internal("magnetRL.png"));
        skin.add("A13", texture1);
        texture1 = new Texture(Gdx.files.internal("x2bonusRL.png"));
        skin.add("A14", texture1);
        texture1 = new Texture(Gdx.files.internal("accelerationRL.png"));
        skin.add("A15", texture1);
        texture1 = new Texture(Gdx.files.internal("CityIcon.png"));
        skin.add("A17", texture1);
        texture1 = new Texture(Gdx.files.internal("LandscapeIcon.png"));
        skin.add("A18", texture1);
        texture1 = new Texture(Gdx.files.internal("BgShop.png"));
        skin.add("shop", texture1);
        skin.add("coin", texture6);

        if (prefs.getLang() == 0) {
            texture1 = new Texture(Gdx.files.internal("Wear.png"));
            skin.add("wear", texture1);
            texture1 = new Texture(Gdx.files.internal("WearN.png"));
            skin.add("wearing", texture1);
            texture1 = new Texture(Gdx.files.internal("Buy.png"));
            skin.add("buy", texture1);
            texture1 = new Texture(Gdx.files.internal("Buster.png"));
            skin.add("buster", texture1);
            texture1 = new Texture(Gdx.files.internal("Cloth.png"));
            skin.add("cloth", texture1);
            texture1 = new Texture(Gdx.files.internal("Support.png"));
            skin.add("support", texture1);
            texture1 = new Texture(Gdx.files.internal("SupportUSR.png"));
            skin.add("A16", texture1);
        }
        else {
            texture1 = new Texture(Gdx.files.internal("WearE.png"));
            skin.add("wear", texture1);
            texture1 = new Texture(Gdx.files.internal("WearNE.png"));
            skin.add("wearing", texture1);
            texture1 = new Texture(Gdx.files.internal("BuyE.png"));
            skin.add("buy", texture1);
            texture1 = new Texture(Gdx.files.internal("BusterE.png"));
            skin.add("buster", texture1);
            texture1 = new Texture(Gdx.files.internal("ClothE.png"));
            skin.add("cloth", texture1);
            texture1 = new Texture(Gdx.files.internal("SupportE.png"));
            skin.add("support", texture1);
            texture1 = new Texture(Gdx.files.internal("SupportUSE.png"));
            skin.add("A16", texture1);
        }
        texture1 = new Texture(Gdx.files.internal("ExitS.png"));
        skin.add("exit", texture1);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font24;
        labelStyle.fontColor = Color.GOLD;
        labelStyle.background = skin.getDrawable("simple2Button");
        label = new Label(chosenLanguage[14], labelStyle);
        label.setVisible(false);
        label.setAlignment(Align.center);

        final ImageButton.ImageButtonStyle textButtonStyle0 = new ImageButton.ImageButtonStyle();
        if (prefs.getTop() != 0 || prefs.getDown() != 0 ) {
            textButtonStyle0.up = skin.getDrawable("wearing");
        }
        else {
            textButtonStyle0.up = skin.getDrawable("wear");
        }
        Image i0 = new Image(skin.getDrawable("slot"));
        Image s0 = new Image(skin.getDrawable("A0"));
        final ImageButton a0 = new ImageButton(textButtonStyle0);

        final ImageTextButton.ImageTextButtonStyle textButtonStyle1 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle1.up = skin.getDrawable("shop");
        textButtonStyle1.font = font;
        textButtonStyle1.fontColor = Color.GOLD;
        textButtonStyle1.up = skin.getDrawable("shop");
        textButtonStyle1.font = font;
        textButtonStyle1.fontColor = Color.GOLD;
        if (!prefs.isA1()) {
            textButtonStyle1.imageUp = skin.getDrawable("coin"); // 15
        }
        else {
            if (prefs.getTop() != 1) {
                textButtonStyle1.up = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle1.up = skin.getDrawable("wear");
            }
        }
        Image i1 = new Image(skin.getDrawable("slot"));
        Image s1 = new Image(skin.getDrawable("A1"));
        final ImageTextButton a1 = new ImageTextButton("", textButtonStyle1);
        if (!prefs.isA1()) {
            a1.setText("15");
        }

        final ImageTextButton.ImageTextButtonStyle textButtonStyle2 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle2.up = skin.getDrawable("shop");
        textButtonStyle2.font = font;
        textButtonStyle2.fontColor = Color.GOLD;
        if (!prefs.isA2()) {
            textButtonStyle2.imageUp = skin.getDrawable("coin");//20
        }
        else {
            if (prefs.getTop() != 2) {
                textButtonStyle2.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle2.imageUp = skin.getDrawable("wear");
            }
        }
        Image i2 = new Image(skin.getDrawable("slot"));
        Image s2 = new Image(skin.getDrawable("A2"));
        final ImageTextButton a2 = new ImageTextButton("", textButtonStyle2);
        if (!prefs.isA2()) {
            a2.setText("20");
        }

        final ImageTextButton.ImageTextButtonStyle textButtonStyle3 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle3.up = skin.getDrawable("shop");
        textButtonStyle3.font = font;
        textButtonStyle3.fontColor = Color.GOLD;
        if (!prefs.isA3()) {
            textButtonStyle3.imageUp = skin.getDrawable("coin");//45
        }
        else {
            if (prefs.getTop() != 3) {
                textButtonStyle3.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle3.imageUp = skin.getDrawable("wear");
            }
        }
        Image i3 = new Image(skin.getDrawable("slot"));
        Image s3 = new Image(skin.getDrawable("A3"));
        final ImageTextButton a3 = new ImageTextButton("", textButtonStyle3);
        if (!prefs.isA3()) {
            a3.setText("45");
        }

        final ImageTextButton.ImageTextButtonStyle textButtonStyle4 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle4.up = skin.getDrawable("shop");
        textButtonStyle4.font = font;
        textButtonStyle4.fontColor = Color.GOLD;
        if (!prefs.isA4()) {
            textButtonStyle4.imageUp = skin.getDrawable("coin");//25
        }
        else {
            if (prefs.getTop() != 4) {
                textButtonStyle4.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle4.imageUp = skin.getDrawable("wear");
            }
        }
        Image i4 = new Image(skin.getDrawable("slot"));
        Image s4 = new Image(skin.getDrawable("A4"));
        final ImageTextButton a4 = new ImageTextButton("", textButtonStyle4);
        if (!prefs.isA4()) {
            a4.setText("25");
        }

        final ImageTextButton.ImageTextButtonStyle textButtonStyle5 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle5.up = skin.getDrawable("shop");
        textButtonStyle5.font = font;
        textButtonStyle5.fontColor = Color.GOLD;
        if (!prefs.isA5()) {
            textButtonStyle5.imageUp = skin.getDrawable("coin");//35
        }
        else {
            if (prefs.getTop() != 5) {
                textButtonStyle5.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle5.imageUp = skin.getDrawable("wear");
            }
        }
        Image i5 = new Image(skin.getDrawable("slot"));
        Image s5 = new Image(skin.getDrawable("A5"));
        final ImageTextButton a5 = new ImageTextButton("", textButtonStyle5);
        if (!prefs.isA5()) {
            a5.setText("35");
        }

        final ImageTextButton.ImageTextButtonStyle textButtonStyle6 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle6.up = skin.getDrawable("shop");
        textButtonStyle6.font = font;
        textButtonStyle6.fontColor = Color.GOLD;
        if (!prefs.isA6()) {
            textButtonStyle6.imageUp = skin.getDrawable("coin");//40
        }
        else {
            if (prefs.getTop() != 6) {
                textButtonStyle6.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle6.imageUp = skin.getDrawable("wear");
            }
        }
        Image i6 = new Image(skin.getDrawable("slot"));
        Image s6 = new Image(skin.getDrawable("A6"));
        final ImageTextButton a6 = new ImageTextButton("", textButtonStyle6);
        if (!prefs.isA6()) {
            a6.setText("40");
        }

        final ImageTextButton.ImageTextButtonStyle textButtonStyle7 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle7.up = skin.getDrawable("shop");
        textButtonStyle7.font = font;
        textButtonStyle7.fontColor = Color.GOLD;
        if (!prefs.isA7()) {
            textButtonStyle7.imageUp = skin.getDrawable("coin");//50
        }
        else {
            if (prefs.getTop() != 7) {
                textButtonStyle7.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle7.imageUp = skin.getDrawable("wear");
            }
        }
        Image i7 = new Image(skin.getDrawable("slot"));
        Image s7 = new Image(skin.getDrawable("A7"));
        final ImageTextButton a7 = new ImageTextButton("", textButtonStyle7);
        if (!prefs.isA7()) {
            a7.setText("50");
        }

        final ImageTextButton.ImageTextButtonStyle textButtonStyle12 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle12.up = skin.getDrawable("shop");
        textButtonStyle12.font = font;
        textButtonStyle12.fontColor = Color.GOLD;
        if (prefs.getA12() < 3) {
            textButtonStyle12.imageUp = skin.getDrawable("coin"); //10, 20, 30
        }
        else {
            if (prefs.getTop() != 8) {
                textButtonStyle12.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle12.imageUp = skin.getDrawable("wear");
            }
        }
        Image i12 = new Image(skin.getDrawable("slot"));
        final ImageTextButton a12 = new ImageTextButton("", textButtonStyle12);
        final Image s12;
        if (prefs.getA12() < 3) {
            s12 = new Image(skin.getDrawable("A12"));
        }
        else {
            s12 = new Image(skin.getDrawable("A12G"));
        }

        if (prefs.getA12() == 0) {
            a12.setText("10");
        }
        else if (prefs.getA12() == 1) {
            a12.setText("20");
        }
        else if (prefs.getA12() == 2) {
            a12.setText("30");
        }

        a1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA1()) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setTop(1);
                    textButtonStyle0.up = skin.getDrawable("wearing");
                    textButtonStyle1.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA2()) textButtonStyle2.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA3()) textButtonStyle3.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA4()) textButtonStyle4.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA5()) textButtonStyle5.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA6()) textButtonStyle6.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA7()) textButtonStyle7.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.getA12() == 3) textButtonStyle12.imageUp = (skin.getDrawable("wearing"));
                }
                else {
                    if (prefs.getScore() >= 15) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-15);
                        prefs.setA1();
                        a1.setText("");
                        textButtonStyle1.imageUp = (skin.getDrawable("wearing"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i1.setPosition(20 + 620, -450);
        s1.setPosition(60 + 620, -250);
        a1.setPosition(120 + 620, -400);

        a2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA2()) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setTop(2);
                    textButtonStyle0.up = skin.getDrawable("wearing");
                    if (prefs.isA1()) textButtonStyle1.imageUp = (skin.getDrawable("wearing"));
                    textButtonStyle2.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA3()) textButtonStyle3.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA4()) textButtonStyle4.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA5()) textButtonStyle5.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA6()) textButtonStyle6.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA7()) textButtonStyle7.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.getA12() == 3) textButtonStyle12.imageUp = (skin.getDrawable("wearing"));
                }
                else {
                    if (prefs.getScore() >= 20) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-20);
                        prefs.setA2();
                        a2.setText("");
                        textButtonStyle2.imageUp = (skin.getDrawable("wearing"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i2.setPosition(20 + 620*2, -450);
        s2.setPosition(60 + 620*2, -250);
        a2.setPosition(120 + 620*2, -400);

        a3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA3()) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setTop(3);
                    textButtonStyle0.up = skin.getDrawable("wearing");
                    if (prefs.isA1()) textButtonStyle1.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA2()) textButtonStyle2.imageUp = (skin.getDrawable("wearing"));
                    textButtonStyle3.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA4()) textButtonStyle4.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA5()) textButtonStyle5.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA6()) textButtonStyle6.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA7()) textButtonStyle7.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.getA12() == 3) textButtonStyle12.imageUp = (skin.getDrawable("wearing"));
                }
                else {
                    if (prefs.getScore() >= 45) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-45);
                        prefs.setA3();
                        a3.setText("");
                        textButtonStyle3.imageUp = (skin.getDrawable("wearing"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i3.setPosition(20 + 620*3, -450);
        s3.setPosition(60 + 620*3, -250);
        a3.setPosition(120 + 620*3, -400);

        a4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA4()) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setTop(4);
                    textButtonStyle0.up = skin.getDrawable("wearing");
                    if (prefs.isA1()) textButtonStyle1.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA2()) textButtonStyle2.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA3()) textButtonStyle3.imageUp = (skin.getDrawable("wearing"));
                    textButtonStyle4.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA5()) textButtonStyle5.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA6()) textButtonStyle6.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA7()) textButtonStyle7.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.getA12() == 3) textButtonStyle12.imageUp = (skin.getDrawable("wearing"));
                }
                else {
                    if (prefs.getScore() >= 25) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-25);
                        prefs.setA4();
                        a4.setText("");
                        textButtonStyle4.imageUp = (skin.getDrawable("wearing"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i4.setPosition(20 + 620*4, -450);
        s4.setPosition(60 + 620*4, -250);
        a4.setPosition(120 + 620*4, -400);

        a5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA5()) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setTop(5);
                    textButtonStyle0.up = skin.getDrawable("wearing");
                    if (prefs.isA1()) textButtonStyle1.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA2()) textButtonStyle2.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA3()) textButtonStyle3.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA4()) textButtonStyle4.imageUp = (skin.getDrawable("wearing"));
                    textButtonStyle5.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA6()) textButtonStyle6.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA7()) textButtonStyle7.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.getA12() == 3) textButtonStyle12.imageUp = (skin.getDrawable("wearing"));
                }
                else {
                    if (prefs.getScore() >= 35) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-35);
                        prefs.setA5();
                        a5.setText("");
                        textButtonStyle5.imageUp = (skin.getDrawable("wearing"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i5.setPosition(20 + 620*5, -450);
        s5.setPosition(60 + 620*5, -250);
        a5.setPosition(120 + 620*5, -400);

        a6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA6()) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setTop(6);
                    textButtonStyle0.up = skin.getDrawable("wearing");
                    if (prefs.isA1()) textButtonStyle1.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA2()) textButtonStyle2.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA3()) textButtonStyle3.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA4()) textButtonStyle4.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA5()) textButtonStyle5.imageUp = (skin.getDrawable("wearing"));
                    textButtonStyle6.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA7()) textButtonStyle7.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.getA12() == 3) textButtonStyle12.imageUp = (skin.getDrawable("wearing"));
                }
                else {
                    if (prefs.getScore() >= 40) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-40);
                        prefs.setA6();
                        a6.setText("");
                        textButtonStyle6.imageUp = (skin.getDrawable("wearing"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i6.setPosition(20 + 620*6, -450);
        s6.setPosition(60 + 620*6, -250);
        a6.setPosition(120 + 620*6, -400);

        a7.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA7()) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setTop(7);
                    textButtonStyle0.up = skin.getDrawable("wearing");
                    if (prefs.isA1()) textButtonStyle1.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA2()) textButtonStyle2.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA3()) textButtonStyle3.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA4()) textButtonStyle4.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA5()) textButtonStyle5.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA6()) textButtonStyle6.imageUp = (skin.getDrawable("wear"));
                    textButtonStyle7.imageUp = (skin.getDrawable("wear"));
                    if (prefs.getA12() == 3) textButtonStyle12.imageUp = (skin.getDrawable("wearing"));
                }
                else {
                    if (prefs.getScore() >= 50) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-50);
                        prefs.setA7();
                        a7.setText("");
                        textButtonStyle7.imageUp = (skin.getDrawable("wear"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i7.setPosition(20 + 620*7, -450);
        s7.setPosition(60 + 620*7, -250);
        a7.setPosition(120 + 620*7, -400);

        final ImageTextButton.ImageTextButtonStyle textButtonStyle8 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle8.up = skin.getDrawable("shop");
        textButtonStyle8.font = font;
        textButtonStyle8.fontColor = Color.GOLD;
        if (!prefs.isA8()) {
            textButtonStyle8.imageUp = skin.getDrawable("coin");//20
        }
        else {
            if (prefs.getDown() != 1) {
                textButtonStyle8.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle8.imageUp = skin.getDrawable("wear");
            }
        }
        Image i8 = new Image(skin.getDrawable("slot"));
        Image s8 = new Image(skin.getDrawable("A8"));
        final ImageTextButton a8 = new ImageTextButton("", textButtonStyle8);
        if (!prefs.isA8()) {
            a8.setText("20");
        }

        final ImageTextButton.ImageTextButtonStyle textButtonStyle9 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle9.up = skin.getDrawable("shop");
        textButtonStyle9.font = font;
        textButtonStyle9.fontColor = Color.GOLD;
        if (!prefs.isA9()) {
            textButtonStyle9.imageUp = skin.getDrawable("coin");//30
        }
        else {
            if (prefs.getDown() != 2) {
                textButtonStyle9.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle9.imageUp = skin.getDrawable("wear");
            }
        }
        Image i9 = new Image(skin.getDrawable("slot"));
        Image s9 = new Image(skin.getDrawable("A9"));
        final ImageTextButton a9 = new ImageTextButton("", textButtonStyle9);
        if (!prefs.isA9()) {
            a9.setText("30");
        }

        final ImageTextButton.ImageTextButtonStyle textButtonStyle10 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle10.up = skin.getDrawable("shop");
        textButtonStyle10.font = font;
        textButtonStyle10.fontColor = Color.GOLD;
        if (!prefs.isA10()) {
            textButtonStyle10.imageUp = skin.getDrawable("coin");//40
        }
        else {
            if (prefs.getDown() != 3) {
                textButtonStyle10.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle10.imageUp = skin.getDrawable("wear");
            }
        }
        Image i10 = new Image(skin.getDrawable("slot"));
        Image s10 = new Image(skin.getDrawable("A10"));
        final ImageTextButton a10 = new ImageTextButton("", textButtonStyle10);
        if (!prefs.isA10()) {
            a10.setText("40");
        }

        final ImageTextButton.ImageTextButtonStyle textButtonStyle11 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle11.up = skin.getDrawable("shop");
        textButtonStyle11.font = font;
        textButtonStyle11.fontColor = Color.GOLD;
        if (!prefs.isA11()) {
            textButtonStyle11.imageUp = skin.getDrawable("coin");//25
        }
        else {
            if (prefs.getDown() != 4) {
                textButtonStyle11.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle11.imageUp = skin.getDrawable("wear");
            }
        }
        Image i11 = new Image(skin.getDrawable("slot"));
        Image s11 = new Image(skin.getDrawable("A11"));
        final ImageTextButton a11 = new ImageTextButton("", textButtonStyle11);
        if (!prefs.isA11()) {
            a11.setText("25");
        }

        a8.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA8()) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setDown(1);
                    textButtonStyle8.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA9()) textButtonStyle9.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA10()) textButtonStyle10.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA11()) textButtonStyle11.imageUp = (skin.getDrawable("wearing"));
                }
                else {
                    if (prefs.getScore() >= 20) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-20);
                        prefs.setA8();
                        a8.setText("");
                        textButtonStyle8.imageUp = (skin.getDrawable("wearing"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i8.setPosition(20 + 620*8, -450);
        s8.setPosition(60 + 620*8, -250);
        a8.setPosition(120 + 620*8, -400);

        a9.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA9()) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setDown(2);
                    textButtonStyle0.up = skin.getDrawable("wearing");
                    if (prefs.isA8()) textButtonStyle8.imageUp = (skin.getDrawable("wearing"));
                    textButtonStyle9.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA10()) textButtonStyle10.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA11()) textButtonStyle11.imageUp = (skin.getDrawable("wearing"));
                }
                else {
                    if (prefs.getScore() >= 30) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-30);
                        prefs.setA9();
                        a9.setText("");
                        textButtonStyle9.imageUp = (skin.getDrawable("wearing"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i9.setPosition(20 + 620*9, -450);
        s9.setPosition(60 + 620*9, -250);
        a9.setPosition(120 + 620*9, -400);

        a10.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA10()) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setDown(3);
                    textButtonStyle0.up = skin.getDrawable("wearing");
                    if (prefs.isA8()) textButtonStyle8.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA9()) textButtonStyle9.imageUp = (skin.getDrawable("wearing"));
                    textButtonStyle10.imageUp = (skin.getDrawable("wear"));
                    if (prefs.isA11()) textButtonStyle11.imageUp = (skin.getDrawable("wearing"));
                }
                else {
                    if (prefs.getScore() >= 40) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-40);
                        prefs.setA10();
                        a10.setText("");
                        textButtonStyle10.imageUp = (skin.getDrawable("wearing"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i10.setPosition(20 + 620*10, -450);
        s10.setPosition(60 + 620*10, -250);
        a10.setPosition(120 + 620*10, -400);

        a11.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.isA11()) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setDown(4);
                    textButtonStyle0.up = skin.getDrawable("wearing");
                    if (prefs.isA8()) textButtonStyle8.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA9()) textButtonStyle9.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA10()) textButtonStyle10.imageUp = (skin.getDrawable("wearing"));
                    textButtonStyle11.imageUp = (skin.getDrawable("wear"));
                }
                else {
                    if (prefs.getScore() >= 25) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-25);
                        prefs.setA11();
                        a11.setText("");
                        textButtonStyle11.imageUp = (skin.getDrawable("wearing"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i11.setPosition(20 + 620*11, -450);
        s11.setPosition(60 + 620*11, -250);
        a11.setPosition(120 + 620*11, -400);

        a12.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.getA12() == 0 && prefs.getScore() >= 10) {
                    if (assetManager.isLoaded(pathCoin)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathCoin, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setA12(1);//20
                    prefs.setScore(-10);
                    a12.setText("20");
                    label.setText(chosenLanguage[14]);
                    label.setVisible(true);
                    setTimer();
                }
                else if (prefs.getA12() == 1 && prefs.getScore() >= 20) {
                    if (assetManager.isLoaded(pathCoin)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathCoin, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setA12(2);//40
                    prefs.setScore(-20);
                    a12.setText("40");
                    label.setText(chosenLanguage[14]);
                    label.setVisible(true);
                    setTimer();
                }
                else if (prefs.getA12() == 2 && prefs.getScore() >= 40) {
                    if (assetManager.isLoaded(pathCoin)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathCoin, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setA12(3);
                    textButtonStyle12.imageUp = (skin.getDrawable("wearing"));
                    prefs.setScore(-40);
                    a12.setText("");
                    label.setText(chosenLanguage[14]);
                    label.setVisible(true);
                    s12.setDrawable(skin.getDrawable("A12G"));
                    setTimer();
                }
                else if (prefs.getA12() == 3) {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setTop(8);
                    textButtonStyle0.up = skin.getDrawable("wearing");
                    if (prefs.isA1()) textButtonStyle1.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA2()) textButtonStyle2.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA3()) textButtonStyle3.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA4()) textButtonStyle4.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA5()) textButtonStyle5.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA6()) textButtonStyle6.imageUp = (skin.getDrawable("wearing"));
                    if (prefs.isA7()) textButtonStyle7.imageUp = (skin.getDrawable("wearing"));
                    textButtonStyle12.imageUp = (skin.getDrawable("wear"));
                }
                else {
                    if (assetManager.isLoaded(pathErr)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathErr, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    label.setText(chosenLanguage[15]);
                    label.setVisible(true);
                    setTimer();
                }
            }
        });
        i12.setPosition(20 + 620*12, -450);
        s12.setPosition(60 + 620*12, -250);
        a12.setPosition(120 + 620*12, -400);

        final ImageTextButton.ImageTextButtonStyle textButtonStyle17 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle17.up = skin.getDrawable("shop");
        textButtonStyle17.font = font;
        textButtonStyle17.fontColor = Color.GOLD;
        if (!prefs.isA17()) {
            textButtonStyle17.imageUp = skin.getDrawable("coin");//60
        }
        else {
            if (prefs.getBg() != 1) {
                textButtonStyle17.imageUp = skin.getDrawable("wearing");
            }
            else {
                textButtonStyle17.imageUp = skin.getDrawable("wear");
            }
        }
        Image i17 = new Image(skin.getDrawable("slot"));
        Image s17 = new Image(skin.getDrawable("A17"));
        final ImageTextButton a17 = new ImageTextButton("", textButtonStyle17);
        if (!prefs.isA17()) {
            a17.setText("60");
        }

        final ImageTextButton.ImageTextButtonStyle textButtonStyle18 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle18.up = skin.getDrawable("shop");
        textButtonStyle18.font = font;
        textButtonStyle18.fontColor = Color.GOLD;
        if (prefs.getBg() != 0) {
            textButtonStyle18.imageUp = skin.getDrawable("wearing");
        }
        else {
            textButtonStyle18.imageUp = skin.getDrawable("wear");
        }
        Image i18 = new Image(skin.getDrawable("slot"));
        Image s18 = new Image(skin.getDrawable("A18"));
        final ImageTextButton a18 = new ImageTextButton("", textButtonStyle18);

        a17.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!prefs.isA17()) {
                    if (prefs.getScore() >= 60) {
                        if (assetManager.isLoaded(pathCoin)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathCoin, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        prefs.setScore(-60);
                        prefs.setA17();
                        a17.setText("");
                        textButtonStyle17.imageUp = (skin.getDrawable("wearing"));
                        label.setText(chosenLanguage[14]);
                    }
                    else {
                        if (assetManager.isLoaded(pathErr)) {
                            try {
                                if (musicB.isPlaying()) musicB.stop();
                            } catch (Exception ignore) {}
                            musicB = assetManager.get(pathErr, Music.class);
                            musicB.setVolume(1.0f);
                            musicB.play();
                        }
                        label.setText(chosenLanguage[15]);
                    }
                    label.setVisible(true);
                    setTimer();
                }
                else {
                    if (assetManager.isLoaded(pathBtn)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathBtn, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setBg(1);
                    img = new Texture(Gdx.files.internal("City.png"));
                    textButtonStyle18.imageUp = (skin.getDrawable("wearing"));
                    textButtonStyle17.imageUp = (skin.getDrawable("wear"));
                }
            }
        });
        i17.setPosition(20 + 620*13, -450);
        s17.setPosition(60 + 620*13, -250);
        a17.setPosition(120 + 620*13, -400);

        a18.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (assetManager.isLoaded(pathBtn)) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                prefs.setBg(0);
                img = new Texture(Gdx.files.internal("Landscape.png"));
                if (prefs.isA17()) textButtonStyle17.imageUp = (skin.getDrawable("wearing"));
                textButtonStyle18.imageUp = (skin.getDrawable("wear"));
            }
        });
        i18.setPosition(20 + 620*14, -450);
        s18.setPosition(60 + 620*14, -250);
        a18.setPosition(120 + 620*14, -400);

        ImageTextButton.ImageTextButtonStyle textButtonStyle13 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle13.up = skin.getDrawable("shop");
        textButtonStyle13.font = font;
        textButtonStyle13.fontColor = Color.GOLD;
        textButtonStyle13.imageUp = skin.getDrawable("coin");
        Image i13 = new Image(skin.getDrawable("slot"));
        Image s13 = new Image(skin.getDrawable("A13"));
        final ImageTextButton a13 = new ImageTextButton("10", textButtonStyle13);//10
        a13.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.getScore() >= 10) {
                    if (assetManager.isLoaded(pathCoin)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathCoin, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setScore(-10);
                    prefs.setAmountMagnetBooster(1);
                    label.setText(chosenLanguage[14]);
                }
                else {
                    if (assetManager.isLoaded(pathErr)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathErr, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    label.setText(chosenLanguage[15]);
                }
                label.setVisible(true);
                setTimer();
            }
        });
        i13.setPosition(20 + 620*15, -450);
        s13.setPosition(60 + 620*15 + 128, -128);
        a13.setPosition(120 + 620*15, -400);

        ImageTextButton.ImageTextButtonStyle textButtonStyle14 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle14.up = skin.getDrawable("shop");
        textButtonStyle14.font = font;
        textButtonStyle14.fontColor = Color.GOLD;
        textButtonStyle14.imageUp = skin.getDrawable("coin");
        Image i14 = new Image(skin.getDrawable("slot"));
        Image s14 = new Image(skin.getDrawable("A14"));
        final ImageTextButton a14 = new ImageTextButton("12", textButtonStyle14);//12
        a14.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.getScore() >= 12) {
                    if (assetManager.isLoaded(pathCoin)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathCoin, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setScore(-12);
                    prefs.setAmountIncreaseWeightBooster(1);
                    label.setText(chosenLanguage[14]);
                }
                else {
                    if (assetManager.isLoaded(pathErr)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathErr, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    label.setText(chosenLanguage[15]);
                }
                label.setVisible(true);
                setTimer();
            }
        });
        i14.setPosition(20 + 620*16, -450);
        s14.setPosition(60 + 620*16 + 128, -128);
        a14.setPosition(120 + 620*16, -400);

        ImageTextButton.ImageTextButtonStyle textButtonStyle15 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle15.up = skin.getDrawable("shop");
        textButtonStyle15.font = font;
        textButtonStyle15.fontColor = Color.GOLD;
        textButtonStyle15.imageUp = skin.getDrawable("coin");
        Image i15 = new Image(skin.getDrawable("slot"));
        Image s15 = new Image(skin.getDrawable("A15"));
        final ImageTextButton a15 = new ImageTextButton("7", textButtonStyle15);//7
        a15.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (prefs.getScore() >= 7) {
                    if (assetManager.isLoaded(pathCoin)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathCoin, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setScore(-7);
                    prefs.setAmountSpeedBooster(1);
                    label.setText(chosenLanguage[14]);
                }
                else {
                    if (assetManager.isLoaded(pathErr)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathErr, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    label.setText(chosenLanguage[15]);
                }
                label.setVisible(true);
                setTimer();
            }
        });
        i15.setPosition(20 + 620*17, -450);
        s15.setPosition(60 + 620*17 + 128, -128);
        a15.setPosition(120 + 620*17, -400);

        ImageButton.ImageButtonStyle textButtonStyle16 = new ImageButton.ImageButtonStyle();
        textButtonStyle16.up = skin.getDrawable("A16");
        final ImageButton a16 = new ImageButton(textButtonStyle16);
        a16.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!isDay) {
                    if (assetManager.isLoaded(pathCoin)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathCoin, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    isDay = true;
                    prefs.setScore(10);
                    prefs.setForRecycled(10);
                }

                if (isET) {
                    if (assetManager.isLoaded(pathCoin)) {
                        try {
                            if (musicB.isPlaying()) musicB.stop();
                        } catch (Exception ignore) {}
                        musicB = assetManager.get(pathCoin, Music.class);
                        musicB.setVolume(1.0f);
                        musicB.play();
                    }
                    prefs.setScore(100000);
                    prefs.setForRecycled(100);
                }

                label.setText(chosenLanguage[16]);
                label.setVisible(true);
                setTimer();

                n = 0;
                for (int i = 0; i < String.valueOf(prefs.getScore()).length(); i++) {
                    n++;
                }

                if (!isET) Gdx.net.openURI("https://qiwi.com/n/RIAND915");

                if (isET) isET = false;
            }
        });
        a16.setPosition(20 + 620*18, -450);

        a0.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.setTop(0);
                prefs.setDown(0);
                textButtonStyle0.up = skin.getDrawable("wear");
                if (prefs.isA1()) textButtonStyle1.imageUp = (skin.getDrawable("wearing"));
                if (prefs.isA2()) textButtonStyle2.imageUp = (skin.getDrawable("wearing"));
                if (prefs.isA3()) textButtonStyle3.imageUp = (skin.getDrawable("wearing"));
                if (prefs.isA4()) textButtonStyle4.imageUp = (skin.getDrawable("wearing"));
                if (prefs.isA5()) textButtonStyle5.imageUp = (skin.getDrawable("wearing"));
                if (prefs.isA6()) textButtonStyle6.imageUp = (skin.getDrawable("wearing"));
                if (prefs.isA7()) textButtonStyle7.imageUp = (skin.getDrawable("wearing"));
                if (prefs.isA8()) textButtonStyle8.imageUp = (skin.getDrawable("wearing"));
                if (prefs.isA9()) textButtonStyle9.imageUp = (skin.getDrawable("wearing"));
                if (prefs.isA10()) textButtonStyle10.imageUp = (skin.getDrawable("wearing"));
                if (prefs.isA11()) textButtonStyle11.imageUp = (skin.getDrawable("wearing"));
                if (prefs.getA12() == 3) textButtonStyle12.imageUp = (skin.getDrawable("wearing"));
            }
        });
        i0.setPosition(20, -450);
        s0.setPosition(120, -200);
        a0.setPosition(120, -400);

        group1.addActor(i0);
        group1.addActor(i1);
        group1.addActor(i2);
        group1.addActor(i3);
        group1.addActor(i4);
        group1.addActor(i5);
        group1.addActor(i6);
        group1.addActor(i7);
        group1.addActor(i8);
        group1.addActor(i9);
        group1.addActor(i10);
        group1.addActor(i11);
        group1.addActor(i12);
        group1.addActor(i13);
        group1.addActor(i14);
        group1.addActor(i15);
        group1.addActor(i17);
        group1.addActor(i18);
        group1.addActor(s0);
        group1.addActor(s1);
        group1.addActor(s2);
        group1.addActor(s3);
        group1.addActor(s4);
        group1.addActor(s5);
        group1.addActor(s6);
        group1.addActor(s7);
        group1.addActor(s8);
        group1.addActor(s9);
        group1.addActor(s10);
        group1.addActor(s11);
        group1.addActor(s12);
        group1.addActor(s13);
        group1.addActor(s14);
        group1.addActor(s15);
        group1.addActor(s17);
        group1.addActor(s18);
        group1.addActor(a0);
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
        ImageButton.ImageButtonStyle textButtonStyleC1 = new ImageButton.ImageButtonStyle();
        textButtonStyleC1.up = skin.newDrawable("cloth");
        final ImageButton textButton1 = new ImageButton(textButtonStyleC1);
        textButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (assetManager.isLoaded(pathBtn)) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                if (stage.getCamera().position.x != 0) scrollSpeed = -500f;
            }
        });
        ImageButton.ImageButtonStyle textButtonStyleC2 = new ImageButton.ImageButtonStyle();
        textButtonStyleC2.up = skin.newDrawable("buster");
        final ImageButton textButton2 = new ImageButton(textButtonStyleC2);
        textButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (assetManager.isLoaded(pathBtn)) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                scrollSpeed = count(stage.getCamera().position.x);
                btn2 = true;
            }
        });
        ImageButton.ImageButtonStyle textButtonStyleC3 = new ImageButton.ImageButtonStyle();
        textButtonStyleC3.up = skin.newDrawable("support");
        final ImageButton textButton3 = new ImageButton(textButtonStyleC3);
        textButton3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (assetManager.isLoaded(pathBtn)) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                if (stage.getCamera().position.x != 8700) scrollSpeed = 500f;

                if (et == 0) setETimer();
                et++;
            }
        });
        textButton1.setPosition(-510, 140);
        textButton2.setPosition(-150, 140);
        textButton3.setPosition(215, 140);
        group2.addActor(textButton1);
        group2.addActor(textButton2);
        group2.addActor(textButton3);
        group2.setPosition((Width>>1), Height-(Height>>2));
        stage.addActor(group2);
        stage.addActor(label);

        ImageButton.ImageButtonStyle textButtonStyleX = new ImageButton.ImageButtonStyle();
        textButtonStyleX.up = skin.newDrawable("exit");
        final ImageButton imgBtn = new ImageButton(textButtonStyleX);
        imgBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
                stage.getRoot().getColor().a = 1;
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.sequence(
                        Actions.alpha(0.1f, 0.2f)));
                sequenceAction.addAction(run(new Runnable() {
                    @Override
                    public void run() {
                        ScreenManager.getInstance().setScreen(new MenuScreen(2));
                    }
                }));
                stage.getRoot().addAction(sequenceAction);
                stage.getCamera().position.set(Width>>1, Height>>1, 0);
            }
        });
        imgBtn.setPosition((Width>>1)-150, -90);
        group.addActor(imgBtn);
        group.setPosition(stage.getCamera().position.x, Height - (Height >> 5));
        stage.addActor(group);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(gestureDetector);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);

        if (assetManager.isLoaded(pathBG)) {
            musicBG = assetManager.get(pathBG, Music.class);
            musicBG.setVolume(1.0f);
            musicBG.play();
        }

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
                    Thread.sleep(2500);
                    label.setVisible(false);
                } catch (InterruptedException ignored) {}
            }
        };
        thread.start();
    }

    private void setETimer() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    et = 0;
                } catch (InterruptedException ignored) {}
            }
        };
        thread1.start();
    }

    /**
     * Function for calculation distance from start point to start point of boosters
     *
     * @param pos - position
     * @return - returns speed
     */
    private float count(float pos) {
        float ans = 0;
        if (pos < 10270) {
            while (pos < 10270) {
                pos += ans;
                ans += 10;
                if (pos == 10270) return ans;
            }
            return ans-19;
        }
        else if (pos > 10270){
            while (pos > 10270) {
                pos += ans;
                ans -= 10;
                if (pos == 10270) return ans;
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

        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(img, stage.getCamera().position.x - (Width >> 1), 0);
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

//        //*Test
//        font24.getData().setScale(1f);
//        font24.setColor(Color.WHITE);
//        font24.draw(stage.getBatch(), Width + " " + Height, Width >> 1, Height>>1);
//        font24.draw(stage.getBatch(), stage.getCamera().viewportWidth + " " + stage.getCamera().viewportHeight, Width >> 1, (Height>>1)-40);
//        font24.draw(stage.getBatch(), t.getWidth() + " " + t.getHeight(), Width >> 1, (Height>>1)-80);
//        Texture texture1 = new Texture(Gdx.files.internal("Slot.png"));
//        font24.draw(stage.getBatch(), texture1.getWidth() + " " + texture1.getHeight(), Width >> 1, (Height>>1)-120);
//        font24.draw(stage.getBatch(), stage.getWidth() + " " + stage.getHeight(), Width >> 1, (Height>>1)-160);

        stage.getBatch().end();
    }

    /**
     * Update function
     * @param dt - delta time
     */
    public void update(float dt) {
        stage.act(dt);

        float newPosX = (stage.getCamera().position.x + scrollSpeed), gp = group.getX() + scrollSpeed;
        float MAX_SCROLL = 11800.0f;
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
            group.setPosition(10270, group.getY());
            group2.setPosition(10270, group2.getY());
            stage.getCamera().position.set(10270, stage.getCamera().position.y, 0);
            stage.getCamera().update();
            btn2 = false;
        }

        if (et==3) {
            isET = true;
            et = 0;
        }
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
            font24.dispose();
            stage.dispose();
            t.dispose();
            t1.dispose();
            t2.dispose();
            musicB.dispose();
            musicBG.dispose();
            assetManager.dispose();
        }
        catch (Exception ignore) {}
    }
}
