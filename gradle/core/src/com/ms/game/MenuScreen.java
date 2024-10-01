package com.ms.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends AbstractScreen {
    /**
     * Menu screen, start screen
     *
     * Created by MS
     */
    private Stage stage;
    private SpriteBatch batch;
    private Texture img1;
    private BitmapFont font24;
    private Prefs prefs;
    private boolean haveOnScreenText = true;
    private String[] chosenLanguage;

    /**
     * Menu screen constructor
     * @param batch - batch
     */
    public MenuScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    /**
     * Initialization and creating button
     */
    @Override
    public void show() {
        stage = new Stage();
        font24 = new BitmapFont(Gdx.files.internal("font32S.fnt"));
        prefs = new Prefs();
        Texture img = new Texture(Gdx.files.internal("play.png"));
        img1 = new Texture(Gdx.files.internal("StartScreen.png"));
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
                setScreenWithFade(new MyGdxGame(batch));
                //ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.GAME);
            }
        }); //* Event listener
        imgBtn.setPosition(-100, -50);
        group.addActor(imgBtn);
        group.setPosition(Gdx.graphics.getWidth() >> 1, Gdx.graphics.getHeight() >> 1);
        stage.addActor(group);

        final Group group1 = new Group();
        Texture texture = new Texture(Gdx.files.internal("ButtonDesign1.png"));
        final Skin skin1 = new Skin();
        skin1.add("simple1Button", texture);
        TextButton.TextButtonStyle textButtonStyle1 = new TextButton.TextButtonStyle();
        textButtonStyle1.up = skin1.getDrawable("simple1Button");
        textButtonStyle1.font = font24;
        textButtonStyle1.fontColor = Color.FOREST;
        final TextButton txtBtn = new TextButton(chosenLanguage[6], textButtonStyle1);
        txtBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setScreenWithFade(new ShopScreen(batch));
                //ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.SHOP);
            }
        });
        txtBtn.setPosition(-150, -75);
        group1.addActor(txtBtn);
        group1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() >> 15), Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() >> 5));
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
                txtBtn.setText(chosenLanguage[6]);
            }
        });
        txtBtn2.setPosition(-140, -25);
        group2.addActor(txtBtn2);
        group2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() >> 15), (Gdx.graphics.getHeight() >> 5));
        stage.addActor(group2);
        Gdx.input.setInputProcessor(stage);
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
            font24.draw(stage.getBatch(), chosenLanguage[1] + prefs.getScore(), (Gdx.graphics.getWidth() >> 1) - (Gdx.graphics.getWidth() >> 1) + 20, Gdx.graphics.getHeight() - 25);
            font24.setColor(Color.CYAN);
            font24.getData().setScale(3f);
            font24.draw(stage.getBatch(), chosenLanguage[2] + prefs.getRecord(), (Gdx.graphics.getWidth() >> 1) - (Gdx.graphics.getWidth() >> 3), (Gdx.graphics.getHeight() >> 1) + 1.25f * (Gdx.graphics.getHeight() >> 2));
        }
        font24.getData().setScale(1f);
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
    }

    /**
     * Destructor
     */
    @Override
    public void dispose() {
        font24.dispose();
        img1.dispose();
        stage.dispose();
        haveOnScreenText = true;
    }
}
