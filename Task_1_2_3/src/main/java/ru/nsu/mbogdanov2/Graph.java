package ru.nsu.mbogdanov2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We implement directed weighted graph.
 * Three different constructors for matrix of incidents and adjacency
 * and for list of adjacency
 *
 * @param <T>
 */
public class Graph<T extends Comparable<T>> {

    private final Map<T, Vertex<T>> vertexes;
    private final Map<T, List<Edge<T>>> edges;
    private final int INF = Integer.MAX_VALUE;

    public Graph() {
        vertexes = new HashMap<>();
        edges = new HashMap<>();
    }

    /**
     * Matrix of adjacency.
     * Matrix is in a row-major implemented
     *  example of row-major matrix
     *   |a b c |
     *   |d e f |   =>  [a,b,c,d,e,f,j,k,l]
     *   |j k d |
     *
     * @param vertexArray - вершины
     * @param matrix - матрица
     */
    public Graph(T[] vertexArray, int[] matrix) {
        vertexes = new HashMap<>();
        edges = new HashMap<>();

        int len = vertexArray.length;
        for (T t : vertexArray) {
            addVertex(t);
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                addEdge(vertexArray[i], vertexArray[j], matrix[i*len+j]);
            }
        }
    }

    /**
     * Matrix of incident.
     * If there are only one positive or ine negative weight
     * We think that it's a loop
     * Matrix is in a row-major implemented
     * example of row-major matrix
     *   |a b c |
     *   |d e f |   =>  [a,b,c,d,e,f,j,k,l]
     *   |j k d |
     *
     * @param vertexArray array of vertexes name in matrix
     * @param incidentMatrix matrix of incident itself
     * @param edgeNumber number of edges
     */
    public Graph(T[] vertexArray, int[] incidentMatrix, int edgeNumber) {
        vertexes = new HashMap<>();
        edges = new HashMap<>();

        int len = vertexArray.length;
        for (T t : vertexArray) {
            addVertex(t);
        }

        for (int i = 0; i < edgeNumber; i++) {
            int weight = 0;
            T k1 = null;
            T k2 = null;
            for (int j = 0; j < len; j++) {
                if (incidentMatrix[j*edgeNumber+i] > 0) {
                    k1 = vertexArray[j];
                    weight = incidentMatrix[j*edgeNumber+i];
                }
                if (incidentMatrix[j*edgeNumber+i] < 0) {
                    k2 = vertexArray[j];
                    weight = Math.abs(incidentMatrix[j*edgeNumber+i]);
                }
            }
            if (k1 != null && k2 != null) {
                addEdge(k1, k2, weight);
            }
            if (k1 == null && k2 != null) {
                addEdge(k2, k2, weight);
            }
            if (k1 != null && k2 == null) {
                addEdge(k1, k1, weight);
            }
        }
    }

    /**
     * Graph representation from list of adjacency.
     *
     * @param vertexArray array of existing vertexes
     * @param vertexList list of adjacency
     * @param weights weights of the edges
     */
    public Graph(T[] vertexArray, List<T>[] vertexList, List<Integer>[] weights) {
        vertexes = new HashMap<>();
        edges = new HashMap<>();

        int len = vertexArray.length;
        for (T t : vertexArray) {
            addVertex(t);
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < vertexList[i].size(); j++) {
                addEdge(vertexArray[i], vertexList[i].get(j), weights[i].get(j));
            }
        }
    }

    public List<Vertex<T>> dijkstra(T vertexStart){
        if (!vertexes.containsKey(vertexStart)) {
            throw new IllegalArgumentException("No vertex with this name");

            Vertex<T> start = vertexes.get(vertexStart);

            List<Vertex<T>> distanceArray = new ArrayList<>();

            vertexes.forEach((k, v) -> {
                distanceArray.add(v);
                v.setVisited(false);
                v.setDistance(INF);
            });

        }
        /*
        D[*] = +infinity,  mark[*] = W
        D[s] = 0, mark[s] = G
        while (gray vertex exists):
            u = vertex such that: 1) mark[u] = G
                                    2) D[u] = min
        mark[u] = B
        for uv in E:
            if D[v] > D[u] + w(uv):
            D[v] = D[u] + w(uv)
            F[v] = u
            mark[v] = G
         */
    }
    /**
     * Method to add new vertex to the graph.
     * If vertex with this name is already exist
     * or name is null, we do nothing
     *
     * @param name name of the vertex that we want to add
     */
    public void addVertex(T name){
        if (vertexes.containsKey(name) || name == null){
            return;
        }
        Vertex<T> v = new Vertex<>(name);
        edges.put(v.getName(), new ArrayList<>());
        vertexes.put(name, v);
    }

    /**
     * Adding new edge to the graph.
     *
     * @param name1 name of the first vertex
     * @param name2 name of the second vertex
     * @param weight positive weights of the vertex
     */
    public void addEdge(T name1, T name2, Integer weight) {
        if (weight <= 0 || name1 == null || name2 == null){
            throw new IllegalArgumentException("Only positive weight and not null names");
        }

        Vertex<T> v1 = vertexes.get(name1);
        Vertex<T> v2 = vertexes.get(name2);

        Edge<T> e = new Edge<>(v1, v2, weight);

        edges.get(v1.getName()).add(e);
    }

    /**
     * Deleting vertex from the graph.
     * Function throw null pointer exception is key is null
     * We delete all edges from and to this vertex
     *
     * @param name key of the vertex
     * @return deleted vertex
     */
    public Vertex<T> deleteVertex(T name) {
        if (vertexes.get(name) == null){
                throw new NullPointerException("This key is null, don't try to use it");
        }
        if(!vertexes.containsKey(name)) {
            throw new IllegalArgumentException("No vertex with this name");
        }
        edges.remove(name);
        var deletedVertex = vertexes.get(name);
        vertexes.remove(name);
        return deletedVertex;
    }

    /**
     * Deleting edge from the graph.
     * We remove the edge, but don't do anything with vertexes
     *
     * @param name1 name of the first vertex
     * @param name2 name of incoming vertex
     * @return return deleted edge or throw exception
     */
    public Edge<T> deleteEdge(T name1, T name2) {
        List<Edge<T>> listOfEdges = edges.get(name1);
        for (int i = 0; i < listOfEdges.size(); i++) {
            if (listOfEdges.get(i).getV2().getName() == name2) {
                return listOfEdges.remove(i);
            }
        }
        throw new IllegalArgumentException("No such edge");
    }
}