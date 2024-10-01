package com.ms.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;

import java.nio.ByteBuffer;

public class ScreenshotFactory {
    /**
     * Class for making screenshots
     * Main receiver is GameOverScreen.java class to BG
     *
     * Created by MS
     */

    /**
     * Function, which make a request to function getScreen() and save screenshot
     * (Now isn't work, exception with saving file)
     */
    public static void saveScreenshot() {
        try {
            FileHandle fh;
            //File file;
            fh = new FileHandle("screenshot.png");
            Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
            PixmapIO.writePNG(fh, pixmap); // ** Exception
            //file = new File(fh.file().getPath());
            //if (file.createNewFile()) System.out.println("created!");
            pixmap.dispose();
        }
        catch (Exception ignored){}
    }

    /**
     * Function, which is making screenshot and is returning in pixmap
     *
     * @param x - position start x
     * @param y - position start y
     * @param w - width screenshot
     * @param h - height screenshot
     * @param yDown - boolean parameter, which will remake screenshot if main function can't do it
     * @return - return pixmap
     */
    private static Pixmap getScreenshot(int x, int y, int w, int h, boolean yDown) {
        final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);

        if (yDown) {
            ByteBuffer pixels = pixmap.getPixels();
            int numBytes = w * h * 4;
            byte[] lines = new byte[numBytes];
            int numBytesPerLine = w * 4;
            for (int i = 0; i < h; i++) {
                pixels.position((h - i - 1) * numBytesPerLine);
                pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
            }
            pixels.clear();
            pixels.put(lines);
        }

        return pixmap;
    }
}
