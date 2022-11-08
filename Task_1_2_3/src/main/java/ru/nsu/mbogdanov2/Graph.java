package ru.nsu.mbogdanov2;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * We implement directed weighted graph.
 * Three different constructors for matrix of incidents and adjacency
 * and for list of adjacency
 */
public class Graph<T> {

    private static final int INF = Integer.MAX_VALUE;
    private final Map<T, Vertex<T>> vertexes;
    private final Map<T, List<Edge<T>>> edges;

    public Graph() {
        vertexes = new HashMap<>();
        edges = new HashMap<>();
    }

    /**
     * Matrix of adjacency.
     * Matrix is in a row-major implemented
     * example of row-major matrix
     *   |a b c |
     *   |d e f |   =  [a,b,c,d,e,f,j,k,l]
     *   |j k d |
     * This matrix size is [n * n] or [n][n], so
     * element number i in column number j
     * is equal to [i * len + j] = [i][j]
     *
     * @param vertexArray - vertexes
     * @param matrix - row-major matrix
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
                if (matrix[i * len + j] != 0) {
                    addEdge(vertexArray[i], vertexArray[j], matrix[i * len + j]);
                }
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
     *   |d e f |   =  [a,b,c,d,e,f]
     * This matrix size is [n * m] or [n][m], where n = edgeNumber and m = vertexNumber
     * element number i in column number j
     * is equal to [i * n + j] = [i][j]
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
                if (incidentMatrix[j * edgeNumber + i] > 0) {
                    k1 = vertexArray[j];
                    weight = incidentMatrix[j * edgeNumber + i];
                }
                if (incidentMatrix[j * edgeNumber + i] < 0) {
                    k2 = vertexArray[j];
                    weight = Math.abs(incidentMatrix[j * edgeNumber + i]);
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

    /**Graph representation with list of adjacency.
     * We have list with special type ListOfAdjacency
     * Then we just get information from this list and create edges
     *
     * @param vertexArray array of vertexes name
     * @param listOfAdjacency list of adjacency itself
     */
    public Graph(T[] vertexArray, List<ListOfAdjacency<T>> listOfAdjacency) {
        vertexes = new HashMap<>();
        edges = new HashMap<>();

        for (T t : vertexArray) {
            addVertex(t);
        }
        for (ListOfAdjacency<T> currentArray : listOfAdjacency) {
            for (int i = 0; i < currentArray.getSize(); i++) {
                addEdge(currentArray.getVertex(), currentArray.getVertexNameFromId(i),
                        currentArray.getWeightFromId(i));
            }

        }
    }

    /**We use this method to find vertexes for dijkstra.
     * If there are such vertexes in the graph, we return true
     * and this vertex, algorithms continues
     *
     * @return container with boolean value and vertex
     */
    public MyContainer<T> grayExist() {
        for (var entry : vertexes.entrySet()) {
            var value = entry.getValue();
            if (value.getMark() == 2) {
                return new MyContainer<>(true, value);
            }
        }
        return new MyContainer<>(false, null);
    }

