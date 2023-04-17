package ru.egor.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    private int width;
    private int height;
    private double maxRatio;
    TextColorSchema schema = new Schema();

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        if (((double) (img.getWidth() / img.getHeight()) > this.maxRatio) && this.maxRatio != 0) {
            throw new BadImageSizeException((double) (img.getWidth() / img.getHeight()), this.maxRatio);
        }

        // Обновление высоты и ширины
        int newHeight;
        int newWidth;

        // Проверка длины и ширины картинки

        if (width != 0 && img.getWidth() > width && img.getHeight() <= height) {
            double ratio = Math.floor(((((double) img.getWidth()) / img.getHeight()) * 10)) / 10;
            newWidth = width;
            newHeight = (int) Math.floor(newWidth / ratio);
        } else if (height != 0 && img.getHeight() > height && img.getWidth() <= width) {
            double ratio = Math.floor(((((double) img.getWidth()) / img.getHeight()) * 10)) / 10;
            newHeight = height;
            newWidth = (int) Math.floor((double) newHeight * ratio);
        } else if (height != 0 && width != 0 && img.getWidth() > width && img.getHeight() > height) {
            double ratio = (Math.floor(((double) Math.max(img.getHeight(), img.getWidth()) / width) * 10)) / 10;
            if (img.getWidth() > img.getHeight()) {
                newWidth = width;
                newHeight = (int) Math.floor((double) img.getHeight() / ratio);
            } else {
                newHeight = height;
                newWidth = (int) Math.floor((double) img.getWidth() / ratio);
            }

        } else {
            newHeight = img.getHeight();
            newWidth = img.getWidth();
        }

        System.out.println(newWidth);
        System.out.println(newHeight);


        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();

        StringBuilder sb = new StringBuilder();

        for (int h = 0; h < bwImg.getHeight(); h++) {
            for (int w = 0; w < bwImg.getWidth(); w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                sb.append(c);
                sb.append(c);
            }
            sb.append("\n");
        }

        return sb.toString();
    }



    @Override
    public void setMaxWidth(int width) {
        if (width > 0) {
            this.width = width;
        }
    }

    @Override
    public void setMaxHeight(int height) {
        if (height > 0) {
            this.height = height;
        }
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        if (maxRatio > 0) {
            this.maxRatio = maxRatio;
        }
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
