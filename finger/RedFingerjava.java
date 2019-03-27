package io.night.convertor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * 将黑色的指纹转化为红色
 * Created By Night 2019/3/11
 */
public class Finger666 {
    public static void bloodFinger(final String inutFilePath,final String outputFilePath) throws IOException {
//        String i = "/tmp/tt2.jpg";
//        String o = "/tmp/test2.jpg";
        BufferedImage image = ImageIO.read(new File(inutFilePath));

        int w = image.getWidth();
        int h = image.getHeight();
        float[] rgb = new float[3];

        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int pixel = image.getRGB(x, y);

                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);

                float[] hsbColor = Color.RGBtoHSB((int) rgb[0], (int) rgb[1], (int) rgb[2], null);

                hsbColor[0] = 0;


                hsbColor[2] = 0.79f + (0.21f * hsbColor[2]);
                if (hsbColor[2] > 0.97f) {
                    hsbColor[1] = 0f;
                } else {
                    hsbColor[1] = 1f;
                }
                int cc = Color.HSBtoRGB(hsbColor[0], hsbColor[1], hsbColor[2]);
                bi.setRGB(x, y, cc);
            }
        }

        ImageIO.write(bi, "jpg", new File(outputFilePath));

    }
}
