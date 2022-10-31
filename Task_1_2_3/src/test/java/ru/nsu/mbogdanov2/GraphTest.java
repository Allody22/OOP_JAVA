package ru.nsu.mbogdanov2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Special class for testing graph methods.
 */
public class GraphTest {
    @Test
    public void matrixOfIncidentTest() throws FileNotFoundException {
        File file = new File("src/test/resources/matrixOfIncidentTest.txt");
        Scanner sc = new Scanner(file);
        String vertexesLine = sc.nextLine();

        int[] vertexArrayForInt = Arrays.stream(vertexesLine.split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Integer[] vertexArray = Arrays.stream( vertexArrayForInt ).boxed().toArray( Integer[]::new );

        int numberOfEdges = sc.nextInt();
        int numberOfVertexes = vertexArray.length;
        int[] matrixOfIncident = new int[numberOfVertexes * numberOfEdges];

        for( int i = 0; i < numberOfVertexes; i++) {
            for( int j = 0; j < numberOfEdges; j++) {
                matrixOfIncident[i * numberOfEdges + j] = sc.nextInt();
            }
        }
        Graph<Integer> graph = new Graph<>(vertexArray,matrixOfIncident,numberOfEdges);

        var answer = graph.dijkstra(300);
        var secondVertex = new Vertex<>(200);
        secondVertex.setDistance(20);
        secondVertex.setMark(3);

        Assertions.assertEquals((answer.get(0)).getDistance(), 0);
        Assertions.assertEquals(answer.get(1), secondVertex);
        Assertions.assertEquals(answer.get(2),graph.getVertex(answer.get(2).getName()));
    }

    @Test
    public void matrixOfAdjacencyTest() throws FileNotFoundException {
        File file = new File("src/test/resources/matrixOfAdjacencyTest.txt");
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

        var actual = graph.dijkstra("five");
        int[] expected= {0,10,10,20,30};

        for(int i = 0; i < actual.size(); i++){
            Assertions.assertEquals(actual.get(i).getDistance(),expected[i]);
        }
    }
}
