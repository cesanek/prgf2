package model;

import java.util.ArrayList;
import java.util.List;

public class Solid {
    private List<Vertex> vertexBuffer;
    private List<Integer> indexBuffer;
    private List<Part> partBuffer;

    public Solid() {
        vertexBuffer = new ArrayList<>();
        indexBuffer = new ArrayList<>();
        partBuffer = new ArrayList<>();
    }

    public List<Vertex> getVertexBuffer() {
        return vertexBuffer;
    }

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public List<Part> getPartBuffer() {
        return partBuffer;
    }
}
