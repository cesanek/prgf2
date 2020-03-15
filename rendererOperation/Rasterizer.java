package rendererOperation;

import model.Vertex;
import rasterOperation.VisibilityBuffer;
import transforms.Point3D;




public class Rasterizer {
    private VisibilityBuffer visibilityBuffer;
    private Shader shader;


    public Rasterizer(VisibilityBuffer visibilityBuffer, Shader shader) {
        this.visibilityBuffer = visibilityBuffer;
        this.shader = shader;
    }


    public void rasterizeTriangle(Vertex a, Vertex b, Vertex c) {
        Point3D corA = getPlace(a.getPosition());
        Point3D corB = getPlace(b.getPosition());
        Point3D corC = getPlace(c.getPosition());


        b = b.dehomog();
        c = c.dehomog();
        a = a.dehomog();


        if (corA.getY() > corB.getY()) {
            Point3D help = corA;
            corA = corB;
            corB = help;

        }

        if (corB.getY() > corC.getY()) {
            Point3D help = corB;
            corB = corC;
            corC = help;
        }

        if (corA.getY() > corB.getY()) {
            Point3D help = corA;
            corA = corB;
            corB = help;

        }

        for (int y = Math.max((int) corA.getY() + 1, 0); y < Math.min(corB.getY(), visibilityBuffer.getHeight() - 1); y++) {
            double t1 = (y - corA.getY()) / (corB.getY() - corA.getY());
            double t2 = (y - corA.getY()) / (corC.getY() - corA.getY());
            double x1 = corA.getX() * (1.0 - t1) + corB.getX() * t1;
            double x2 = corA.getX() * (1.0 - t2) + corC.getX() * t2;
            double z1 = corA.getZ() * (1.0 - t1) + corB.getZ() * t1;
            double z2 = corA.getZ() * (1.0 - t2) + corC.getZ() * t2;


            if (x1 > x2) {
                double help = x2;
                x2 = x1;
                x1 = help;
                help = z1;
                z1 = z2;
                z2 = help;
            }


            for (int x = Math.max((int) x1 + 1, 0); x < Math.min(x2, visibilityBuffer.getWidth() - 1); x++) {
                double t = (x - x1) / (x2 - x1);
                double z = z1 * (1 - t) + z2 * t;
                Vertex abc = new Vertex(null, a.getColor().add(b.getColor().add(c.getColor())));
                visibilityBuffer.drawPixel(x, y, (float) z, shader.getColor(abc));
            }
        }


        for (int y = Math.max((int) corB.getY() + 1, 0); y < Math.min(corC.getY(), visibilityBuffer.getHeight() - 1); y++) {
            double t1 = (y - corB.getY()) / (corC.getY() - corB.getY());
            double t2 = (y - corA.getY()) / (corC.getY() - corA.getY());
            double x1 = corB.getX() * (1.0 - t1) + corC.getX() * t1;
            double x2 = corA.getX() * (1.0 - t2) + corC.getX() * t2;
            double z1 = corB.getZ() * (1.0 - t1) + corC.getZ() * t1;
            double z2 = corA.getZ() * (1.0 - t2) + corC.getZ() * t2;


            if (x1 > x2) {
                double help = x2;
                x2 = x1;
                x1 = help;
                help = z1;
                z1 = z2;
                z2 = help;
            }

            for (int x = Math.max((int) x1 + 1, 0); x < Math.min(x2, visibilityBuffer.getWidth() - 1); x++) {
                double t = (x - x1) / (x2 - x1);
                double z = z1 * (1 - t) + z2 * t;
                Vertex abc = new Vertex(null, a.getColor().add(b.getColor().add(c.getColor())));
                visibilityBuffer.drawPixel(x, y, (float) z, shader.getColor(abc));
            }


        }
    }


    public Point3D getPlace(Point3D point3D) {

        double x = (point3D.getX() + 1) * (visibilityBuffer.getWidth() - 1) / 2;
        double y = (-point3D.getY() + 1) * (visibilityBuffer.getHeight() - 1) / 2;

        return new Point3D(x, y, point3D.getZ(), point3D.getW());

    }


}


