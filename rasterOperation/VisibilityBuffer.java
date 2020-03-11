package rasterOperation;

public class VisibilityBuffer {
    //pokud depth buffer provede úspěšný test potom se může informace v image bufferu přepsat
    // domácí úkol implelmentovat algortimus Z-buffer, 3 metody , testovaní Z souřadnice , clear metoda, drawpixel(x,y,z,color)
//metoda set backgroudnd
    //metoda clear  - z buffer clear  , zavolání img s hodnotou Z 1 (maximalní hloubka
    ///draw pixel z test  dostane ((x, y , z) , barvu) - hledá barvu nejbližší k pozorovateli na určitých souřadnicích - je to jen test


    private DepthBuffer depthBuffer;
    private ImageBuffer imageBuffer;

    public VisibilityBuffer( ImageBuffer imageBuffer) {

        this.imageBuffer = imageBuffer;
        this.depthBuffer= new DepthBuffer(imageBuffer.getHeight(),imageBuffer.getWidth())
    }


    public void clear(){
        depthBuffer.clear();
        imageBuffer.clean();
    }






    public void drawPixel(int x, int y) {
        if (img.checkIndex(x, y))
            img.setElement(x, y, color);
    }

    public String getPixel(int x, int y) {
        if (img.checkIndex(x, y)) &&img.getElement(x, y).isPresent()){
            int b = (img.getElement(x, y))
        }
        return

    }


}
