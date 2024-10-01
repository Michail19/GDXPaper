package com.ms.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MyGdxGame extends AbstractScreen {
    /**
     * This is a main class of game
     * Game screen
     * <p>
     * Created by MS
     */

    public SpriteBatch batch;
    public Texture img, img1, paperTexture, paperTexture1, gr;
    public TextureRegion playerImg;
    private TextureAtlas textureAtlas;
    public boolean isGameOver = false, isTouched = false, isPaused = false, n = false;
    private Player player;
    private Ground ground, ground1, ground2, ground3;
    private Paper paper;
    private Prefs prefs;
    private Camera camera;
    private Background background;
    private ScrollHandler scroller;
    private BitmapFont font24;
    private Stage stage, pause;
    private int c = 0, end = 0;
    private float lastCamX, lastCamY;
    private String[] chosenLanguage = language[getLang()];

    /*
    Plan:
    *    1) Multi-level platforms +-
    *    2) Donates -
    3) Platforms +
    4) Screens +
    *    5) Shop -
    *    6) Screenshot on the end screen -
    7) Player animation +
    8) Counting trees +
    9) Paper collecting +
    10) Paper on multi-level platforms +
    11) Icon and name +
    12) Moving camera +
    13) Jumping +
    14) Detailed player +
    *    15) Description of necessity paper recycling -
    *    16) Challenges -
    17) Global variables +
    18) Record +
    19) Coins +
    *    20) Busters or different cloth -
    21) Animations +
     */

    //private ShapeRenderer shapeRenderer;

    /**
     * Main class constructor, initialization the batch resource
     *
     * @param batch - Game batch
     */
    public MyGdxGame(SpriteBatch batch) {
        this.batch = batch;
    }

    /**
     * Initialization method
     */
    @Override
    public void show() {
        player = new Player(200, 320, 1024, 1024);
        img = new Texture(Gdx.files.internal("Landscape.png"));
        img1 = new Texture(Gdx.files.internal("Landscape1.png"));
        gr = new Texture(Gdx.files.internal("Grass.png"));
        paperTexture = new Texture(Gdx.files.internal("CrumplesPaperMin.png"));
        paperTexture1 = new Texture(Gdx.files.internal("CrumplesPaperMin1.png"));
        textureAtlas = new TextureAtlas("AnimationPlayer.pack");
        playerImg = textureAtlas.findRegion("Player");
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        scroller = new ScrollHandler();
        background = scroller.getBackground();
        paper = scroller.getPaper();
        ground = scroller.getGround();
        ground1 = scroller.getGround();
        ground2 = scroller.getGround();
        ground3 = scroller.getGround();
        ground.setLength(5); //* Make size of 5 blocks
        font24 = new BitmapFont(Gdx.files.internal("font32S.fnt"));
        font24.getData().setScale(2f);
        lastCamX = camera.position.x;
        lastCamY = camera.position.y;
        stage = new Stage();
        prefs = new Prefs();
        chosenLanguage = language[prefs.getLang()];

        initButtons();
        Gdx.input.setInputProcessor(stage);
        stage.getRoot().getColor().a = 0;
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1.5f)));

        // in create method:
        //shapeRenderer = new ShapeRenderer();
        //shapeRenderer.setAutoShapeType(true);
    }

    private void initButtons() {
        Texture img = new Texture(Gdx.files.internal("pause.png"));
        final Skin skin = new Skin();
        skin.add("simpleButton", img);
        ImageButton.ImageButtonStyle textButtonStyle = new ImageButton.ImageButtonStyle();
        textButtonStyle.up = skin.getDrawable("simpleButton");
        Group group = new Group();
        final ImageButton imgBtn = new ImageButton(textButtonStyle);
        imgBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPaused = true;
                n = true;

                pause = new Stage();
                Group group1 = new Group();
                Texture texture = new Texture(Gdx.files.internal("ButtonDesign1.png"));
                Skin skin1 = new Skin();
                skin1.add("simple1Button", texture);
                TextButton.TextButtonStyle textButtonStyle1 = new TextButton.TextButtonStyle();
                textButtonStyle1.up = skin1.getDrawable("simple1Button");
                textButtonStyle1.font = font24;
                textButtonStyle1.fontColor = Color.FOREST;
                TextButton txtBtn1 = new TextButton(chosenLanguage[4], textButtonStyle1);
                TextButton txtBtn11 = new TextButton(chosenLanguage[5], textButtonStyle1);
                txtBtn1.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        stage.getRoot().clearActions();
                        isPaused = false;
                        stage.getRoot().getColor().a = 0;
                        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
                        Gdx.input.setInputProcessor(stage);
                        stage.getActors().removeIndex(1);
                    }
                });
                txtBtn11.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        stage.getRoot().clearActions();
                        player.jumpForce = 0;
                        camera.position.set(Gdx.graphics.getWidth() >> 1, Gdx.graphics.getHeight() >> 1, 0);
                        pause.getActors().clear();
                        ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.GAME_OVER, player);
                    }
                });
                txtBtn1.setPosition(-(Gdx.graphics.getWidth() >> 1)  + (Gdx.graphics.getHeight() >> 2) * 0.85f, -(Gdx.graphics.getHeight() >> 2) - (Gdx.graphics.getHeight() >> 2) * 0.4f);
                txtBtn11.setPosition(-(Gdx.graphics.getWidth() >> 1) + (Gdx.graphics.getHeight() >> 2) * 1.15f, -(Gdx.graphics.getHeight() >> 2) - (Gdx.graphics.getHeight() >> 2) * 0.85f);
                group1.addActor(txtBtn1);
                group1.addActor(txtBtn11);
                group1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() >> 2), Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() >> 3));
                pause.addActor(group1);
                Gdx.input.setInputProcessor(pause);
                stage.getRoot().getColor().a = 1f;
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f)));

                stage.addActor(pause.getRoot());
                //ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.MENU);
            }
        }); //* Event listener
        imgBtn.setPosition(-100, -50);
        group.addActor(imgBtn);
        group.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() >> 25), Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() >> 5));
        stage.addActor(group);
        stage.getRoot().getColor().a = 0;
    }

    /**
     * Render method, works with update function
     *
     * @param dt - delta time
     */
    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //* Other part of screen painted in green colour
        if (!isPaused) {
            update(dt);

            stage.getBatch().begin(); //* Function for starting batch painting

            if ((player.getRectangle().overlaps(ground.getRectangle()) || player.getRectangle().overlaps(ground1.getRectangle()) ||
                    player.getRectangle().overlaps(ground2.getRectangle()) || player.getRectangle().overlaps(ground3.getRectangle()))
                    && !player.isGrounded) {
                scroller.stop();
                if (end != -50) {
                    end -= 1;
                }
                c = 0;
                player.jumpForce -= 10;
            } //* Moratorium for intersection ground by body

            if (player.isGrounded() && Gdx.input.isTouched()) {
                isTouched = true;
                player.isGrounded = false;
                player.startM = player.getJumpForce();
            } //* Checking the separation from the ground
            else {
                if (!n) {
                    if (player.getJumpForce() >= player.startM + 700)
                        isTouched = false; //* Checking the separation from the ground

                    if (player.getJumpForce() < player.startM + 700 && isTouched) {
                        player.jumpForce += 10;
                    }  //* Up
                    else {
                        if (!player.isGrounded && player.getPosition().y + player.getJumpForce() > -1200) {
                            player.jumpForce -= 10;
                        }
                    } //* Down
                }
                else {
                    n = false;
                    isTouched = false;
                    player.isGrounded = true;
                }
            } //* Function of jumping, jump force = 700

            ground.getRectangle().setPosition(ground.getX(), ground.getY());
            ground1.getRectangle().setPosition(ground1.getX(), ground1.getY());
            ground2.getRectangle().setPosition(ground2.getX(), ground2.getY());
            ground3.getRectangle().setPosition(ground3.getX(), ground3.getY()); //* Updating rectangle position of grounds

            player.getRectangle().setPosition(player.getRectangle().getX(), player.getY() + player.getJumpForce());
            player.getRectangleL().setPosition(player.getRectangleL().getX(), player.getY() + player.getJumpForce());//* Updating rectangle position of player

            if (camera.position.y - (Gdx.graphics.getHeight() >> 1) <= 0 || camera.position.y + (Gdx.graphics.getHeight() >> 1) >= 4167) {
                camera.position.set(player.getPosition().x + 600, player.getPosition().y + player.getJumpForce() + 450, 0);
                if (player.getPosition().y + player.getJumpForce() <= -1200) {
                    isGameOver = true;
                }
            } else {
                if (player.getPosition().x > 0)
                    camera.position.set(player.getPosition().x + 600, player.getPosition().y + player.getJumpForce() + 450, 0);
                camera.update();
            }
            stage.getBatch().setProjectionMatrix(camera.combined); //* Updating camera position

            if (isGameOver) {
                player.jumpForce = 0;
                isGameOver = false;
                //ScreenshotFactory.saveScreenshot();
                camera.position.set(Gdx.graphics.getWidth() >> 1, Gdx.graphics.getHeight() >> 1, 0);
                ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.GAME_OVER, player);
            } //* Game over function, which is destroying process and change screen

            drawBackground(); //* Drawing BG
            stage.getBatch().draw(playerImg, player.getPosition().x - 200 + end, player.getPosition().y + player.getJumpForce() - 20); //* Player texture batching

            if (paper.getWeight() < 6)
                stage.getBatch().draw(paperTexture, paper.getX(), paper.getY(), paper.getWidth(), paper.getHeight());
            else
                stage.getBatch().draw(paperTexture1, paper.getX(), paper.getY(), paper.getWidth(), paper.getHeight()); //* Paper texture batching

            font24.getData().setScale(1.5f);
            if (camera.position.y - (Gdx.graphics.getHeight() >> 1) > 0 && camera.position.y + (Gdx.graphics.getHeight() >> 1) < 4167) {
                font24.draw(stage.getBatch(), chosenLanguage[0] + player.getWeight(), camera.position.x - (Gdx.graphics.getWidth() >> 1) + 20, camera.position.y + (Gdx.graphics.getHeight() >> 1) - 25);
                lastCamX = camera.position.x;
                lastCamY = camera.position.y;
            } else {
                font24.draw(stage.getBatch(), chosenLanguage[0] + player.getWeight(), lastCamX - (Gdx.graphics.getWidth() >> 1) + 20, lastCamY + (Gdx.graphics.getHeight() >> 1) - 25); //* Text texture batching
            }

            for (int i = 0; i < ground.getLength(); i++)
                stage.getBatch().draw(gr, ground.getX() + ground.getWidth() * i, ground.getY());
            for (int i = 0; i < ground1.getLength(); i++)
                stage.getBatch().draw(gr, ground1.getX() + ground1.getWidth() * i, ground1.getY());
            for (int i = 0; i < ground2.getLength(); i++)
                stage.getBatch().draw(gr, ground2.getX() + ground2.getWidth() * i, ground2.getY());
            for (int i = 0; i < ground3.getLength(); i++)
                stage.getBatch().draw(gr, ground3.getX() + ground3.getWidth() * i, ground3.getY()); //* Ground texture batching

            // in render method:
            //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            //shapeRenderer.setColor(Color.BLACK);
            //shapeRenderer.rect(player.getRectangle().x, player.getRectangleL().y, player.getRectangle().width, player.getRectangle().height);
            //shapeRenderer.end();

            //stage.getBatch().begin();
            //stage.getBatch().end();
        }
        else {
            stage.act(dt);
            stage.getBatch().begin();

            stage.getBatch().setProjectionMatrix(camera.combined); //* Updating camera position
            drawBackground(); //* Drawing BG
            stage.getBatch().draw(playerImg, player.getPosition().x - 200 + end, player.getPosition().y + player.getJumpForce() - 20); //* Player texture batching

            ground.getRectangle().setPosition(ground.getX(), ground.getY());
            ground1.getRectangle().setPosition(ground1.getX(), ground1.getY());
            ground2.getRectangle().setPosition(ground2.getX(), ground2.getY());
            ground3.getRectangle().setPosition(ground3.getX(), ground3.getY()); //* Updating rectangle position of grounds

            for (int i = 0; i < ground.getLength(); i++)
                stage.getBatch().draw(gr, ground.getX() + ground.getWidth() * i, ground.getY());
            for (int i = 0; i < ground1.getLength(); i++)
                stage.getBatch().draw(gr, ground1.getX() + ground1.getWidth() * i, ground1.getY());
            for (int i = 0; i < ground2.getLength(); i++)
                stage.getBatch().draw(gr, ground2.getX() + ground2.getWidth() * i, ground2.getY());
            for (int i = 0; i < ground3.getLength(); i++)
                stage.getBatch().draw(gr, ground3.getX() + ground3.getWidth() * i, ground3.getY()); //* Ground texture batching

            pause.draw();

            font24.setColor(Color.CYAN);
            font24.getData().setScale(5f);
            font24.draw(stage.getBatch(), chosenLanguage[3], (Gdx.graphics.getWidth() >> 1) - (Gdx.graphics.getWidth() >> 3), (Gdx.graphics.getHeight() >> 1) + 1.25f * (Gdx.graphics.getHeight() >> 2));
        }
        stage.getBatch().end(); //* Function for ending batch painting
        stage.draw();
    }

    /**
     * Background batching
     */
    private void drawBackground() {
        if (!scroller.getBackground().isRight) {
            stage.getBatch().draw(img, background.getX(), background.getY(), background.getWidth(), background.getHeight());
            stage.getBatch().draw(img1, background.getX() - 4167, background.getY(), background.getWidth(), background.getHeight());
        } else {
            stage.getBatch().draw(img1, background.getX(), background.getY(), background.getWidth(), background.getHeight());
            stage.getBatch().draw(img, background.getX() - 4167, background.getY(), background.getWidth(), background.getHeight());
        } //* Checking right-left side of texture and batching
    }

    /**
     * Updating method
     *
     * @param dt - delta time
     */
    public void update(float dt) {
        scroller.update(dt); //* Scroller updating, sending dt to ScrollHandler class
        stage.act(dt);

        paper.getRectangle().setPosition(paper.getX(), paper.getY()); //* Updating rectangle position of paper

        player.isGrounded = player.getRectangleL().overlaps(ground.getRectangle()) ||
                player.getRectangleL().overlaps(ground1.getRectangle()) ||
                player.getRectangleL().overlaps(ground2.getRectangle()) ||
                player.getRectangleL().overlaps(ground3.getRectangle()); //* Checking the landing

        c++; //* Variable of player animation
        if (c == 2) playerImg = textureAtlas.findRegion("Player");
        else if (c == 4) playerImg = textureAtlas.findRegion("Player1");
        else if (c == 6) playerImg = textureAtlas.findRegion("Player2");
        else if (c == 8) playerImg = textureAtlas.findRegion("Player3");
        else if (c == 10) playerImg = textureAtlas.findRegion("Player2");
        else if (c == 12) playerImg = textureAtlas.findRegion("Player1");
        else if (c == 14) {
            playerImg = textureAtlas.findRegion("Player");
            c = 0;
        } //* Player animation

        if (player.getRectangle().overlaps(paper.getRectangle()) || player.getRectangleL().overlaps(paper.getRectangle())) {
            scroller.resetPaper();
            player.weight += paper.getWeight();
        } //* Collecting paper
    }

    /**
     * Dispose method
     */
    @Override
    public void dispose() {
        try {
            textureAtlas.dispose();
            img.dispose();
            img1.dispose();
            font24.dispose();
            paperTexture.dispose();
            paperTexture1.dispose();
            gr.dispose();
            stage.dispose();
            playerImg.getTexture().dispose();
        } catch (IllegalArgumentException ignore) {}
    }
}
