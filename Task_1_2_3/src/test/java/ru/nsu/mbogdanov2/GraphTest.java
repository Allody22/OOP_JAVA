package ru.nsu.mbogdanov2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
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
        System.out.println("Hello world");
        Assertions.assertEquals(numberOfEdges, vertexArray[0]);
    }
}
