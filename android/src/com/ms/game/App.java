package com.ms.game;

import android.app.Application;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

public class App extends Application {
    /**
     * Splash screen time setup
     *
     * Created by MS
     */
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(1)); //* Set 1 second length splash screen
    }
}
