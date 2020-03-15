package rendererOperation;

import model.Part;
import model.Solid;
import model.Vertex;
import transforms.Mat4;
import view.Raster;

public class Renderer {
    private Mat4 model;
    private Mat4 view;
    private Mat4 projection;
    private Raster raster;
    private Rasterizer rast;

    public Renderer(Raster rast, Rasterizer raszerizer) {
        this.raster = rast;
        this.rast = raszerizer;


    }

    public void renderer(Solid solid) {


        for (Part part : solid.getPartBuffer()) {
            switch (part.getType()) {
                case TRIANGLES:
                    for (int i = 0; i < part.getCount() - 1; i++) {
                        int i1, i2, i3;
                        i1 = part.getStartIndex() + i * 3;
                        i2 = i1 + 1;
                        i3 = i2 + 1;
                        int ii1 = solid.getIndexBuffer().get(i1);
                        int ii2 = solid.getIndexBuffer().get(i2);
                        int ii3 = solid.getIndexBuffer().get(i3);
                        drawTriangle(solid.getVertexBuffer().get(ii1),
                                solid.getVertexBuffer().get(ii2),
                                solid.getVertexBuffer().get(ii3)
                        );

                    }
                    break;
            }
        }
    }


    public void drawTriangle(Vertex a, Vertex b, Vertex c) {


        a = new Vertex(a.getPosition().mul(model).mul(view).mul(projection), a.getColor());
        b = new Vertex(b.getPosition().mul(model).mul(view).mul(projection), b.getColor());
        c = new Vertex(c.getPosition().mul(model).mul(view).mul(projection), c.getColor());

        double ax = a.getPosition().getX();
        double ay = a.getPosition().getY();
        double az = a.getPosition().getZ();
        double aw = a.getPosition().getW();

        double bx = b.getPosition().getX();
        double by = b.getPosition().getY();
        double bz = b.getPosition().getZ();
        double bw = b.getPosition().getW();


        double cx = c.getPosition().getX();
        double cy = c.getPosition().getY();
        double cz = c.getPosition().getZ();
        double cw = c.getPosition().getW();


        if ((ax < -aw && bx < -bw && cx < -cw) ||
                (ax > aw && bx > bw && cx > cw) ||
                (ay < -aw && by < -bw && cy < -cw) ||
                (ay > aw && by > bw && cy > cw) ||
                (az < 0 && bz < 0 && cz < 0) ||
                (az > aw && bz > bw && cz > cw)
        ) {
            return;
        }


        if (az < bz) {
            Vertex help = b;
            b.add(a);
            a.add(help);
        }
        if (bz < cz) {
            Vertex help = c;
            c.add(b);
            b.add(help);

        }
        if (az < bz) {
            Vertex help = b;
            b.add(a);
            a.add(help);

        }


        if (az < 0) {
            return;
        }
        if (bz < 0) {
            double t = az / (az - bz);
            Vertex vb = b.mul(1 - t).add(a.mul(t));
            double tt = (0 - bz) / (az - bz);
            Vertex vc = c.mul(1 - tt).add(a.mul(tt));
            vc.setColor(c.getColor());
            vb.setColor(b.getColor());

            rast.rasterizeTriangle(a, vb, vc);
            return;


        }
        if (c.getPosition().getZ() < 0) {
            double t = bz / (bz - cz);
            Vertex xb = c.mul(1 - t).add(b.mul(t));
            double tt = (0 - cz) / (az - cz);
            Vertex xc = c.mul(1 - tt).add(a.mul(tt));

            xc.setColor(c.getColor());
            xb.setColor(c.getColor());
            rast.rasterizeTriangle(a, b, xb);
            rast.rasterizeTriangle(a, xc, xb);
            return;

        }


        rast.rasterizeTriangle(a, b, c);

    }


    public Mat4 getModel() {
        return model;
    }

    public void setModel(Mat4 model) {
        this.model = model;
    }

    public Mat4 getProjection() {
        return projection;
    }

    public void setProjection(Mat4 projection) {
        this.projection = projection;
    }

    public void setView(Mat4 view) {
        this.view = view;
    }


    public Mat4 getView() {
        return view;
    }


    public void clear() {

    }
}



