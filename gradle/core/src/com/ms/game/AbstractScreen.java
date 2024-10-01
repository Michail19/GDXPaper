package com.ms.game;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {
    /**
     * Main class for screens
     *
     * Created by MS
     */

    /////////////////////////////////////0///////////1//////////2///////////3///////////////4///////////////////////5////////////////6/////
    public String[][] language = {{"Collected: ", "Coins: ", "Record: ", "Pause", "  Continue game  ", "  Return \n to menu  ", "     Shop    "},
            /////0///////////1///////////2///////////3////////////////4/////////////////////////5///////////////////6////////
            {"Собрано: ", "Монеты: ", "Рекорд: ", "Пауза", "  Продолжить игру  ", "  Вернуться \n в меню  ", "  Магазин  "}};
    private int lang;

    public int getLang() {
        return lang;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }

    /**
     * Resize method
     * @param width - wight
     * @param height - height
     */
    @Override
    public void resize(int width, int height) {
        ScreenManager.getInstance().resize(width, height);
    }

    /**
     * Pause method
     */
    @Override
    public void pause() {

    }

    /**
     * Resume method
     */
    @Override
    public void resume() {

    }

    /**
     * Hide method
     */
    @Override
    public void hide() {

    }

}
