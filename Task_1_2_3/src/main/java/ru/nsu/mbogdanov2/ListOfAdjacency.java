package ru.nsu.mbogdanov2;

import java.util.ArrayList;
import java.util.List;

/**Class for comfortable representation of lists of adjacency.
 *
 */
public class ListOfAdjacency<T> {
    private T vertex;
    private final List<T> vertexName;
    private final List<Integer> weight;
    private int size;


    public ListOfAdjacency() {
        vertex = null;
        vertexName = new ArrayList<>();
        weight = new ArrayList<>();
        size = 0;
    }

    public Integer getWeightFromId(Integer id) {
        return weight.get(id);
    }

    public T getVertexNameFromId(Integer id) {
        return vertexName.get(id);
    }

    public T getVertex() {
        return vertex;
    }

    public int getSize() {
        return size;
    }

    public void setVertex(T vertex) {
        this.vertex = vertex;
    }

    public void setWeight(Integer weight) {
        this.weight.add(weight);
    }

    public void setVertexName(T vertexName) {
        size++;
        this.vertexName.add(vertexName);
    }
}
