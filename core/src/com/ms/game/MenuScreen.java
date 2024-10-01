package com.ms.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends AbstractScreen {
    /**
     * Menu screen, start screen
     *
     * Created by MS
     */
    private Stage stage;
    private Texture img1, player, top, bottom, texture1;
    private BitmapFont font24;
    private Prefs prefs;
    private boolean haveOnScreenText = true;
    private String[] chosenLanguage;
    private final int Width = Gdx.graphics.getWidth();
    private final int Height = Gdx.graphics.getHeight();
    private int level = 0, n; //* level - for button animation, n - for coin position
    private double progress = 0; //* progress - for button animation

    /**
     * Menu screen constructor
     */
    public MenuScreen() {}

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
        stage.getCamera().viewportWidth = Width;
        stage.getCamera().viewportHeight = Height;

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
        setLang(prefs.getLang());
        chosenLanguage = language[getLang()];

        stage.getRoot().getColor().a = 0;
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));

        Group group = new Group();
        final ImageButton imgBtn = new ImageButton(textButtonStyle);
        imgBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setScreenWithFade(new MyGdxGame());
                //ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.GAME);
            }
        }); //* Event listener
        imgBtn.setPosition(-98, -48);
        group.addActor(imgBtn);
        group.setPosition(Width >> 1, (Height >> 1) - 50);
        stage.addActor(group);

        final Group group1 = new Group();
        Texture texture = new Texture(Gdx.files.internal("ButtonDesign1.png"));
        final Skin skin1 = new Skin();
        skin1.add("simple1Button", texture);
        texture = new Texture(Gdx.files.internal("Shop.png"));
        skin1.add("shopButton", texture);
        ImageButton.ImageButtonStyle textButtonStyle1 = new ImageButton.ImageButtonStyle();
        textButtonStyle1.up = skin1.getDrawable("shopButton");
        final ImageButton txtBtn = new ImageButton(textButtonStyle1);
        txtBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setScreenWithFade(new ShopScreen());
            }
        });
        txtBtn.setPosition(-150, -110);
        group1.addActor(txtBtn);
        group1.setPosition(Width - (Width >> 15), Height - (Height >> 5));

        texture = new Texture(Gdx.files.internal("Computer.png"));
        skin1.add("button", texture);
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.imageUp = skin1.getDrawable("button");
        ImageButton imageButton = new ImageButton(imageButtonStyle);
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setScreenWithFade(new InformationScreen());
            }
        });
        imageButton.setPosition(-300, -110);
        group1.addActor(imageButton);

        texture = new Texture(Gdx.files.internal("Challenge.png"));
        skin1.add("button1", texture);
        ImageButton.ImageButtonStyle imageButtonStyle1 = new ImageButton.ImageButtonStyle();
        imageButtonStyle1.imageUp = skin1.getDrawable("button1");
        ImageButton imageButton1 = new ImageButton(imageButtonStyle1);
        imageButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setScreenWithFade(new ChallengeScreen());
            }
        });
        imageButton1.setPosition(-450, -110);
        group1.addActor(imageButton1);
        stage.addActor(group1);

        texture = new Texture(Gdx.files.internal("Factory.png"));
        skin1.add("button2", texture);
        ImageButton.ImageButtonStyle imageButtonStyle2 = new ImageButton.ImageButtonStyle();
        imageButtonStyle2.imageUp = skin1.getDrawable("button2");
        ImageButton imageButton2 = new ImageButton(imageButtonStyle2);
        imageButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setScreenWithFade(new FactoryScreen());
            }
        });
        imageButton2.setPosition(-600, -110);
        group1.addActor(imageButton2);
        stage.addActor(group1);

        Group group2 = new Group();
        Texture texture2 = new Texture(Gdx.files.internal("Eng.png"));
        skin1.add("eng", texture2);
        texture2 = new Texture(Gdx.files.internal("Rus.png"));
        skin1.add("rus", texture2);
        final ImageButton.ImageButtonStyle textButtonStyle2 = new ImageButton.ImageButtonStyle();
        if (getLang() == 1) textButtonStyle2.up = skin1.getDrawable("rus");
        else textButtonStyle2.up = skin1.getDrawable("eng");
        final ImageButton txtBtn2 = new ImageButton(textButtonStyle2);
        txtBtn2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (getLang() == 1) {
                    setLang(0);
                    prefs.setLang(0);
                    textButtonStyle2.up = skin1.getDrawable("eng");
                }
                else {
                    setLang(1);
                    prefs.setLang(1);
                    textButtonStyle2.up = skin1.getDrawable("rus");
                }
                txtBtn2.setStyle(textButtonStyle2);
                chosenLanguage = language[getLang()];
            }
        });
        txtBtn2.setPosition(-140, -25);
        group2.addActor(txtBtn2);
        group2.setPosition(Width - (Width >> 15) - 50, (Height >> 5));
        stage.addActor(group2);
        Gdx.input.setInputProcessor(stage);

        n = 0;
        for (int i = 0; i < String.valueOf(prefs.getScore()).length(); i++) {
            n++;
        }
    }

    /**
     * Rendering all objects
     * @param delta - delta time
     */
    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(img1, 0, -300);
        if (haveOnScreenText) {
            font24.setColor(Color.GOLDENROD);
            font24.getData().setScale(2f);
            stage.getBatch().draw(texture1, 5 + 35 * n, Height - 85, 80, 80);
            font24.draw(stage.getBatch(), String.valueOf(prefs.getScore()), 20, Height - 20);
            font24.setColor(Color.CYAN);
            font24.getData().setScale(3f);
            font24.draw(stage.getBatch(), chosenLanguage[2] + prefs.getRecord(), (Width >> 1) - (Width >> 3) + (Width >> 7), (Height >> 1) + 1.25f * (Height >> 2));
        }
        font24.getData().setScale(1f);
        stage.getBatch().draw(player, 170, -295);
        if (prefs.getDown() != 0) stage.getBatch().draw(bottom, 450, 180);
        if (prefs.getTop() != 0 && prefs.getTop() != 8) stage.getBatch().draw(top, 445, 365);
        else if (prefs.getTop() == 8) stage.getBatch().draw(top, 87, 105);
        stage.getBatch().end();
        stage.draw();
    }

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
        }
        catch (Exception ignore) {}
    }
}
