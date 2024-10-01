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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ms.game.AbstractScreen;
import com.ms.game.save.ChallengePrefs;
import com.ms.game.save.Prefs;
import com.ms.game.ScreenManager;

public class ChallengeScreen extends AbstractScreen {
    /**
     * Challenge screen
     *
     * Created by MS
     */
    private ChallengePrefs challengePrefs;
    private Prefs prefs;
    private Stage stage;
    private String[] chosenLanguage;
    private Texture t1, t2, t3, t4, texture, texture1;
    private BitmapFont font;
    private float scrollSpeed = 0.0f;
    private final float w = ((int) ScreenManager.getInstance().getViewport().getWorldHeight())>>1;
    private ImageButton textButton;
    private ImageButton textButton1, textButton2;
    public int  h = 0, m = 0;
    private final int Width = (int) ScreenManager.getInstance().getViewport().getWorldWidth();
    private final int Height = (int) ScreenManager.getInstance().getViewport().getWorldHeight();
    private Music musicB, music, musicBG;
    private AssetManager assetManager;
    private final String pathBtn = String.valueOf(Gdx.files.internal("btn.mp3")), pathBG = String.valueOf(Gdx.files.internal("text.mp3")),
            pathCoin = String.valueOf(Gdx.files.internal("getCoin.mp3"));
    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\\\/?-+=()*&.;,{}\\\"´`'<>";


    /**
     * Challenge screen constructor
     */
    public ChallengeScreen() {}

    /**
     * Initialization and creating button
     */
    @Override
    public void show() {
        challengePrefs = new ChallengePrefs();
        prefs = new Prefs();
        stage = new Stage();
        t1 = new Texture(Gdx.files.internal("magnetR.png"));
        t2 = new Texture(Gdx.files.internal("x2bonusR.png"));
        t3 = new Texture(Gdx.files.internal("accelerationR.png"));
        t4 = new Texture(Gdx.files.internal("Coin.png"));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("comic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARACTERS;
        parameter.size = 32; // font size
        font = generator.generateFont(parameter);
        generator.dispose(); // avoid memory leaks, important
        texture = new Texture(Gdx.files.internal("check.png"));
        if (prefs.getBg() == 0) texture1 = new Texture(Gdx.files.internal("Landscape.png"));
        else texture1 = new Texture(Gdx.files.internal("City.png"));
        setLang(prefs.getLang());
        chosenLanguage = language[getLang()];
        String[] iLanguage = challengeLang[getLang()];

        stage.setViewport(ScreenManager.getInstance().getViewport());
        stage.getRoot().getColor().a = 0;
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));

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
                scrollSpeed = deltaY;
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

        Group group = new Group();
        Skin skin = new Skin();
        Texture texture = new Texture(Gdx.files.internal("ButtonDesign1.png"));
        skin.add("button1", texture);
        texture = new Texture(Gdx.files.internal("ExitS.png"));
        skin.add("exit", texture);
        texture = new Texture(Gdx.files.internal("upbtn.png"));
        skin.add("up", texture);
        texture = new Texture(Gdx.files.internal("downbtn.png"));
        skin.add("down", texture);
        texture = new Texture(Gdx.files.internal("ChallS.png"));
        skin.add("button2", texture);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = skin.getDrawable("button2");
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        assetManager = new AssetManager();
        if (!prefs.isVolumeOff()) {
            assetManager.load(pathBtn, Music.class);
            assetManager.load(pathCoin, Music.class);
        }
        if (!prefs.isMelOff()) {
            assetManager.load(pathBG, Music.class);
        }
        assetManager.finishLoading();
        checkConditions();

