package model;


public interface Vectorable<V> {

    V add(Vertex vec);

    V mul(double scalar);
}
