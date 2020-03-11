package rasterOperation;

import transforms.Integer;
import transforms.Col;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class ImageBuffer implements GridBuffer<Col> {

   /* private int width;
    private int height; */
    // private  Integer[][] ib;


    private BufferedImage im;


    public ImageBuffer(BufferedImage im) {

        this.im = im;
    }


    @Override
    public int getWidth() {
        return im.getWidth();
    }

    @Override
    public int getHeight() {
        return im.getHeight();
    }

    @Override
    public Optional<Col> getElement(int x, int y) {
        if (checkIndex(x, y)) {
            return Optional.of(new Col(im.getRGB(x, y)));
        }
        return Optional.empty();

    }


    @Override
    public void setElement(Col value, int x, int y) {

        im.setRGB(x, y, value.getRGB());


    }


    @Override
    public boolean checkIndex(int x, int y) {
        return false;
    }

    public void clean() {
        Graphics graphics = im.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, im.getWidth(), im.getHeight());
    }

}