        Label label1 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[3] + iLanguage[2]), labelStyle);
        Label label2 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[4] + iLanguage[2]), labelStyle);
        Label label3 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[5] + iLanguage[2]), labelStyle);
        Label label4 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[6] + iLanguage[2]), labelStyle);
        Label label5 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[7] + iLanguage[2]), labelStyle);
        Label label6 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[8] + iLanguage[2]), labelStyle);
        Label label7 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[11] + iLanguage[10]), labelStyle);
        Label label8 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[12] + iLanguage[10]), labelStyle);
        Label label9 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[13] + iLanguage[10]), labelStyle);
        Label label10 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[14] + iLanguage[10]), labelStyle);
        Label label11 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[15] + iLanguage[10]), labelStyle);
        Label label12 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[16] + iLanguage[10]), labelStyle);
        Label label13 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[3] + iLanguage[18]), labelStyle);
        Label label14 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[4] + iLanguage[18]), labelStyle);
        Label label15 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[5] + iLanguage[18]), labelStyle);
        Label label16 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[6] + iLanguage[18]), labelStyle);
        Label label17 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[7] + iLanguage[18]), labelStyle);
        Label label18 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[8] + iLanguage[18]), labelStyle);
        Label label19 = new Label(iLanguage[19], labelStyle);
        Label label20 = new Label(iLanguage[20], labelStyle);
        Label label21 = new Label((iLanguage[21] + h + ":" + m + iLanguage[1] + iLanguage[25] + iLanguage[26]), labelStyle);
        Label label22 = new Label((iLanguage[21] + h + ":" + m + iLanguage[1] + iLanguage[23] + iLanguage[24]), labelStyle);
        Label label23 = new Label((iLanguage[21] + h + ":" + m + iLanguage[1] + iLanguage[23] + iLanguage[24] + iLanguage[25] + iLanguage[26]), labelStyle);
        Label label24;
        if (prefs.getLang() == 1) label24= new Label((iLanguage[21] + h + ":" + m + iLanguage[1] + iLanguage[27] + iLanguage[22]), labelStyle);
        else label24 = new Label((iLanguage[21] + h + ":" + m + iLanguage[1] + iLanguage[27] + iLanguage[28]), labelStyle);
        Label label25;
        if (prefs.getLang() == 1) label25 = new Label((iLanguage[21] + h + ":" + m + iLanguage[1] + iLanguage[11] + iLanguage[22]), labelStyle);
        else label25 = new Label((iLanguage[21] + h + ":" + m + iLanguage[1] + iLanguage[11] + iLanguage[31]), labelStyle);
        Label label26 = new Label((iLanguage[21] + h + ":" + m + iLanguage[1] + iLanguage[12] + iLanguage[22]), labelStyle);
        Label label27 = new Label(iLanguage[28], labelStyle);
        Label label28 = new Label((iLanguage[29] + prefs.getRecycledPaper() + iLanguage[1] + iLanguage[30] + iLanguage[2]), labelStyle);
        Label label29 = new Label((iLanguage[29] + prefs.getRecycledPaper() + iLanguage[1] + iLanguage[4] + iLanguage[2]), labelStyle);
        Label label30 = new Label((iLanguage[29] + prefs.getRecycledPaper() + iLanguage[1] + iLanguage[5] + iLanguage[2]), labelStyle);
        Label label31 = new Label((iLanguage[29] + prefs.getRecycledPaper() + iLanguage[1] + iLanguage[6] + iLanguage[2]), labelStyle);
        Label label32 = new Label((iLanguage[29] + prefs.getRecycledPaper() + iLanguage[1] + iLanguage[7] + iLanguage[2]), labelStyle);
        Label label33 = new Label((iLanguage[29] + prefs.getRecycledPaper() + iLanguage[1] + iLanguage[8] + iLanguage[2]), labelStyle);

        label1.setAlignment(Align.center);
        label2.setAlignment(Align.center);
        label3.setAlignment(Align.center);
        label4.setAlignment(Align.center);
        label5.setAlignment(Align.center);
        label6.setAlignment(Align.center);
        label7.setAlignment(Align.center);
        label8.setAlignment(Align.center);
        label9.setAlignment(Align.center);
        label10.setAlignment(Align.center);
        label11.setAlignment(Align.center);
        label12.setAlignment(Align.center);
        label13.setAlignment(Align.center);
        label14.setAlignment(Align.center);
        label15.setAlignment(Align.center);
        label16.setAlignment(Align.center);
        label17.setAlignment(Align.center);
        label18.setAlignment(Align.center);
        label19.setAlignment(Align.center);
        label20.setAlignment(Align.center);
        label21.setAlignment(Align.center);
        label22.setAlignment(Align.center);
        label23.setAlignment(Align.center);
        label24.setAlignment(Align.center);
        label25.setAlignment(Align.center);
        label26.setAlignment(Align.center);
        label27.setAlignment(Align.center);
        label28.setAlignment(Align.center);
        label29.setAlignment(Align.center);
        label30.setAlignment(Align.center);
        label31.setAlignment(Align.center);
        label32.setAlignment(Align.center);
        label33.setAlignment(Align.center);

        label1.setWidth(Width-270);
        label2.setWidth(Width-270);
        label3.setWidth(Width-270);
        label4.setWidth(Width-270);
        label5.setWidth(Width-270);
        label6.setWidth(Width-270);
        label7.setWidth(Width-270);
        label8.setWidth(Width-270);
        label9.setWidth(Width-270);
        label10.setWidth(Width-270);
        label11.setWidth(Width-270);
        label12.setWidth(Width-270);
        label13.setWidth(Width-270);
        label14.setWidth(Width-270);
        label15.setWidth(Width-270);
        label16.setWidth(Width-270);
        label17.setWidth(Width-270);
        label18.setWidth(Width-270);
        label19.setWidth(Width-270);
        label20.setWidth(Width-270);
        label21.setWidth(Width-270);
        label22.setWidth(Width-270);
        label23.setWidth(Width-270);
        label24.setWidth(Width-270);
        label25.setWidth(Width-270);
        label26.setWidth(Width-270);
        label27.setWidth(Width-270);
        label28.setWidth(Width-270);
        label29.setWidth(Width-270);
        label30.setWidth(Width-270);
        label31.setWidth(Width-270);
        label32.setWidth(Width-270);
        label33.setWidth(Width-270);

        label1.setPosition(20, Height-110);
        label2.setPosition(20, Height-220);
        label3.setPosition(20, Height-330);
        label4.setPosition(20, Height-440);
        label5.setPosition(20, Height-550);
        label6.setPosition(20, Height-660);
        label7.setPosition(20, Height-770);
        label8.setPosition(20, Height-880);
        label9.setPosition(20, Height-990);
        label10.setPosition(20, Height-1100);
        label11.setPosition(20, Height-1210);
        label12.setPosition(20, Height-1320);
        label13.setPosition(20, Height-1430);
        label14.setPosition(20, Height-1540);
        label15.setPosition(20, Height-1650);
        label16.setPosition(20, Height-1760);
        label17.setPosition(20, Height-1870);
        label18.setPosition(20, Height-1980);
        label19.setPosition(20, Height-2090);
        label20.setPosition(20, Height-2200);
        label21.setPosition(20, Height-2310);
        label22.setPosition(20, Height-2420);
        label23.setPosition(20, Height-2530);
        label24.setPosition(20, Height-2640);
        label25.setPosition(20, Height-2750);
        label26.setPosition(20, Height-2860);
        label27.setPosition(20, Height-2970);
        label28.setPosition(20, Height-3080);
        label29.setPosition(20, Height-3190);
        label30.setPosition(20, Height-3300);
        label31.setPosition(20, Height-3410);
        label32.setPosition(20, Height-3520);
        label33.setPosition(20, Height-3630);

        group.addActor(label1);
        group.addActor(label2);
        group.addActor(label3);
        group.addActor(label4);
        group.addActor(label5);
        group.addActor(label6);
        group.addActor(label7);
        group.addActor(label8);
        group.addActor(label9);
        group.addActor(label10);
        group.addActor(label11);
        group.addActor(label12);
        group.addActor(label13);
        group.addActor(label14);
        group.addActor(label15);
        group.addActor(label16);
        group.addActor(label17);
        group.addActor(label18);
        group.addActor(label19);
        group.addActor(label20);
        group.addActor(label21);
        group.addActor(label22);
        group.addActor(label23);
        group.addActor(label24);
        group.addActor(label25);
        group.addActor(label26);
        group.addActor(label27);
        group.addActor(label28);
        group.addActor(label29);
        group.addActor(label30);
        group.addActor(label31);
        group.addActor(label32);
        group.addActor(label33); //*Adding all challenges

        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.up = skin.getDrawable("exit");
        textButton = new ImageButton(buttonStyle);
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
                        ScreenManager.getInstance().setScreen(new MenuScreen(4));
                    }
                }));
                stage.getRoot().addAction(sequenceAction);
                stage.getCamera().position.set(Width>>1, Height>>1, 0);
            }
        });
        textButton.setPosition(Width-200, 0);
        stage.addActor(textButton);

        ImageButton.ImageButtonStyle buttonStyle1 = new ImageButton.ImageButtonStyle();
        buttonStyle1.down = skin.getDrawable("up");
        buttonStyle1.up = skin.getDrawable("up");
        textButton1 = new ImageButton(buttonStyle1);
        textButton1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (assetManager.isLoaded(pathBtn)) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                scrollSpeed = 250f;
            }
        });
        textButton1.setPosition(Width-240, 0);
        stage.addActor(textButton1);

        ImageButton.ImageButtonStyle buttonStyle2 = new ImageButton.ImageButtonStyle();
        buttonStyle2.down = skin.getDrawable("down");
        buttonStyle2.up = skin.getDrawable("down");
        textButton2 = new ImageButton(buttonStyle2);
        textButton2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (assetManager.isLoaded(pathBtn)) {
                    try {
                        if (musicB.isPlaying()) musicB.stop();
                    } catch (Exception ignore) {}
                    musicB = assetManager.get(pathBtn, Music.class);
                    musicB.setVolume(1.0f);
                    musicB.play();
                }
                scrollSpeed = -250f;
            }
        });
        textButton2.setPosition(Width-240, 0);
        stage.addActor(textButton2);
        stage.addActor(group);

        stage.getCamera().position.set(stage.getCamera().position.x, Height - w, 0);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(gestureDetector);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);

        if (assetManager.isLoaded(pathBG)) {
            musicBG = assetManager.get(pathBG, Music.class);
            musicBG.setVolume(1.0f);
            musicBG.play();
        }
    }

    /**
     * Function for checking conditions and getting rewards
     */
    public void checkConditions() {
        String s = challengePrefs.getTimeInGame();
        int r = 0, l = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ':' && r == 0) {
                h = Integer.parseInt(s.substring(0, i));
                l = i + 1;
                r++;
            }
            else if (s.charAt(i) == ':' && r == 1) {
                m = Integer.parseInt(s.substring(l, i));
                l = i + 1;
            }
        }

        if (!challengePrefs.isM1()) {
            if (prefs.getCollectedPaper() >= 1000) {
                getSoundCoin();
                prefs.setAmountIncreaseWeightBooster(1);
                challengePrefs.setM1();
            }
        }
        if (!challengePrefs.isM2()) {
            if (prefs.getCollectedPaper() >= 5000) {
                getSoundCoin();
                prefs.setAmountIncreaseWeightBooster(3);
                challengePrefs.setM2();
            }
        }
        if (!challengePrefs.isM3()) {
            if (prefs.getCollectedPaper() >= 10000) {
                getSoundCoin();
                prefs.setAmountIncreaseWeightBooster(5);
                challengePrefs.setM3();
            }
        }
        if (!challengePrefs.isM4()) {
            if (prefs.getCollectedPaper() >= 25000) {
                getSoundCoin();
                prefs.setAmountIncreaseWeightBooster(10);
                challengePrefs.setM4();
            }
        }
        if (!challengePrefs.isM5()) {
            if (prefs.getCollectedPaper() >= 50000) {
                getSoundCoin();
                prefs.setAmountIncreaseWeightBooster(20);
                challengePrefs.setM5();
            }
        }
        if (!challengePrefs.isM6()) {
            if (prefs.getCollectedPaper() >= 100000) {
                getSoundCoin();
                prefs.setAmountIncreaseWeightBooster(100);
                challengePrefs.setM6();
            }
        } //* Collected paper
        if (!challengePrefs.isM7()) {
            if (prefs.getUsedBusters() >= 3) {
                getSoundCoin();
                prefs.setScore(10);
                challengePrefs.setM7();
            }
        }
        if (!challengePrefs.isM8()) {
            if (prefs.getUsedBusters() >= 5) {
                getSoundCoin();
                prefs.setScore(20);
                challengePrefs.setM8();
            }
        }
        if (!challengePrefs.isM9()) {
            if (prefs.getUsedBusters() >= 10) {
                getSoundCoin();
                prefs.setScore(30);
                challengePrefs.setM9();
            }
        }
        if (!challengePrefs.isM10()) {
            if (prefs.getUsedBusters() >= 20) {
                getSoundCoin();
                prefs.setScore(50);
                challengePrefs.setM10();
            }
        }
        if (!challengePrefs.isM11()) {
            if (prefs.getUsedBusters() >= 30) {
                getSoundCoin();
                prefs.setScore(70);
                challengePrefs.setM11();
            }
        }
        if (!challengePrefs.isM12()) {
            if (prefs.getUsedBusters() >= 50) {
                getSoundCoin();
                prefs.setScore(100);
                challengePrefs.setM12();
            }
        } //* Used busters
        if (!challengePrefs.isM13()) {
            if (prefs.getJumps() >= 1000) {
                getSoundCoin();
                prefs.setAmountMagnetBooster(1);
                challengePrefs.setM13();
            }
        }
        if (!challengePrefs.isM14()) {
            if (prefs.getJumps() >= 5000) {
                getSoundCoin();
                prefs.setAmountMagnetBooster(3);
                challengePrefs.setM14();
            }
        }
        if (!challengePrefs.isM15()) {
            if (prefs.getJumps() >= 10000) {
                getSoundCoin();
                prefs.setAmountMagnetBooster(5);
                challengePrefs.setM15();
            }
        }
        if (!challengePrefs.isM16()) {
            if (prefs.getJumps() >= 25000) {
                getSoundCoin();
                prefs.setAmountMagnetBooster(10);
                challengePrefs.setM16();
            }
        }
        if (!challengePrefs.isM17()) {
            if (prefs.getJumps() >= 50000) {
                getSoundCoin();
                prefs.setAmountMagnetBooster(20);
                challengePrefs.setM17();
            }
        }
        if (!challengePrefs.isM18()) {
            if (prefs.getJumps() >= 100000) {
                getSoundCoin();
                prefs.setAmountMagnetBooster(30);
                challengePrefs.setM18();
            }
        } //* Jumps
        if (!challengePrefs.isM19()) {
            if (prefs.getA12() == 3) {
                getSoundCoin();
                prefs.setScore(100);
                challengePrefs.setM19();
            }
        } //* Unique skin
        if (!challengePrefs.isM20()) {
            if (amountCollectedSkins() >= 5) {
                getSoundCoin();
                prefs.setScore(100);
                challengePrefs.setM20();
            }
        } //* 5 skins
        if (!challengePrefs.isM21()) {
            if (m>=30) {
                getSoundCoin();
                prefs.setAmountSpeedBooster(1);
                challengePrefs.setM21();
            }
        }
        if (!challengePrefs.isM22()) {
            if (h>=1) {
                getSoundCoin();
                prefs.setAmountSpeedBooster(3);
                challengePrefs.setM22();
            }
        }
        if (!challengePrefs.isM23()) {
            if ((h >= 1 && m>=30) || h>=2) {
                getSoundCoin();
                prefs.setAmountSpeedBooster(5);
                challengePrefs.setM23();
            }
        }
        if (!challengePrefs.isM24()) {
            if (h>=2) {
                getSoundCoin();
                prefs.setAmountSpeedBooster(10);
                challengePrefs.setM24();
            }
        }
        if (!challengePrefs.isM25()) {
            if (h>=3) {
                getSoundCoin();
                prefs.setAmountSpeedBooster(20);
                challengePrefs.setM25();
            }
        }
        if (!challengePrefs.isM26()) {
            if (h>=5) {
                getSoundCoin();
                prefs.setAmountSpeedBooster(100);
                challengePrefs.setM26();
            }
        } //* Time
        if (!challengePrefs.isM27()) {
            if (prefs.getStageFactory() >= 4) {
                getSoundCoin();
                prefs.setScore(50);
                challengePrefs.setM27();
            }
        } //* Factory
        if (!challengePrefs.isM28()) {
            if (prefs.getRecycledPaper() >= 500) {
                getSoundCoin();
                prefs.setScore(10);
                challengePrefs.setM28();
            }
        }
        if (!challengePrefs.isM29()) {
            if (prefs.getRecycledPaper() >= 5000) {
                getSoundCoin();
                prefs.setScore(15);
                challengePrefs.setM29();
            }
        }
        if (!challengePrefs.isM30()) {
            if (prefs.getRecycledPaper() >= 10000) {
                getSoundCoin();
                prefs.setScore(30);
                challengePrefs.setM30();
            }
        }
        if (!challengePrefs.isM31()) {
            if (prefs.getCollectedPaper() >= 25000) {
                getSoundCoin();
                prefs.setScore(60);
                challengePrefs.setM31();
            }
        }
        if (!challengePrefs.isM32()) {
            if (prefs.getRecycledPaper() >= 50000) {
                getSoundCoin();
                prefs.setScore(100);
                challengePrefs.setM32();
            }
        }
        if (!challengePrefs.isM33()) {
            if (prefs.getRecycledPaper() >= 100000) {
                getSoundCoin();
                prefs.setScore(150);
                challengePrefs.setM33();
            }
        } //* Recycled paper
    }

    /**
     * Function for playing sound, when player gets something
     */
    private void getSoundCoin() {
        if (assetManager.isLoaded(pathCoin)) {
            music = assetManager.get(pathCoin, Music.class);
            music.setVolume(0.8f);
            music.play();
        }
    }

    /**
     * Function for counting progress in opening skins
     * (I could made it in Prefs.java, but it was the easiest variant at moment of coding)
     *
     * @return - returns amount of skins (integer)
     */
    private int amountCollectedSkins() {
        int amount = 0;
        if (prefs.isA1()) amount++;
        if (prefs.isA2()) amount++;
        if (prefs.isA3()) amount++;
        if (prefs.isA4()) amount++;
        if (prefs.isA5()) amount++;
        if (prefs.isA6()) amount++;
        if (prefs.isA7()) amount++;
        if (prefs.isA8()) amount++;
        if (prefs.isA9()) amount++;
        if (prefs.isA10()) amount++;
        if (prefs.isA11()) amount++;
        if (prefs.getA12() == 3) amount++;
        return amount;
    }

    /**
     * Rendering and updating all objects
     * @param delta - delta time
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);

        //*
        float newPosY = (stage.getCamera().position.y + scrollSpeed);
        float MAX_SCROLL = Height - w;
        float MIN_SCROLL = -2030.0f;
        if (newPosY + scrollSpeed + 10 > MAX_SCROLL) {
            newPosY = MAX_SCROLL + scrollSpeed + 10;
        }
        else if (newPosY < MIN_SCROLL - scrollSpeed - 10) {
            newPosY = MIN_SCROLL - scrollSpeed - 10;
        }
        textButton.setPosition(textButton.getX(), newPosY);
        textButton1.setPosition(textButton1.getX(), newPosY - (Height>>1));
        textButton2.setPosition(textButton2.getX(), newPosY + ((Height>>1)-192));
        stage.getCamera().position.set(stage.getCamera().position.x, newPosY, 0);
        stage.getCamera().update();

        if (scrollSpeed > 0) {
            scrollSpeed -= 10;
            if (scrollSpeed - 10 < 0) scrollSpeed = 0;
        }
        else if (scrollSpeed < 0) {
            scrollSpeed += 10;
            if (scrollSpeed + 10 > 0) scrollSpeed = 0;
        }
        //* Moving on screen

        stage.getBatch().begin();
        stage.getBatch().draw(texture1, 0, -2850);
        stage.getBatch().end();

        stage.draw();

        stage.getBatch().begin();

        if (challengePrefs.isM1()) {
            stage.getBatch().draw(texture, 90, Height-100, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-50);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-50);
            font.draw(stage.getBatch(), "x1", Width-480, Height-50);
            stage.getBatch().draw(t2, Width-400, Height-100, 80, 80);
        } //* Label 1

        if (challengePrefs.isM2()) {
            stage.getBatch().draw(texture, 90, Height-210, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-160);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-160);
            font.draw(stage.getBatch(), "x3", Width-480, Height-160);
            stage.getBatch().draw(t2, Width-400, Height-210, 80, 80);
        } //* Label 2

        if (challengePrefs.isM3()) {
            stage.getBatch().draw(texture, 90, Height-320, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-270);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-270);
            font.draw(stage.getBatch(), "x5", Width-480, Height-270);
            stage.getBatch().draw(t2, Width-400, Height-320, 80, 80);
        } //* Label 3

        if (challengePrefs.isM4()) {
            stage.getBatch().draw(texture, 90, Height-430, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-380);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-380);
            font.draw(stage.getBatch(), "x10", Width-480, Height-380);
            stage.getBatch().draw(t2, Width-400, Height-430, 80, 80);
        } //* Label 4

        if (challengePrefs.isM5()) {
            stage.getBatch().draw(texture, 90, Height-540, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-490);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-490);
            font.draw(stage.getBatch(), "x20", Width-480, Height-490);
            stage.getBatch().draw(t2, Width-400, Height-540, 80, 80);
        } //* Label 5

        if (challengePrefs.isM6()) {
            stage.getBatch().draw(texture, 90, Height-650, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-600);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-600);
            font.draw(stage.getBatch(), "x100", Width-480, Height-600);
            stage.getBatch().draw(t2, Width-400, Height-650, 80, 80);
        } //* Label 6

        if (challengePrefs.isM7()) {
            stage.getBatch().draw(texture, 90, Height-760, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-710);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-710);
            font.draw(stage.getBatch(), "x10", Width-480, Height-710);
            stage.getBatch().draw(t4, Width-400, Height-760, 80, 80);
        } //* Label 7

        if (challengePrefs.isM8()) {
            stage.getBatch().draw(texture, 90, Height-870, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-820);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-820);
            font.draw(stage.getBatch(), "x20", Width-480, Height-820);
            stage.getBatch().draw(t4, Width-400, Height-870, 80, 80);
        } //* Label 8

        if (challengePrefs.isM9()) {
            stage.getBatch().draw(texture, 90, Height-980, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-930);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-930);
            font.draw(stage.getBatch(), "x30", Width-480, Height-930);
            stage.getBatch().draw(t4, Width-400, Height-980, 80, 80);
        } //* Label 9

        if (challengePrefs.isM10()) {
            stage.getBatch().draw(texture, 90, Height-1090, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-1040);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-1040);
            font.draw(stage.getBatch(), "x50", Width-480, Height-1040);
            stage.getBatch().draw(t4, Width-400, Height-1090, 80, 80);
        } //* Label 10

        if (challengePrefs.isM11()) {
            stage.getBatch().draw(texture, 90, Height-1200, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-1150);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-1150);
            font.draw(stage.getBatch(), "x70", Width-480, Height-1150);
            stage.getBatch().draw(t4, Width-400, Height-1200, 80, 80);
        } //* Label 11

        if (challengePrefs.isM12()) {
            stage.getBatch().draw(texture, 90, Height-1310, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-1260);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-1260);
            font.draw(stage.getBatch(), "x100", Width-480, Height-1260);
            stage.getBatch().draw(t4, Width-400, Height-1310, 80, 80);
        } //* Label 12

        if (challengePrefs.isM13()) {
            stage.getBatch().draw(texture, 90, Height-1420, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-1370);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-1370);
            font.draw(stage.getBatch(), "x1", Width-480, Height-1370);
            stage.getBatch().draw(t1, Width-400, Height-1420, 80, 80);
        } //* Label 13

        if (challengePrefs.isM14()) {
            stage.getBatch().draw(texture, 90, Height-1530, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-1480);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-1480);
            font.draw(stage.getBatch(), "x3", Width-480, Height-1480);
            stage.getBatch().draw(t1, Width-400, Height-1530, 80, 80);
        } //* Label 14

        if (challengePrefs.isM15()) {
            stage.getBatch().draw(texture, 90, Height-1640, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-1590);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-1590);
            font.draw(stage.getBatch(), "x5", Width-480, Height-1590);
            stage.getBatch().draw(t1, Width-400, Height-1640, 80, 80);
        } //* Label 15

        if (challengePrefs.isM16()) {
            stage.getBatch().draw(texture, 90, Height-1750, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-1700);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-1700);
            font.draw(stage.getBatch(), "x10", Width-480, Height-1700);
            stage.getBatch().draw(t1, Width-400, Height-1750, 80, 80);
        } //* Label 16

        if (challengePrefs.isM17()) {
            stage.getBatch().draw(texture, 90, Height-1860, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-1810);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-1810);
            font.draw(stage.getBatch(), "x20", Width-480, Height-1810);
            stage.getBatch().draw(t1, Width-400, Height-1860, 80, 80);
        } //* Label 17

        if (challengePrefs.isM18()) {
            stage.getBatch().draw(texture, 90, Height-1970, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-1920);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-1920);
            font.draw(stage.getBatch(), "x30", Width-480, Height-1920);
            stage.getBatch().draw(t1, Width-400, Height-1970, 80, 80);
        } //* Label 18

        if (challengePrefs.isM19()) {
            stage.getBatch().draw(texture, 90, Height-2080, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-2030);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-2030);
            font.draw(stage.getBatch(), "x100", Width-480, Height-2030);
            stage.getBatch().draw(t4, Width-400, Height-2080, 80, 80);
        } //* Label 19

        if (challengePrefs.isM20()) {
            stage.getBatch().draw(texture, 90, Height-2190, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-2140);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-2140);
            font.draw(stage.getBatch(), "x100", Width-480, Height-2140);
            stage.getBatch().draw(t4, Width-400, Height-2190, 80, 80);
        } //* Label 20

        if (challengePrefs.isM21()) {
            stage.getBatch().draw(texture, 90, Height-2300, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-2250);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-2250);
            font.draw(stage.getBatch(), "x1", Width-480, Height-2250);
            stage.getBatch().draw(t3, Width-400, Height-2300, 80, 80);
        } //* Label 21

        if (challengePrefs.isM22()) {
            stage.getBatch().draw(texture, 90, Height-2410, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-2360);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-2360);
            font.draw(stage.getBatch(), "x3", Width-480, Height-2360);
            stage.getBatch().draw(t3, Width-400, Height-2410, 80, 80);
        } //* Label 22

        if (challengePrefs.isM23()) {
            stage.getBatch().draw(texture, 90, Height-2520, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-2470);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-2470);
            font.draw(stage.getBatch(), "x4", Width-480, Height-2470);
            stage.getBatch().draw(t3, Width-400, Height-2520, 80, 80);
        } //* Label 23

        if (challengePrefs.isM24()) {
            stage.getBatch().draw(texture, 90, Height-2630, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-2580);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-2580);
            font.draw(stage.getBatch(), "x10", Width-480, Height-2580);
            stage.getBatch().draw(t3, Width-400, Height-2630, 80, 80);
        } //* Label 24

        if (challengePrefs.isM25()) {
            stage.getBatch().draw(texture, 90, Height-2740, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-2690);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-2690);
            font.draw(stage.getBatch(), "x20", Width-480, Height-2690);
            stage.getBatch().draw(t3, Width-400, Height-2740, 80, 80);
        } //* Label 25

        if (challengePrefs.isM26()) {
            stage.getBatch().draw(texture, 90, Height-2850, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-2800);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-2800);
            font.draw(stage.getBatch(), "x100", Width-480, Height-2800);
            stage.getBatch().draw(t3, Width-400, Height-2850, 80, 80);
        } //* Label 26

        if (challengePrefs.isM27()) {
            stage.getBatch().draw(texture, 90, Height-2960, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-2910);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-2910);
            font.draw(stage.getBatch(), "x50", Width-480, Height-2910);
            stage.getBatch().draw(t4, Width-400, Height-2960, 80, 80);
        } //* Label 27

        if (challengePrefs.isM28()) {
            stage.getBatch().draw(texture, 90, Height-3070, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-3020);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-3020);
            font.draw(stage.getBatch(), "x10", Width-480, Height-3020);
            stage.getBatch().draw(t4, Width-400, Height-3070, 80, 80);
        } //* Label 28

        if (challengePrefs.isM29()) {
            stage.getBatch().draw(texture, 90, Height-3180, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-3130);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-3130);
            font.draw(stage.getBatch(), "x15", Width-480, Height-3130);
            stage.getBatch().draw(t4, Width-400, Height-3180, 80, 80);
        } //* Label 29

        if (challengePrefs.isM30()) {
            stage.getBatch().draw(texture, 90, Height-3290, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-3240);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-3240);
            font.draw(stage.getBatch(), "x30", Width-480, Height-3240);
            stage.getBatch().draw(t4, Width-400, Height-3290, 80, 80);
        } //* Label 30

        if (challengePrefs.isM31()) {
            stage.getBatch().draw(texture, 90, Height-3400, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-3350);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-3350);
            font.draw(stage.getBatch(), "x60", Width-480, Height-3350);
            stage.getBatch().draw(t4, Width-400, Height-3400, 80, 80);
        } //* Label 31

        if (challengePrefs.isM32()) {
            stage.getBatch().draw(texture, 90, Height-3510, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-3460);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-3460);
            font.draw(stage.getBatch(), "x100", Width-480, Height-3460);
            stage.getBatch().draw(t4, Width-400, Height-3510, 80, 80);
        } //* Label 32

        if (challengePrefs.isM33()) {
            stage.getBatch().draw(texture, 90, Height-3620, 80, 80);
            font.draw(stage.getBatch(), chosenLanguage[19], Width-480, Height-3570);
        }
        else {
            font.draw(stage.getBatch(), chosenLanguage[18], Width-620, Height-3570);
            font.draw(stage.getBatch(), "x150", Width-480, Height-3570);
            stage.getBatch().draw(t4, Width-400, Height-3620, 80, 80);
        } //* Label 33

//        //* Test
//        font.getData().setScale(3f);
//        font.setColor(Color.WHITE);
//        font.draw(stage.getBatch(), Width + " " + Height, Width >> 1, (Height>>1));
//        font.draw(stage.getBatch(), stage.getCamera().viewportWidth + " " + stage.getCamera().viewportHeight, Width >> 1, (Height>>1)-80);
//        font.draw(stage.getBatch(), stage.getWidth() + " " + stage.getHeight(), Width >> 1, (Height>>1)-160);
//        font.getData().setScale(1f);

        stage.getBatch().end();
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
            font.dispose();
            t1.dispose();
            texture.dispose();
            t2.dispose();
            t3.dispose();
            t4.dispose();
            texture1.dispose();
            musicB.dispose();
            music.dispose();
            musicBG.dispose();
            assetManager.dispose();
        }
        catch (Exception ignore) {}
    }
}
