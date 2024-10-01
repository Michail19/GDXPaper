package com.ms.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;

public class ShopScreen extends AbstractScreen{
    /**
     * Shop
     *
     * Created by MS
     */
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font24;
    private Prefs prefs;
    private OrthographicCamera camera;
    private ExtendViewport viewport;
    private String[] chosenLanguage;
    private final float MAX_SCROLL = 3760.0f;

    /**
     * Menu screen constructor
     * @param batch - batch
     */
    public ShopScreen(SpriteBatch batch) {
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
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        chosenLanguage = language[prefs.getLang()];

        Group group = new Group();
        Texture texture = new Texture(Gdx.files.internal("ButtonDesign1.png"));
        Skin skin = new Skin();
        skin.add("simple1Button", texture);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
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
        imgBtn.setPosition(-165, -75);
        group.addActor(imgBtn);
        group.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() >> 15), Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() >> 5));
        stage.addActor(group);

        Group group1 = new Group();
        Texture texture1 = new Texture(Gdx.files.internal("Slot.png"));
        skin.add("slot", texture1);
        texture1 = new Texture(Gdx.files.internal("badlogic.jpg"));
        skin.add("A1", texture1);
        texture1 = new Texture(Gdx.files.internal("TreeM.png"));
        skin.add("A2", texture1);
        texture1 = new Texture(Gdx.files.internal("badlogic.jpg"));
        skin.add("A3", texture1);
        texture1 = new Texture(Gdx.files.internal("badlogic.jpg"));
        skin.add("A4", texture1);
        texture1 = new Texture(Gdx.files.internal("badlogic.jpg"));
        skin.add("A5", texture1);
        texture1 = new Texture(Gdx.files.internal("badlogic.jpg"));
        skin.add("A6", texture1);
        texture1 = new Texture(Gdx.files.internal("badlogic.jpg"));
        skin.add("A7", texture1);

        ImageTextButton.ImageTextButtonStyle textButtonStyle1 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle1.up = skin.getDrawable("slot");
        textButtonStyle1.down = skin.getDrawable("slot");
        textButtonStyle1.checked = skin.getDrawable("slot");
        textButtonStyle1.imageUp = skin.getDrawable("A1");
        textButtonStyle1.imageDown = skin.getDrawable("A1");
        textButtonStyle1.imageChecked = skin.getDrawable("A1");
        textButtonStyle1.font = font24;
        textButtonStyle1.fontColor = Color.CYAN;
        ImageTextButton a1 = new ImageTextButton("1", textButtonStyle1);
        a1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        a1.setPosition(20, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle2 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle2.up = skin.getDrawable("slot");
        textButtonStyle2.down = skin.getDrawable("slot");
        textButtonStyle2.checked = skin.getDrawable("slot");
        textButtonStyle2.imageUp = skin.getDrawable("A2");
        textButtonStyle2.imageDown = skin.getDrawable("A2");
        textButtonStyle2.imageChecked = skin.getDrawable("A2");
        textButtonStyle2.font = font24;
        textButtonStyle2.fontColor = Color.CYAN;
        ImageTextButton a2 = new ImageTextButton("1", textButtonStyle2);
        a2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        a2.setPosition(640, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle3 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle3.up = skin.getDrawable("slot");
        textButtonStyle3.down = skin.getDrawable("slot");
        textButtonStyle3.checked = skin.getDrawable("slot");
        textButtonStyle3.imageUp = skin.getDrawable("A3");
        textButtonStyle3.imageDown = skin.getDrawable("A3");
        textButtonStyle3.imageChecked = skin.getDrawable("A3");
        textButtonStyle3.font = font24;
        textButtonStyle3.fontColor = Color.CYAN;
        ImageTextButton a3 = new ImageTextButton("1", textButtonStyle3);
        a3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        a3.setPosition(1260, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle4 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle4.up = skin.getDrawable("slot");
        textButtonStyle4.down = skin.getDrawable("slot");
        textButtonStyle4.checked = skin.getDrawable("slot");
        textButtonStyle4.imageUp = skin.getDrawable("A4");
        textButtonStyle4.imageDown = skin.getDrawable("A4");
        textButtonStyle4.imageChecked = skin.getDrawable("A4");
        textButtonStyle4.font = font24;
        textButtonStyle4.fontColor = Color.CYAN;
        ImageTextButton a4 = new ImageTextButton("1", textButtonStyle4);
        a4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        a4.setPosition(1880, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle5 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle5.up = skin.getDrawable("slot");
        textButtonStyle5.down = skin.getDrawable("slot");
        textButtonStyle5.checked = skin.getDrawable("slot");
        textButtonStyle5.imageUp = skin.getDrawable("A5");
        textButtonStyle5.imageDown = skin.getDrawable("A5");
        textButtonStyle5.imageChecked = skin.getDrawable("A5");
        textButtonStyle5.font = font24;
        textButtonStyle5.fontColor = Color.CYAN;
        ImageTextButton a5 = new ImageTextButton("1", textButtonStyle5);
        a5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        a5.setPosition(2500, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle6 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle6.up = skin.getDrawable("slot");
        textButtonStyle6.down = skin.getDrawable("slot");
        textButtonStyle6.checked = skin.getDrawable("slot");
        textButtonStyle6.imageUp = skin.getDrawable("A6");
        textButtonStyle6.imageDown = skin.getDrawable("A6");
        textButtonStyle6.imageChecked = skin.getDrawable("A6");
        textButtonStyle6.font = font24;
        textButtonStyle6.fontColor = Color.CYAN;
        ImageTextButton a6 = new ImageTextButton("1", textButtonStyle6);
        a6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        a6.setPosition(3120, -450);

        ImageTextButton.ImageTextButtonStyle textButtonStyle7 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle7.up = skin.getDrawable("slot");
        textButtonStyle7.down = skin.getDrawable("slot");
        textButtonStyle7.checked = skin.getDrawable("slot");
        textButtonStyle7.imageUp = skin.getDrawable("A7");
        textButtonStyle7.imageDown = skin.getDrawable("A7");
        textButtonStyle7.imageChecked = skin.getDrawable("A7");
        textButtonStyle7.font = font24;
        textButtonStyle7.fontColor = Color.CYAN;
        ImageTextButton a7 = new ImageTextButton("1", textButtonStyle7);
        a7.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        a7.setPosition(3740, -450);

        group1.addActor(a1);
        group1.addActor(a2);
        group1.addActor(a3);
        group1.addActor(a4);
        group1.addActor(a5);
        group1.addActor(a6);
        group1.addActor(a7);
        group1.setPosition(0, Gdx.graphics.getHeight() >> 1);
        stage.addActor(group1);
        Gdx.input.setInputProcessor(stage);
    }

    //@Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.position.x += deltaX;
        camera.update();
        return false;
    }

    /**
     * Rendering all objects
     * @param delta - delta time
     */
    @Override
    public void render(float delta) {
        update(delta);

        stage.getBatch().setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
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
        stage.dispose();
    }
}