    /**Dijkstra algorithm.
     * It finds array of vertexes with minimal distance to input array
     * I realized algorithm that our lector Gatilov gave us year ago.
     *  Pseudocode of algorithm.
     *   D[*] = +infinity,  mark[*] = 1
     *   D[s] = 0, mark[s] = 2
     *   while (gray vertex exists):
     *     u = vertex such that: 1) mark[u] = 2
     *                           2) D[u] = min
     *     mark[u] = 3
     *     for uv in E:
     *       if D[v] bigger than D[u] + w(uv):
     *         D[v] = D[u] + w(uv)
     *         mark[v] = G
     *
     * @param nameStart start vertex of the algorithm
     * @return sorted array of vertexes
     */
    public List<Vertex<T>> dijkstra(T nameStart) {
        if (!vertexes.containsKey(nameStart)) {
            throw new IllegalArgumentException("No vertex with this name");
        }

        var start = vertexes.get(nameStart);
        List<Vertex<T>> result = new ArrayList<>();

        vertexes.forEach((k, v) -> {
            result.add(v);
            v.setDistance(INF);
            v.setMark(1);
        });

        start.setDistance(0);
        start.setMark(2); //color 2 means that we have started algorithm for this vertex

        MyContainer<T> pair;
        while ((pair = grayExist()).getValueBoolean()) {
            Vertex<T> currentVertex = pair.getValueVertex();
            List<Edge<T>> currEdgeList = edges.get(currentVertex.getName());
            currentVertex.setMark(3); //algorithm for this vertex is ended
            if (currEdgeList == null) {
                continue;
            }
            for (var edges : currEdgeList) {
                Vertex<T> to = edges.getV2();
                to.setDistance(Math.min(
                        currentVertex.getDistance() + edges.getWeight(), to.getDistance()));
                if (to.getMark() != 3) {
                    to.setMark(2);
                }
            }
        }
        result.sort(Comparator.comparingInt(Vertex::getDistance));
        return result;
    }

    /**
     * Method to add new vertex to the graph.
     * If vertex with this name is already exist
     * or name is null, we do nothing
     *
     * @param name name of the vertex that we want to add
     */
    public void addVertex(T name) {
        if (vertexes.containsKey(name) || name == null) {
            throw new IllegalArgumentException("Invalid vertex name");
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
        if (weight <= 0) {
            throw new IllegalArgumentException("Only positive weight");
        }

        if (name1 == null || name2 == null) {
            throw new IllegalArgumentException("Only not null names");
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
        if (vertexes.get(name) == null || !vertexes.containsKey(name)) {
            throw new NullPointerException("Invalid vertex name");
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
    public Edge<T> deleteEdge(T name1, T name2, Integer weight) {
        if (!edges.containsKey(name1)) {
            throw new NullPointerException("No edges from this vertex");
        }
        for (var edge : edges.get(name1)) {
            if (edge.getV2().getName() == name2 && edge.getWeight() == weight) {
                edges.get(name1).remove(edge);
                return edge;
            }
        }
        throw new IllegalArgumentException("No such edge");
    }

    /**Getter of vertex.
     *
     * @param name name of the vertex
     * @return vertex
     */
    public Vertex<T> getVertex(T name) {
        return vertexes.get(name);
    }

    /**Method to get one edge from the graph with input data.
     *
     * @param nameFrom vertex - start of the edge
     * @param nameTo vertex - end of the edge
     * @param weight weight of this edge
     * @return edge or exception if there are no such edge
     */
    public Edge<T> getEdge(T nameFrom, T nameTo, Integer weight) {
        if (!vertexes.containsKey(nameFrom) || !vertexes.containsKey(nameTo)) {
            throw new NullPointerException("Invalid vertex name");
        }
        List<Edge<T>> nameFromEdges = edges.get(nameFrom);
        for (var edges : nameFromEdges) {
            if (edges.getV2().getName() == nameTo && edges.getWeight() == weight) {
                return edges;
            }
        }
        throw new IllegalArgumentException("No edge with this weight");
    }

    public Map<T, List<Edge<T>>> getEdges() {
        return edges;
    }

    public Map<T, Vertex<T>> getVertexes() {
        return vertexes;
    }

    public int getInf() {
        return INF;
    }

    /**Special private static class to return Vertex and boolean value in one time.
     */
    private static class MyContainer<T> {
        private final boolean valueBoolean;
        private final Vertex<T> valueVertex;

        public MyContainer(boolean first, Vertex<T> second) {
            valueBoolean = first;
            valueVertex = second;
        }

        public boolean getValueBoolean() {
            return valueBoolean;
        }

        public Vertex<T> getValueVertex() {
            return valueVertex;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Graph)) {
            return false;
        }
        Graph<?> graph = (Graph<?>) o;
        return Objects.equals(vertexes, graph.vertexes)
                && Objects.equals(edges, graph.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexes, edges);
    }
}