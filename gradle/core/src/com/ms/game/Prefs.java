package com.ms.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;

public class Prefs {
    private Preferences preferences;
    private float score, record;
    private int lang;
    private boolean a1 = false, a2 = false, a3 = false, a4 = false, a5 = false, a6 = false, a7 = false;

    /**
     * Class for saving results
     */
    public Prefs() {
        preferences = Gdx.app.getPreferences("Save");
        score = preferences.getFloat("Score");
        record = preferences.getFloat("Record");
        lang = preferences.getInteger("Lang");
        a1 = preferences.getBoolean("A1");
        a2 = preferences.getBoolean("A2");
        a3 = preferences.getBoolean("A3");
        a4 = preferences.getBoolean("A4");
        a5 = preferences.getBoolean("A5");
        a6 = preferences.getBoolean("A6");
        a7 = preferences.getBoolean("A7");
    }

    public void setScore(float score) {
        this.score += score;
        preferences.putFloat("Score", roundAvoid(this.score, 2));
        preferences.flush();
    }

    public void setRecord(float score) {
        if (score > this.record) {
            this.record = score;
            preferences.putFloat("Record", this.record);
            preferences.flush();
        }
    }

    public int getLang() {
        return lang;
    }

    public void setLang(int lang) {
        this.lang = lang;
        preferences.putInteger("Lang", this.lang);
        preferences.flush();
    }

    public static float roundAvoid(float value, int places) {
        return (float) (Math.round(value * Math.pow(10, places)) / Math.pow(10, places));
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

    public float getScore() {
        return score;
    }

    public float getRecord() {
        return record;
    }

    public void clearPrefs() {
        score = 0;
        record = 0;
        a1 = false;
        a2 = false;
        a3 = false;
        a4 = false;
        a5 = false;
        a6 = false;
        a7 = false;
        preferences.putFloat("Score", 0f);
        preferences.putFloat("Record", 0f);
        preferences.flush();
        preferences.clear();
    }
}
