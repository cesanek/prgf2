package geometryObject;

import model.Part;
import model.Solid;
import model.TypeTopology;
import model.Vertex;
import transforms.Col;
import transforms.Point3D;

public class Cube extends Solid {


    public Cube() {
        getVertexBuffer().add(new Vertex(new Point3D(-1, -1, 1), new Col(255, 255, 255)));//A-0
        getVertexBuffer().add(new Vertex(new Point3D(1, -1, 1), new Col(255, 255, 255))); //b-1
        getVertexBuffer().add(new Vertex(new Point3D(1, -1, -1), new Col(255, 255, 255))); //c-2
        getVertexBuffer().add(new Vertex(new Point3D(-1, -1, -1), new Col(255, 255, 255))); //d-3


        getVertexBuffer().add(new Vertex(new Point3D(-1, 1, 1), new Col(255, 255, 255))); //e-4
        getVertexBuffer().add(new Vertex(new Point3D(1, 1, 1), new Col(255, 255, 255))); //f-5
        getVertexBuffer().add(new Vertex(new Point3D(1, 1, -1), new Col(255, 255, 255))); //g-6
        getVertexBuffer().add(new Vertex(new Point3D(-1, 1, -1), new Col(255, 255, 255))); //h-7

        getPartBuffer().add(new Part(TypeTopology.TRIANGLES, 8, 0));


        getIndexBuffer().add(3);
        getIndexBuffer().add(2);
        getIndexBuffer().add(7);
        getIndexBuffer().add(3);
        getIndexBuffer().add(7);
        getIndexBuffer().add(6);



        getIndexBuffer().add(0);
        getIndexBuffer().add(3);
        getIndexBuffer().add(7);
        getIndexBuffer().add(0);
        getIndexBuffer().add(7);
        getIndexBuffer().add(4);


        getIndexBuffer().add(0);
        getIndexBuffer().add(1);
        getIndexBuffer().add(2);
        getIndexBuffer().add(0);
        getIndexBuffer().add(3);
        getIndexBuffer().add(2);


        getIndexBuffer().add(4);
        getIndexBuffer().add(5);
        getIndexBuffer().add(6);
        getIndexBuffer().add(4);
        getIndexBuffer().add(7);
        getIndexBuffer().add(6);


        getIndexBuffer().add(4);
        getIndexBuffer().add(5);
        getIndexBuffer().add(1);
        getIndexBuffer().add(4);
        getIndexBuffer().add(0);
        getIndexBuffer().add(1);


        getIndexBuffer().add(1);
        getIndexBuffer().add(2);
        getIndexBuffer().add(6);
        getIndexBuffer().add(1);
        getIndexBuffer().add(5);
        getIndexBuffer().add(6);








    }
}
