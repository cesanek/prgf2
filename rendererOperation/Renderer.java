package rendererOperation;

import model.Part;
import model.Solid;
import model.Vertex;
import transforms.Mat4;
import transforms.Point3D;

public class Renderer {
    private Mat4 model;
    private Mat4 view;
    private Mat4 projection;
    private Raster raster;

    public Renderer(Raster raster) {
        this.raster = raster;

    }

    public void renderer(Solid solid) {
        //dodělat transformace todo




        for (Part part : solid.getPartBuffer()) {
            switch (part.getType()) {
                case TRIANGLES:
                    for (int i = 0; i < part.getCount() - 1; i++) {
                        //nutno spočítat index
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

    //pro vykreslení vrcholů chce zachytit 3 vrcholy
    public void drawTriangle(Vertex a, Vertex b, Vertex c) {

        //předpoklad že je transofmované všema třema maticema n,v,p
        //todo rychle ořezaní 78 slide ? Z-near kladné!

        //seřazení vrcholů podle z souřadnice -Z
        a = new Vertex(a.getPosition().mul(model).mul(view).mul(projection), a.getColor());
        b=new Vertex(b.getPosition().mul(model).mul(view).mul(projection), b.getColor());
        c=new Vertex(c.getPosition().mul(model).mul(view).mul(projection), c.getColor());

        if (a.getPosition().getZ() < b.getPosition().getZ()) {
            Vertex pomocna = b;
            b.add(c);
            a.add(pomocna);
            if (a.getPosition().getZ() < c.getPosition().getZ()) {
                Vertex pomocna2 = a;
                a.add(c);
                c.add(pomocna2);

            }
            if (b.getPosition().getZ() < c.getPosition().getZ()) {
                Vertex pomocna2 = b;
                b.add(c);
                c.add(pomocna2);

            }
        }

        if (a.getPosition().getZ() < 0) {
            return;
        }
        if (b.getPosition().getZ() < 0) {
            //todo linearní interpolace ořezaní na trojúhelník
            //spocitat 2 body vrcholy ade a rastrtriangle
            Vertex d=new Vertex(new Point3D(), new Col());
            Vertex e= new Vertex(new Point3D(), new Col());

        }
        if (c.getPosition().getZ() < 0) {
            //todo linearní interpolace ořezaní na trojúhelník
            //spocitat 2 body vrcholy (,b,e) a rastrtriangle
            //visibility
            //fotka bez výpočtu

        }


        rasterizer.rasterTriangle(v1, v2, v3);
    }


}
