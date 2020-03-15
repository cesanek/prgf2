package rasterOperation;

import java.util.Optional;

public class DepthBuffer implements GridBuffer<Float> {
    private float[][] buffer;


    private int height;
    private int width;

    public DepthBuffer(int height, int width) {
        this.height = height;
        this.width = width;
        buffer = new float[height][width];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public Optional<Float> getElement(int x, int y) {
        if (checkIndex(x, y)) {
            return Optional.of(buffer[x][y]);
        }
        return Optional.empty();
    }

    @Override
    public void setElement(Float value, int x, int y) {
        if (checkIndex(x, y)) {
            buffer[x][y] = value;
        }

    }

    public void clear() {
        for (int i = 0; i < width; i++) {
            for (int x = 0; x < height; i++) {
                buffer[x][i] = 1.0f;
            }
        }
    }

}