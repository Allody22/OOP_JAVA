package ru.nsu.mbogdanov2;

import static org.junit.jupiter.api.Assertions.assertThrows;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testing of graph implementations with dijkstra algorithm.
 * We scan line and parse them into good type.
 * Then we use one of the implementations to create the graph
 * And,finally, we start dijkstra algorithm and check its result
 * by comparing it with the actual result, found ourselves without code
 */
public class GraphTest {

    @Test
    public void exceptionsTest() {
        InputStream file = getClass().getClassLoader().
                getResourceAsStream("matrixOfAdjacencyTest.txt");
        assert file != null;
        Scanner sc = new Scanner(file);
        String vertexesLine = sc.nextLine();
        String[] vertexArray = vertexesLine.split("\\s");

        int numberOfVertexes = vertexArray.length;
        int[] matrixOfAdjacency = new int[numberOfVertexes * numberOfVertexes];

        for( int i = 0; i < numberOfVertexes; i++) {
            for( int j = 0; j < numberOfVertexes; j++) {
                matrixOfAdjacency[i * numberOfVertexes + j] = sc.nextInt();
            }
        }
        Graph<String> graph = new Graph<>(vertexArray,matrixOfAdjacency);

        IllegalArgumentException exceptionDijkstra =
                assertThrows(IllegalArgumentException.class, () -> graph.dijkstra("seven"));
        Assertions.assertEquals("No vertex with this name", exceptionDijkstra.getMessage());

        IllegalArgumentException exceptionAddEdge =
                assertThrows(IllegalArgumentException.class, () -> graph.addEdge("one","two",-10));
        Assertions.assertEquals("Only positive weight", exceptionAddEdge.getMessage());

        NullPointerException exceptionDeleteVertex =
                assertThrows(NullPointerException.class, () -> graph.deleteVertex("seven"));
        Assertions.assertEquals("Invalid vertex name", exceptionDeleteVertex.getMessage());

        IllegalArgumentException exceptionDeleteEdge =
                assertThrows(IllegalArgumentException.class, () -> graph.deleteEdge("two","two",33));
        Assertions.assertEquals("No such edge", exceptionDeleteEdge.getMessage());
    }

    @Test
    public void matrixOfIncidentTest() {
        var file = getClass().getClassLoader().
                getResourceAsStream("matrixOfIncidentTest.txt");
        assert file != null;
        var sc = new Scanner(file);
        var vertexesLine = sc.nextLine();

        var vertexArrayForInt = Arrays.stream(vertexesLine.split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Integer[] vertexArray = Arrays.stream(vertexArrayForInt).boxed().toArray(Integer[]::new);

        var numberOfEdges = sc.nextInt();
        var numberOfVertexes = vertexArray.length;
        var matrixOfIncident = new int[numberOfVertexes * numberOfEdges];

        for (int i = 0; i < numberOfVertexes; i++) {
            for (int j = 0; j < numberOfEdges; j++) {
                matrixOfIncident[i * numberOfEdges + j] = sc.nextInt();
            }
        }
        Graph<Integer> actualGraph = new Graph<>(vertexArray, matrixOfIncident, numberOfEdges);

        var actual = actualGraph.dijkstra(300);
        var actualSecondVertex = new Vertex<>(200);
        actualSecondVertex.setDistance(20);
        actualSecondVertex.setMark(3);

        Assertions.assertEquals((actual.get(0)).getDistance(), 0);
        Assertions.assertEquals(actual.get(1), actualSecondVertex);
        Assertions.assertEquals(actual.get(2), actualGraph.getVertex(actual.get(2).getName()));
    }

    @Test
    public void matrixOfAdjacencyTest() {
        InputStream file = getClass().getClassLoader().
                getResourceAsStream("matrixOfAdjacencyTest.txt");
        assert file != null;
        var sc = new Scanner(file);
        var vertexesLine = sc.nextLine();
        var vertexArray = vertexesLine.split("\\s");

        int numberOfVertexes = vertexArray.length;
        int[] matrixOfAdjacency = new int[numberOfVertexes * numberOfVertexes];

        for (int i = 0; i < numberOfVertexes; i++) {
            for (int j = 0; j < numberOfVertexes; j++) {
                matrixOfAdjacency[i * numberOfVertexes + j] = sc.nextInt();
            }
        }
        Graph<String> graph = new Graph<>(vertexArray, matrixOfAdjacency);
        var actual = graph.dijkstra("five");
        int[] expectedDistance = {0, 10, 10, 20, 30};
        String[] expectedNames = {"five", "one", "three", "two", "four"};

        for(int i = 0; i < actual.size(); i++) {
            Assertions.assertEquals(actual.get(i).getDistance(), expectedDistance[i]);
            Assertions.assertEquals(actual.get(i).getName(), expectedNames[i]);
        }
    }

    @Test
    public void listOfAdjacencyTest() {
        InputStream file = getClass().getClassLoader().
                getResourceAsStream("listOfAdjacencyTest.txt");
        assert file != null;
        Scanner sc = new Scanner(file);
        String vertexesLine = sc.nextLine();
        String[] vertexArray = vertexesLine.split("\\s");

        int numberOfVertexes = vertexArray.length;
        List<ListOfAdjacency<String>> listOfAdjacency = new ArrayList<>();

        for (int i = 0; i < numberOfVertexes; i++) {
            String lineOfAdjacency = sc.nextLine();
            var arrayForLineOfAdjacency = lineOfAdjacency.split("\\s");
            var vertexPair = new ListOfAdjacency<String>();
            vertexPair.setVertex(arrayForLineOfAdjacency[0]);
            for (int j = 1; j < arrayForLineOfAdjacency.length - 1; j+=2) {
                vertexPair.setVertexName(arrayForLineOfAdjacency[j]);
                vertexPair.setWeight(Integer.valueOf(arrayForLineOfAdjacency[j + 1]));
            }
            listOfAdjacency.add(vertexPair);
        }
        var expectedGraph = new Graph<>(vertexArray, listOfAdjacency);
        var actual = expectedGraph.dijkstra("b");
        for (int i = 1; i < actual.size(); i++){
            Assertions.assertEquals(actual.get(i).getDistance(), Integer.MAX_VALUE);
        }
    }
}
