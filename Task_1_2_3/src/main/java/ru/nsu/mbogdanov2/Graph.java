package ru.nsu.mbogdanov2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T extends Comparable<T>>{


    private final Map<T, Vertex<T>> vertexes;
    private final Map<T, List<Edge<T>>> edges;

    public Graph() {
        vertexes = new HashMap<>();
        edges = new HashMap<>();
    }

    /**
     * Матрица смежности.
     *
     * @param vertexArray - вершины
     * @param matrix - матрица
     */
    public Graph(T[] vertexArray, int[] matrix) {
        vertexes = new HashMap<>();
        edges = new HashMap<>();

        int len = vertexArray.length;
        /*addAllVertexes(vertexArray);

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                addEdge(vertexArray[i], vertexArray[j], matrix[i][j]);
            }
        }*/
    }


    public Graph(T[] vertexArray, List<T> listOfAdjacency) {
        vertexes = new HashMap<>();
        edges = new HashMap<>();

        int len = vertexArray.length;

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
        vertexes.put(name, v);
    }

    /**
     * Adding new edge to the graph.
     *
     * @param name1 name of the first vertex
     * @param name2 name of the second vertex
     * @param weight positive weight of the vertex
     */
    public void addEdge(T name1, T name2, Integer weight) {
        if (weight <= 0 || name1 == null || name2 == null){
            throw new IllegalArgumentException("Only positive weight and not null names");
        }

        Vertex<T> v1 = vertexes.get(name1);
        Vertex<T> v2 = vertexes.get(name2);

        Edge<T> e = new Edge<>(v1, v2, weight);

        edges.put(v1.getName(), new ArrayList<>());
        edges.put(v2.getName(), new ArrayList<>());

        edges.get(v1.getName()).add(e);
    }

    /**
     * Deleting vertex from the graph.
     * Function throw null pointer exception is key is null
     * We delete all edges from and to this vertex
     *
     * @param key key of the vertex
     * @return deleted vertex
     */
    public Vertex<T> deleteVertex(T key) {
        if (vertexes.get(key) == null){
                throw new NullPointerException("This key is null, don't try to use it");
        }
        edges.remove(key);
        Vertex<T> deletedVertex = vertexes.get(key);
        vertexes.remove(key);
        return deletedVertex;
    }
}


/* Еще не знаю как всё это реализовывать
 * Поэтому напишу тут свои догадки по опитимизации и сокращению необходимой памяти
 */

    /*Матрицы смежности - это матрица n*n, где n - кол-во вершин

     В строке j колоны i стоит цифра один, если из вершины j в i есть путь длинной 1
     Иначе в матрице стоит нуль.
     Идея для упрощения: хранить матрицу в row-major представление, то есть двумерный массив как бы
     сужается в одномерный  матрица | a b c | будет храниться как массив [a,b,c,d,e,f,j,k,l]
                                    | d e f |
                                    | j k l |
     A[i*n+j] это элемеент в i-th строке и j-th столба
     */

    /*Матрица инцидентности - строки вершины графа, а столбцы - ребра графа

      n - кол-во столбцов
      A[i*n+j] = 1, если ребро выходит из вершины
               = -1, если входит в вершину
               = 0, если ребра нет или тут петля
     Храним также как матрицу смежности
     */