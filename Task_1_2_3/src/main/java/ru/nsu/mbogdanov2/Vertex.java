package ru.nsu.mbogdanov2;


import java.util.Objects;

/**
 * Vertex is a point in the graph with distance.
 * Vertexes are connected by edges
 * Name is a string name of this vertex. For example: A,B,C etc.
 *
 */
public class Vertex<T extends Comparable<T>> {
    private final T name;
    private Integer distance;
    private int mark;

    public Vertex(T name){
        this.name = name;
        distance = null;
        mark = 1;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDistance() {
        return distance;
    }

    public T getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex<?> vertex = (Vertex<?>) o;
        return mark == vertex.mark && Objects.equals(name, vertex.name) && Objects.equals(distance, vertex.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, distance, mark);
    }
}
