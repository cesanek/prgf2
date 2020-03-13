package model;

import transforms.Col;
import transforms.Integer;
import transforms.Point3D;

public class Vertex implements Vectorable<Vertex> {
    public Point3D getPosition() {
        return position;
    }

    public Col getColor() {
        return color;
    }

    public void setColor(Col color){
        this.color=color;

    }

    //
    private final Point3D position;
    private Col color;


    public Vertex(Point3D position, Col color) {
        this.position = position;
        this.color = color;
    }

    @Override
    public Vertex add(Vertex vec) {
        // position=position.add(vec.getPosition());
        return new Vertex(position.add(vec.getPosition()),
                this.color.add(vec.getColor()));
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "position=" + position +
                ", color=" + color +
                '}';
    }

    @Override
    public Vertex mul(double scalar) {
        return new Vertex(this.position.mul(scalar),
                this.color.mul(scalar));
    }

    public Vertex dehomog() {


        return this.mul(1 / position.getW());

    }


}
