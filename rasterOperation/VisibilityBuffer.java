package rasterOperation;

import transforms.Col;

public class VisibilityBuffer {

    private DepthBuffer depthBuffer;
    private ImageBuffer imageBuffer;

    public VisibilityBuffer(ImageBuffer imageBuffer) {

        this.imageBuffer = imageBuffer;
        this.depthBuffer = new DepthBuffer(imageBuffer.getHeight(), imageBuffer.getWidth());
    }


    public void clear() {
        depthBuffer.clear();
        imageBuffer.clean();
    }

    public boolean isVisible(int x, int y, float z) {
        return depthBuffer.getElement(x, y).get() >= z;
    }

    public void drawPixel(int x, int y, float z, Col col) {
        if (isVisible(x, y, z)) {
            imageBuffer.setElement(col, x, y);
            depthBuffer.setElement(z, x, y);


        }

    }


    public int getWidth() {
        return imageBuffer.getWidth();
    }

    public int getHeight() {
        return imageBuffer.getHeight();
    }

}
