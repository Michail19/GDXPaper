package com.ms.game;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class SplashScreen extends AndroidApplication {
    /**
     * Splash screen
     *
     * @param bundle - bundle
     *
     * Created by MS
     */
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE); //* Clearing window
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Window applicationWindow = getApplicationWindow();
            WindowManager.LayoutParams attrib = applicationWindow.getAttributes();
            attrib.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        Intent intent = new Intent(this, AndroidLauncher.class); //* Creating splash screen
        startActivity(intent); //* Starting screen
        finish(); //* Destroying screen
    }
}