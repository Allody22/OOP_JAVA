package ru.nsu.mbogdanov2;


import java.util.Objects;

/**
 * Edge connects vertexes or one vertex in the graph.
 */
public class Edge<T> {
    private final Vertex<T> v1;
    private final Vertex<T> v2;
    private final Integer weight;

    /**
     *Edge constructor.
     * We store the name of the edge as concatenation
     * of 2 vertexes connected by this edge
     *
     * @param from out coming vertex
     * @param to in coming vertex
     * @param weight weight of the edge
     */
    public Edge(Vertex<T> from, Vertex<T> to, Integer weight) {
        v1 = from;
        v2 = to;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex<T> getV2() {
        return v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(v1, edge.v1) && Objects.equals(v2, edge.v2)
                && Objects.equals(weight, edge.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2, weight);
    }
}
