package com.ms.game.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ChallengePrefs {
    private Preferences preferences;
    private boolean m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23, m24, m25, m26, m27, m28, m29, m30, m31, m32, m33;
    private String timeInGame;

    /**
     * Class for saving results
     */
    public ChallengePrefs() {
        preferences = Gdx.app.getPreferences("ChallengeSave");
        m1 = preferences.getBoolean("M1");
        m2 = preferences.getBoolean("M2");
        m3 = preferences.getBoolean("M3");
        m4 = preferences.getBoolean("M4");
        m5 = preferences.getBoolean("M5");
        m6 = preferences.getBoolean("M6");
        m7 = preferences.getBoolean("M7");
        m8 = preferences.getBoolean("M8");
        m9 = preferences.getBoolean("M9");
        m10 = preferences.getBoolean("M10");
        m11 = preferences.getBoolean("M11");
        m12 = preferences.getBoolean("M12");
        m13 = preferences.getBoolean("M13");
        m14 = preferences.getBoolean("M14");
        m15 = preferences.getBoolean("M15");
        m16 = preferences.getBoolean("M16");
        m17 = preferences.getBoolean("M17");
        m18 = preferences.getBoolean("M18");
        m19 = preferences.getBoolean("M19");
        m20 = preferences.getBoolean("M20");
        m21 = preferences.getBoolean("M21");
        m22 = preferences.getBoolean("M22");
        m23 = preferences.getBoolean("M23");
        m24 = preferences.getBoolean("M24");
        m25 = preferences.getBoolean("M25");
        m26 = preferences.getBoolean("M26");
        m27 = preferences.getBoolean("M27");
        m28 = preferences.getBoolean("M28");
        m29 = preferences.getBoolean("M29");
        m30 = preferences.getBoolean("M30");
        m31 = preferences.getBoolean("M31");
        m32 = preferences.getBoolean("M32");
        m33 = preferences.getBoolean("M33");
        timeInGame = preferences.getString("TimeInGame");
    }

    public boolean isM1() {
        return m1;
    }
    public boolean isM2() {
        return m2;
    }
    public boolean isM3() {
        return m3;
    }
    public boolean isM4() {
        return m4;
    }
    public boolean isM5() {
        return m5;
    }
    public boolean isM6() {
        return m6;
    }
    public boolean isM7() {
        return m7;
    }
    public boolean isM8() {
        return m8;
    }
    public boolean isM9() {
        return m9;
    }
    public boolean isM10() {
        return m10;
    }
    public boolean isM11() {
        return m11;
    }
    public boolean isM12() {
        return m12;
    }
    public boolean isM13() {
        return m13;
    }
    public boolean isM14() {
        return m14;
    }
    public boolean isM15() {
        return m15;
    }
    public boolean isM16() {
        return m16;
    }
    public boolean isM17() {
        return m17;
    }
    public boolean isM18() {
        return m18;
    }
    public boolean isM19() {
        return m19;
    }
    public boolean isM20() {
        return m20;
    }
    public boolean isM21() {
        return m21;
    }
    public boolean isM22() {
        return m22;
    }
    public boolean isM23() {
        return m23;
    }
    public boolean isM24() {
        return m24;
    }
    public boolean isM25() {
        return m25;
    }
    public boolean isM26() {
        return m26;
    }
    public boolean isM27() {
        return m27;
    }
    public boolean isM28() {
        return m28;
    }
    public boolean isM29() {
        return m29;
    }
    public boolean isM30() {
        return m30;
    }
    public boolean isM31() {
        return m31;
    }
    public boolean isM32() {
        return m32;
    }
    public boolean isM33() {
        return m33;
    }

    public void setM1() {
        m1 = !m1;
        preferences.putBoolean("M1", m1);
        preferences.flush();
    }
    public void setM2() {
        m2 = !m2;
        preferences.putBoolean("M2", m2);
        preferences.flush();
    }
    public void setM3() {
        m3 = !m3;
        preferences.putBoolean("M3", m3);
        preferences.flush();
    }
    public void setM4() {
        m4 = !m4;
        preferences.putBoolean("M4", m4);
        preferences.flush();
    }
    public void setM5() {
        m5 = !m5;
        preferences.putBoolean("M5", m5);
        preferences.flush();
    }
    public void setM6() {
        m6 = !m6;
        preferences.putBoolean("M6", m6);
        preferences.flush();
    }
    public void setM7() {
        m7 = !m7;
        preferences.putBoolean("M7", m7);
        preferences.flush();
    }
    public void setM8() {
        m8 = !m8;
        preferences.putBoolean("M8", m8);
        preferences.flush();
    }
    public void setM9() {
        m9 = !m9;
        preferences.putBoolean("M9", m9);
        preferences.flush();
    }
    public void setM10() {
        m10 = !m10;
        preferences.putBoolean("M10", m10);
        preferences.flush();
    }
    public void setM11() {
        m11 = !m11;
        preferences.putBoolean("M11", m11);
        preferences.flush();
    }
    public void setM12() {
        m12 = !m12;
        preferences.putBoolean("M12", m12);
        preferences.flush();
    }
    public void setM13() {
        m13 = !m13;
        preferences.putBoolean("M13", m13);
        preferences.flush();
    }
    public void setM14() {
        m14 = !m14;
        preferences.putBoolean("M14", m14);
        preferences.flush();
    }
    public void setM15() {
        m15 = !m15;
        preferences.putBoolean("M15", m15);
        preferences.flush();
    }
    public void setM16() {
        m16 = !m16;
        preferences.putBoolean("M16", m16);
        preferences.flush();
    }
    public void setM17() {
        m17 = !m17;
        preferences.putBoolean("M17", m17);
        preferences.flush();
    }
    public void setM18() {
        m18 = !m18;
        preferences.putBoolean("M18", m18);
        preferences.flush();
    }
    public void setM19() {
        m19 = !m19;
        preferences.putBoolean("M19", m19);
        preferences.flush();
    }
    public void setM20() {
        m20 = !m20;
        preferences.putBoolean("M20", m20);
        preferences.flush();
    }
    public void setM21() {
        m21 = !m21;
        preferences.putBoolean("M21", m21);
        preferences.flush();
    }
    public void setM22() {
        m22 = !m22;
        preferences.putBoolean("M22", m22);
        preferences.flush();
    }
    public void setM23() {
        m23 = !m23;
        preferences.putBoolean("M23", m23);
        preferences.flush();
    }
    public void setM24() {
        m24 = !m24;
        preferences.putBoolean("M24", m24);
        preferences.flush();
    }
    public void setM25() {
        m25 = !m25;
        preferences.putBoolean("M25", m25);
        preferences.flush();
    }
    public void setM26() {
        m26 = !m26;
        preferences.putBoolean("M26", m26);
        preferences.flush();
    }
    public void setM27() {
        m27 = true;
        preferences.putBoolean("M27", true);
        preferences.flush();
    }
    public void setM28() {
        m28 = !m28;
        preferences.putBoolean("M28", m28);
        preferences.flush();
    }
    public void setM29() {
        m29 = !m29;
        preferences.putBoolean("M29", m29);
        preferences.flush();
    }
    public void setM30() {
        m30 = !m30;
        preferences.putBoolean("M30", m30);
        preferences.flush();
    }
    public void setM31() {
        m31 = !m31;
        preferences.putBoolean("M31", m31);
        preferences.flush();
    }
    public void setM32() {
        m32 = !m32;
        preferences.putBoolean("M32", m32);
        preferences.flush();
    }
    public void setM33() {
        m33 = !m33;
        preferences.putBoolean("M33", m33);
        preferences.flush();
    }

    public String getTimeInGame() {
        return timeInGame;
    }

    public void setTimeInGame(String timeInGame) {
        this.timeInGame = timeInGame;
        preferences.putString("TimeInGame", this.timeInGame);
        preferences.flush();
    }

    public void clearPrefs() {
        m1 = false;
        m2 = false;
        m3 = false;
        m4 = false;
        m5 = false;
        m6 = false;
        m7 = false;
        m8 = false;
        m9 = false;
        m10 = false;
        m11 = false;
        m12 = false;
        m13 = false;
        m14 = false;
        m15 = false;
        m16 = false;
        m17 = false;
        m18 = false;
        m19 = false;
        m20 = false;
        m21 = false;
        m22 = false;
        m23 = false;
        m24 = false;
        m25 = false;
        m26 = false;
        m27 = false;
        m28 = false;
        m29 = false;
        m30 = false;
        m31 = false;
        m32 = false;
        m33 = false;
        timeInGame = "00:00:00";
        preferences.putBoolean("M1", false);
        preferences.putBoolean("M2", false);
        preferences.putBoolean("M3", false);
        preferences.putBoolean("M4", false);
        preferences.putBoolean("M5", false);
        preferences.putBoolean("M6", false);
        preferences.putBoolean("M7", false);
        preferences.putBoolean("M8", false);
        preferences.putBoolean("M9", false);
        preferences.putBoolean("M10", false);
        preferences.putBoolean("M11", false);
        preferences.putBoolean("M12", false);
        preferences.putBoolean("M13", false);
        preferences.putBoolean("M14", false);
        preferences.putBoolean("M15", false);
        preferences.putBoolean("M16", false);
        preferences.putBoolean("M17", false);
        preferences.putBoolean("M18", false);
        preferences.putBoolean("M19", false);
        preferences.putBoolean("M20", false);
        preferences.putBoolean("M21", false);
        preferences.putBoolean("M22", false);
        preferences.putBoolean("M23", false);
        preferences.putBoolean("M24", false);
        preferences.putBoolean("M25", false);
        preferences.putBoolean("M26", false);
        preferences.putBoolean("M27", false);
        preferences.putBoolean("M28", false);
        preferences.putBoolean("M29", false);
        preferences.putBoolean("M30", false);
        preferences.putBoolean("M31", false);
        preferences.putBoolean("M32", false);
        preferences.putBoolean("M33", false);
        preferences.putString("TimeInGame", timeInGame);
        preferences.flush();
    }
}
