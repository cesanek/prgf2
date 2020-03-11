package rendererOperation;

import model.Vertex;
import transforms.Vec3D;

import java.beans.Visibility;

public class RasterizeTriangle {

    private Visibility visbility;

    public RasterizeTriangle(Visibility visbility) {
        this.visbility = visbility;
    }


    public void rasterize(Vertex a, Vertex b, Vertex c) {
        a = a.dehomog();
        //...


        // double xa= a.getPosition().getX()* Vec3D(1,-1,1);
        double xa = 0.5 * (a.getPosition().getW() - 1) * (a.getPosition().getX() + 1);
        double ya = 0.5 * (a.getPosition().getW() - 1) * (a.getPosition().getX() + 1);
        double xb = 0.5 * (a.getPosition().getW() - 1) * (a.getPosition().getX() + 1);// dodělat...
        double yb = 0.5 * (a.getPosition().getW() - 1) * (a.getPosition().getX() + 1);
        double yc = 0.5 * (a.getPosition().getW() - 1) * (a.getPosition().getX() + 1);
        //xa,ya,xb,yb,xc,yc


        //ya<=yb<=yc
        for (int y = (int) ya + 1; ya <= yb; y++) {
            double s1 = (y - ya) / (yb - ya);
            double x1 = (xa * (1 - s1) + (xb * s1));
            Vertex ab =a.mul(1-s1).add(b.mul(s1));
            Vertex ac =c.mul(1-s1).add(b.mul(s1)); // počítat podobně
            double x2;

          /*  double s2 = (y - ya) / (yc - ya);
            int x2 = (int) (xa * (1 - s2) + (yc * s1)); */

            for (int x = (int) x1 + 1; x < x2; x++) {
               // double s2 = (y - ya) / (yc - ya);
                double s= (x-x1)/(x2-x1);
                Vertex abc= ab.mul(1-s).add(ac.mul(s));
                double z= abc.getPosition().getZ();
                int color= abc.getColor().getRGB();
                visbility.drawPixelZTest(x,y,z,color);


            }

        }
    }

}


