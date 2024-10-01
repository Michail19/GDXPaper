package com.ms.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameOverScreen extends AbstractScreen {
    /**
     * Game over screen, restart screen
     *
     * Created by MS
     */
    private Stage stage;
    private SpriteBatch batch;
    private Texture img, img1, img2;
    private String info;
    private BitmapFont font24;
    private Prefs prefs;
    private String[] chosenLanguage;

    public void setInfo(Player in) {
        info = "" + in.getWeight()/100;
    }

    /**
     * GameOverScreen constructor
     * @param batch - batch resource
     */
    public GameOverScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    /**
     * Initialization and creating buttons
     */
    @Override
    public void show() {
        stage = new Stage();
        prefs = new Prefs();
        img = new Texture(Gdx.files.internal("reset.png"));
        img1 = new Texture(Gdx.files.internal("Landscape.png"));
        img2 = new Texture(Gdx.files.internal("TreeM.png"));
        //img1 = new Texture(Gdx.files.internal("screenshot.png"));
        Skin skin = new Skin();
        skin.add("simpleButton", img);
        ImageButton.ImageButtonStyle textButtonStyle = new ImageButton.ImageButtonStyle();
        textButtonStyle.up = skin.getDrawable("simpleButton");
        font24 = new BitmapFont(Gdx.files.internal("font32S.fnt"));
        chosenLanguage = language[prefs.getLang()];

        Group group = new Group();
        final ImageButton imgBtn = new ImageButton(textButtonStyle);
        imgBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.MENU);
            }
        }); //* Event listener
        imgBtn.setPosition(-(Gdx.graphics.getWidth() >> 1) + (Gdx.graphics.getHeight() >> 2) * 3f, -(Gdx.graphics.getHeight() >> 2) + (Gdx.graphics.getHeight() >> 2) * 0.45f);
        group.addActor(imgBtn);
        group.setPosition(Gdx.graphics.getWidth() >> 1, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() >> 2) * 2.1f);
        stage.addActor(group);
        Gdx.input.setInputProcessor(stage);

        stage.getRoot().getColor().a = 0;
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));

        prefs.setRecord(Float.parseFloat(info));
        prefs.setScore((float) Math.floor(Float.parseFloat(info) * 12));
    }

    /**
     * Rendering all objects
     * @param delta - delta time
     */
    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(img1, 0, 0);
        stage.getBatch().end();

        stage.draw();

        batch.begin();
        batch.draw(img2, (Gdx.graphics.getWidth() >> 2) * 1.15f, (Gdx.graphics.getHeight() >> 2) + (Gdx.graphics.getHeight() >> 2) * 1.2f);
        font24.getData().setScale(5f, 3f);
        font24.setColor(Color.GREEN);
        font24.draw(batch, " X " + info, (Gdx.graphics.getWidth() >> 5) * 12, (Gdx.graphics.getHeight() >> 2) + (Gdx.graphics.getHeight() >> 2) * 1.75f);
        font24.setColor(Color.GOLDENROD);
        font24.draw(batch, " + " + Math.floor(Float.parseFloat(info) * 12), (Gdx.graphics.getWidth() >> 5) * 12, (Gdx.graphics.getHeight() >> 2) + (Gdx.graphics.getHeight() >> 2) * 1.45f); //* Text texture batching
        batch.end();
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
        try {
            stage.dispose();
            img.dispose();
        }
        catch (IllegalArgumentException ignore) {}
    }
}
