package com.ms.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ScreenManager {
    /**
     * Manager for controlling all screens in game
     *
     * Created by MS
     */

    /**
     * Enum with all screens
     */
    public enum ScreenType {
        MENU, GAME, GAME_OVER, SHOP
    }

    private static ScreenManager ourInstance = new ScreenManager();

    /**
     * Gets accept for all screens
     *
     * @return - returns instance
     */
    public static ScreenManager getInstance() {
        return ourInstance;
    }

    /**
     * Screen manager constructor
     */
    private ScreenManager() {
    }

    public static final int WORLD_WIGHT = Gdx.graphics.getWidth();
    public static final int WORLD_HEIGHT = Gdx.graphics.getHeight();

    private Viewport viewport;
    private Camera camera;
    private Game game;
    private MyGdxGame gameScreen;
    private MenuScreen menuScreen;
    private GameOverScreen gameOverScreen;
    private ShopScreen shopScreen;

    /**
     * Getting viewport
     * @return - return viewport
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     * Getting camera
     * @return - return camera
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Initialization
     * @param game - game class from engine
     * @param batch - batch resource
     */
    public void init(Game game, SpriteBatch batch){
        this.game = game;
        this.camera = new OrthographicCamera(WORLD_WIGHT, WORLD_HEIGHT);
        this.camera.position.set(WORLD_WIGHT >> 1, WORLD_HEIGHT >> 1, 0);
        this.camera.update();
        this.viewport = new FitViewport(WORLD_WIGHT, WORLD_HEIGHT, camera);
        this.gameScreen = new MyGdxGame(batch);
        this.menuScreen = new MenuScreen(batch);
        this.gameOverScreen = new GameOverScreen(batch);
        this.shopScreen = new ShopScreen(batch);
    }

    /**
     * Resize function
     * @param wight - width
     * @param height - height
     */
    public void resize(int wight, int height){
        viewport.update(wight, height);
        viewport.apply();
    }

    /**
     * Function, which change screens
     * @param screenType - enum class
     */
    public void setScreen(ScreenType screenType, Object... args){
        Screen currentScreen = game.getScreen();
        switch (screenType){
            case MENU:
                game.setScreen(menuScreen);
                break;
            case GAME:
                game.setScreen(gameScreen);
                break;
            case SHOP:
                game.setScreen(shopScreen);
                break;
            case GAME_OVER:
                gameOverScreen.setInfo((Player) args[0]);
                game.setScreen(gameOverScreen);
                break;
        } //* Changing screens from types

        if (currentScreen != null){
            currentScreen.dispose();
        } //* Break process if current screen lost data
    }

    /**
     * Simple function for changing screens
     */
    public void setScreen(Screen screen) {
        game.setScreen(screen);
    }
}
