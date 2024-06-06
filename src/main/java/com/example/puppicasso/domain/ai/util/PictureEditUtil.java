package com.example.puppicasso.domain.ai.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PictureEditUtil {
    public static BufferedImage convertToPng(BufferedImage image) {
        BufferedImage pngImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = pngImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return pngImage;
    }

    public static boolean hasTransparency(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if ((image.getRGB(x, y) >> 24) == 0x00) {
                    return true;
                }
            }
        }
        return false;
    }

    public static BufferedImage addTransparency(BufferedImage image) {
        BufferedImage transparentImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = transparentImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return transparentImage;
    }
}
