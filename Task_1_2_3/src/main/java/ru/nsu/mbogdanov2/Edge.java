package ru.nsu.mbogdanov2;


/**
 * Edge connects vertexes in the graph.
 *
 */
public class Edge<T extends Comparable<T>> {
    private final Vertex<T> v1;
    private final Vertex<T> v2;
    private Integer weight;

    /**
     *Edge constructor.
     * We store the name of the edge as concatenation
     * of 2 vertexes connected by this edge
     *
     * @param from out coming vertex
     * @param to in coming vertex
     * @param weight weight of the edge
     */
    public Edge( Vertex<T> from, Vertex<T> to, Integer weight){
        v1 = from;
        v2 = to;
        this.weight = weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex<T> getV1() {
        return v1;
    }

    public Vertex<T> getV2() {
        return v2;
    }
}
