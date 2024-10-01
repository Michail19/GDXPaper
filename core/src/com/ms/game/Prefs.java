package com.ms.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;

public class Prefs {
    private Preferences preferences;
    private float record, collectedPaper;
    private int lang, score, top, down, a12, amountSpeedBooster, amountMagnetBooster, amountIncreaseWeightBooster, bg, usedBusters, jumps, recycledPaper, forRecycled, stageFactory;
    private boolean a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a17;

    /**
     * Class for saving results
     */
    public Prefs() {
        preferences = Gdx.app.getPreferences("Save");
        score = preferences.getInteger("Score");
        record = preferences.getFloat("Record");
        lang = preferences.getInteger("Lang");
        top = preferences.getInteger("Top");
        down = preferences.getInteger("Down");
        bg = preferences.getInteger("BG");
        a1 = preferences.getBoolean("A1");
        a2 = preferences.getBoolean("A2");
        a3 = preferences.getBoolean("A3");
        a4 = preferences.getBoolean("A4");
        a5 = preferences.getBoolean("A5");
        a6 = preferences.getBoolean("A6");
        a7 = preferences.getBoolean("A7");
        a8 = preferences.getBoolean("A8");
        a9 = preferences.getBoolean("A9");
        a10 = preferences.getBoolean("A10");
        a11 = preferences.getBoolean("A11");
        a12 = preferences.getInteger("A12");
        a17 = preferences.getBoolean("A17");
        collectedPaper = preferences.getFloat("CollectedPaper");
        usedBusters = preferences.getInteger("UsedBusters");
        jumps = preferences.getInteger("Jumps");
        amountSpeedBooster = preferences.getInteger("AmountSpeedBooster");
        amountMagnetBooster = preferences.getInteger("AmountMagnetBooster");
        amountIncreaseWeightBooster = preferences.getInteger("AmountIncreaseWeightBooster");
        recycledPaper = preferences.getInteger("RecycledPaper");
        forRecycled = preferences.getInteger("ForRecycled");
        stageFactory  = preferences.getInteger("StageFactory");
    }

    public void setScore(int score) {
        this.score += score;
        preferences.putInteger("Score", this.score);
        preferences.flush();
    }

    public void setRecord(float score) {
        if (score > this.record) {
            this.record += score;
            preferences.putFloat("Record", this.record);
            preferences.flush();
        }
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
        preferences.putInteger("Top", this.top);
        preferences.flush();
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
        preferences.putInteger("Down", this.down);
        preferences.flush();
    }

    public int getLang() {
        return lang;
    }

    public void setLang(int lang) {
        this.lang = lang;
        preferences.putInteger("Lang", this.lang);
        preferences.flush();
    }

    public int getAmountSpeedBooster() {
        return amountSpeedBooster;
    }

    public void setAmountSpeedBooster(int amountSpeedBooster) {
        this.amountSpeedBooster += amountSpeedBooster;
        preferences.putInteger("AmountSpeedBooster", this.amountSpeedBooster);
        preferences.flush();
    }

    public int getAmountMagnetBooster() {
        return amountMagnetBooster;
    }

    public void setAmountMagnetBooster(int amountMagnetBooster) {
        this.amountMagnetBooster += amountMagnetBooster;
        preferences.putInteger("AmountMagnetBooster", this.amountMagnetBooster);
        preferences.flush();
    }

    public int getAmountIncreaseWeightBooster() {
        return amountIncreaseWeightBooster;
    }

    public void setAmountIncreaseWeightBooster(int amountIncreaseWeightBooster) {
        this.amountIncreaseWeightBooster += amountIncreaseWeightBooster;
        preferences.putInteger("AmountIncreaseWeightBooster", this.amountIncreaseWeightBooster);
        preferences.flush();
    }

    public int getJumps() {
        return jumps;
    }

    public void setJumps(int jumps) {
        this.jumps += jumps;
        preferences.putInteger("Jumps", this.jumps);
        preferences.flush();
    }

