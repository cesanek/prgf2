package model;

import transforms.Point3D;

public interface Vectorable<V> {

    V add(Vertex vec);
    V mul(double scalar);
}
