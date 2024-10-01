package com.ms.game.screens;

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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ms.game.AbstractScreen;
import com.ms.game.objects.Player;
import com.ms.game.save.Prefs;
import com.ms.game.ScreenManager;

import java.util.Random;

public class GameOverScreen extends AbstractScreen {
    /**
     * Game over screen, restart screen
     *
     * Created by MS
     */
    private Stage stage;
    private Texture img, img1, img2, texture1;
    private String info;
    private BitmapFont font24;
    private TextureRegion region;
    private final int Width = (int) ScreenManager.getInstance().getViewport().getWorldWidth();
    private final int Height = (int) ScreenManager.getInstance().getViewport().getWorldHeight();
    private int level = 0, n, score, lang; //* level - for button animation, n - for coin position, score - new score, lang  - language
    private double progress = 0; //* progress - for button animation
    private AssetManager assetManager;
    private Music musicB, musicBG;
    private final String pathBtn = String.valueOf(Gdx.files.internal("btn.mp3")), pathBG = String.valueOf(Gdx.files.internal("wait.mp3")),
            pathCoin = String.valueOf(Gdx.files.internal("getCoin.mp3"));

    public void setInfo(Player in) {
        info = "" + in.getWeight()/100;
    }

    /**
     * GameOverScreen constructor
     */
    public GameOverScreen() {}

    /**
     * Initialization and creating buttons
     */
    @Override
    public void show() {
        stage = new Stage();
        Prefs prefs = new Prefs();
        img = new Texture(Gdx.files.internal("reset.png"));
        texture1 = new Texture(Gdx.files.internal("Coin.png"));
        if (prefs.getBg()==0) img1 = new Texture(Gdx.files.internal("Landscape.png"));
        else img1 = new Texture(Gdx.files.internal("City.png"));
        img2 = new Texture(Gdx.files.internal("TreeM.png"));
        Skin skin = new Skin();
        skin.add("simpleButton", img);
        ImageButton.ImageButtonStyle textButtonStyle = new ImageButton.ImageButtonStyle();
        textButtonStyle.up = skin.getDrawable("simpleButton");
        font24 = new BitmapFont(Gdx.files.internal("font32S.fnt"));
        stage.setViewport(ScreenManager.getInstance().getViewport());
        Random random = new Random();
        int a = 1 + random.nextInt(13);
        TextureAtlas atlas;
        if (prefs.getLang() == 1) atlas = new TextureAtlas("TablesE.pack");
        else atlas = new TextureAtlas("Tables.pack");
        region = atlas.findRegion("Tb" + a);
        lang = prefs.getLang();

        assetManager = new AssetManager();
        if (!prefs.isVolumeOff()) {
            assetManager.load(pathBtn, Music.class);
            assetManager.load(pathCoin, Music.class);
        }
        if (!prefs.isMelOff()) {
            assetManager.load(pathBG, Music.class);
        }
        assetManager.finishLoading();

        Group group = new Group();
        final ImageButton imgBtn = new ImageButton(textButtonStyle);
        imgBtn.addListener(new ClickListener(){
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
                stage.getRoot().getColor().a = 0;
                stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeOut(1f)));
                try {
                    ScreenManager.getInstance().setScreen(new MenuScreen(musicBG.getPosition()));
                }
                catch (Exception e) {
                    ScreenManager.getInstance().setScreen(new MenuScreen(1));
                }
            }
        }); //* Event listener
        imgBtn.setPosition(-64, -64);
        group.addActor(imgBtn);
        group.setPosition((Width >> 1) - 376, Height - (Height >> 2) - 460);
        stage.addActor(group);
        Gdx.input.setInputProcessor(stage);

        stage.getRoot().getColor().a = 0;
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));

        prefs.setRecord(Float.parseFloat(info));
        prefs.setScore((int) Math.floor(Float.parseFloat(info) * 12));
        prefs.setCollectedPaper(Float.parseFloat(info)*100);
        score = (int) Math.floor(Float.parseFloat(info) * 12);

        n = 0;
        for (int i = 0; i < String.valueOf(score).length(); i++) {
            n++;
        }

        if (assetManager.isLoaded(pathBG)) {
            musicBG = assetManager.get(pathBG, Music.class);
            musicBG.setLooping(true);
            musicBG.setVolume(0.5f);
            musicBG.play();
        }
        if (assetManager.isLoaded(pathCoin)) {
            musicB = assetManager.get(pathCoin, Music.class);
            musicB.setVolume(1.0f);
            musicB.play();
        }
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

        stage.getBatch().begin();
        stage.getBatch().draw(img2, (Width >> 1) - 650, (Height >> 2) + (Height >> 2) * 1.2f);
        font24.getData().setScale(5f, 3f);
        font24.setColor(Color.GREEN);
        font24.draw(stage.getBatch(), " X " + info, (Width >> 1) - 500, (Height >> 2) + (Height >> 2) * 1.75f);
        font24.setColor(Color.GOLDENROD);
        stage.getBatch().draw(texture1, (Width >> 1) - 370 + 110 * n, (Height >> 1) + 5, 90, 90);
        font24.draw(stage.getBatch(), " + " + score, (Width >> 1) - 500, (Height >> 2) + (Height >> 2) * 1.45f - 30);
        try {
            stage.getBatch().draw(region, (Width >> 1) + 100, (Height >> 1)-180, 762, 362);
        }
        catch (Exception ignore) {}
        font24.setColor(Color.YELLOW);
        font24.getData().setScale(3f);
        font24.draw(stage.getBatch(), language[lang][24], (Width >> 1) + 120, (Height >> 1) + 270);

//        //*Test
//        font24.getData().setScale(1f);
//        font24.setColor(Color.WHITE);
//        font24.draw(stage.getBatch(), Width + " " + Height, Width >> 1, Height>>1);
//        font24.draw(stage.getBatch(), stage.getCamera().viewportWidth + " " + stage.getCamera().viewportHeight, Width >> 1, (Height>>1)-40);
//        font24.draw(stage.getBatch(), stage.getWidth() + " " + stage.getHeight(), Width >> 1, (Height>>1)-80);
//        font24.draw(stage.getBatch(), ((Width >> 1) + 120) + " " + ((Height >> 1) + 370), Width >> 1, (Height>>1)-120);
//        font24.draw(stage.getBatch(), ((Width >> 1) + 100) + " " + ((Height >> 1) - 350), Width >> 1, (Height>>1)-160);
        stage.getBatch().end(); //* Text texture batching
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
            }
            else {
                level = 1;
            }

            if (progress > 0 && level == 1) {
                stage.getActors().get(0).setScale((float) (1f + 0.1f * progress));
                progress -= 0.05f;
            }
            else {
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
            stage.dispose();
            img.dispose();
            img1.dispose();
            img2.dispose();
            font24.dispose();
            musicB.dispose();
            musicBG.dispose();
            assetManager.dispose();
        }
        catch (Exception ignore) {}
    }
}