    public void setA1() {
        a1 = !a1;
        preferences.putBoolean("A1", a1);
        preferences.flush();
    }
    public void setA2() {
        a2 = !a2;
        preferences.putBoolean("A2", a2);
        preferences.flush();
    }
    public void setA3() {
        a3 = !a3;
        preferences.putBoolean("A3", a3);
        preferences.flush();
    }
    public void setA4() {
        a4 = !a4;
        preferences.putBoolean("A4", a4);
        preferences.flush();
    }
    public void setA5() {
        a5 = !a5;
        preferences.putBoolean("A5", a5);
        preferences.flush();
    }
    public void setA6() {
        a6 = !a6;
        preferences.putBoolean("A6", a6);
        preferences.flush();
    }
    public void setA7() {
        a7 = !a7;
        preferences.putBoolean("A7", a7);
        preferences.flush();
    }
    public void setA8() {
        a8 = !a8;
        preferences.putBoolean("A8", a8);
        preferences.flush();
    }
    public void setA9() {
        a9 = !a9;
        preferences.putBoolean("A9", a9);
        preferences.flush();
    }
    public void setA10() {
        a10 = !a10;
        preferences.putBoolean("A10", a10);
        preferences.flush();
    }
    public void setA11() {
        a11 = !a11;
        preferences.putBoolean("A11", a11);
        preferences.flush();
    }
    public void setA12(int a12) {
        this.a12 = a12;
        preferences.putInteger("A12", this.a12);
        preferences.flush();
    }

    public boolean isA1() {
        return a1;
    }
    public boolean isA2() {
        return a2;
    }
    public boolean isA3() {
        return a3;
    }
    public boolean isA4() {
        return a4;
    }
    public boolean isA5() {
        return a5;
    }
    public boolean isA6() {
        return a6;
    }
    public boolean isA7() {
        return a7;
    }
    public boolean isA8() {
        return a8;
    }
    public boolean isA9() {
        return a9;
    }
    public boolean isA10() {
        return a10;
    }
    public boolean isA11() {
        return a11;
    }
    public int getA12() {
        return a12;
    }

    public int getScore() {
        return score;
    }
    public float getRecord() {
        return record;
    }

    public boolean isA17() {
        return a17;
    }

    public void setA17() {
        a17 = !a17;
        preferences.putBoolean("A17", a17);
        preferences.flush();
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
        preferences.putInteger("BG", this.bg);
        preferences.flush();
    }

    public float getCollectedPaper() {
        return collectedPaper;
    }

    public void setCollectedPaper(float collectedPaper) {
        this.collectedPaper += collectedPaper;
        preferences.putFloat("CollectedPaper", this.collectedPaper);
        preferences.flush();
    }

    public int getUsedBusters() {
        return usedBusters;
    }

    public void setUsedBusters(int usedBusters) {
        this.usedBusters += usedBusters;
        preferences.putInteger("UsedBusters", this.usedBusters);
        preferences.flush();
    }

    public int getRecycledPaper() {
        return recycledPaper;
    }

    public void setRecycledPaper(int recycledPaper) {
        this.recycledPaper += recycledPaper;
        preferences.putInteger("RecycledPaper", this.recycledPaper);
        preferences.flush();
    }

    public int getForRecycled() {
        return forRecycled;
    }

    public void setForRecycled(int forRecycled) {
        this.forRecycled += forRecycled;
        preferences.putInteger("ForRecycled", this.forRecycled);
        preferences.flush();
    }

    public void setForRecycledToZero() {
        forRecycled = 0;
        preferences.putInteger("ForRecycled", 0);
        preferences.flush();
    }

    public int getStageFactory() {
        return stageFactory;
    }

    public void setStageFactory(int stageFactory) {
        this.stageFactory = stageFactory;
        preferences.putInteger("StageFactory", this.stageFactory);
        preferences.flush();
    }

    public void clearPrefs() {
        score = 0;
        record = 0;
        top = 0;
        down = 0;
        bg = 0;
        forRecycled = 0;
        recycledPaper = 0;
        stageFactory = 0;
        a1 = false;
        a2 = false;
        a3 = false;
        a4 = false;
        a5 = false;
        a6 = false;
        a7 = false;
        a8 = false;
        a9 = false;
        a10 = false;
        a11 = false;
        a12 = 0;
        a17 = false;
        preferences.putBoolean("A1", false);
        preferences.putBoolean("A2", false);
        preferences.putBoolean("A3", false);
        preferences.putBoolean("A4", false);
        preferences.putBoolean("A5", false);
        preferences.putBoolean("A6", false);
        preferences.putBoolean("A7", false);
        preferences.putBoolean("A8", false);
        preferences.putBoolean("A9", false);
        preferences.putBoolean("A10", false);
        preferences.putBoolean("A11", false);
        preferences.putBoolean("A17", false);
        preferences.putInteger("A12", 0);
        preferences.putInteger("BG", 0);
        preferences.putInteger("Top", 0);
        preferences.putInteger("Down", 0);
        preferences.putInteger("Lang", 0);
        preferences.putInteger("Score", 0);
        preferences.putFloat("Record", 0f);
        preferences.putInteger("RecycledPaper", 0);
        preferences.putInteger("ForRecycled", 0);
        preferences.putInteger("StageFactory", 0);
        preferences.flush();
        preferences.clear();
    }
}
